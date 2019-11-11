package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController {

    private final MealService mealService;

    public JspMealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        var meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping
    public String getMeals(Model model) {
        model.addAttribute("meals", MealsUtil.getTos(mealService.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay()));
        return "meals";
    }

    @GetMapping("/update")
    public String update(Model model, @RequestParam String id) {
        model.addAttribute("meal", mealService.get(Integer.parseInt(id), SecurityUtil.authUserId()));
        return "mealForm";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        mealService.delete(Integer.parseInt(id), SecurityUtil.authUserId());
        return "redirect:/meals";
    }

    @PostMapping("/save")
    public String save(@RequestParam String id, @RequestParam String dateTime, @RequestParam String description, @RequestParam String calories) {
        Meal meal = new Meal(LocalDateTime.parse(dateTime), description, Integer.parseInt(calories));
        if (id.isEmpty()) {
            mealService.create(meal, SecurityUtil.authUserId());
        } else {
            meal.setId(Integer.parseInt(id));
            mealService.update(meal, SecurityUtil.authUserId());
        }
        return "redirect:/meals";
    }

    @GetMapping("/filter")
    public String filter(Model model, @RequestParam String startDate,
                         @RequestParam String endDate,
                         @RequestParam String startTime,
                         @RequestParam String endTime) {
        List<Meal> mealsDateFiltered = mealService.getBetweenDates(parseLocalDate(startDate), LocalDate.parse(endDate), SecurityUtil.authUserId());
        List<MealTo> list = MealsUtil.getFilteredTos(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(), parseLocalTime(startTime), parseLocalTime(endTime));
        model.addAttribute("meals", list);
        return "meals";
    }
}
