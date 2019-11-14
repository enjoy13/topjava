package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {

    @GetMapping("/create")
    public String create(Model model) {
        var meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping
    public String getMeals(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("/update")
    public String update(Model model, @RequestParam String id) {
        model.addAttribute("meal", get(Integer.parseInt(id)));
        return "mealForm";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        delete(Integer.parseInt(id));
        return "redirect:/meals";
    }

    @PostMapping("/save")
    public String save(@RequestParam String id, @RequestParam String dateTime, @RequestParam String description, @RequestParam String calories) {
        Meal meal = new Meal(LocalDateTime.parse(dateTime), description, Integer.parseInt(calories));
        if (id.isEmpty()) {
            create(meal);
        } else {
            int mealId = Integer.parseInt(id);
            meal.setId(mealId);
            update(meal, mealId);
        }
        return "redirect:/meals";
    }

    @GetMapping("/filter")
    public String filter(Model model, @RequestParam String startDate, @RequestParam String endDate,
                         @RequestParam String startTime, @RequestParam String endTime) {
        model.addAttribute("meals", getBetween(parseLocalDate(startDate), parseLocalTime(startTime), parseLocalDate(endDate), parseLocalTime(endTime)));
        return "meals";
    }
}
