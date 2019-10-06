package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealRepository;
import ru.javawebinar.topjava.dao.MealRepositoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = getLogger(UserServlet.class);
    private static final String mealsJp = "/meals.jsp";
    private MealRepository mealRepository;

    @Override
    public void init() throws ServletException {
        this.mealRepository = new MealRepositoryImpl();
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 4, 8, 30), "Breakfast", 800));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 4, 14, 15), "Lunch ", 600));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 4, 18, 50), "Dinner", 1100));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 5, 6, 5), "Breakfast", 300));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 5, 12, 50), "Lunch", 200));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 5, 21, 30), "Dinner", 1300));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 6, 7, 30), "Breakfast", 900));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 6, 12, 30), "Lunch", 100));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 6, 21, 50), "Dinner", 700));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 7, 7, 30), "Breakfast", 900));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 7, 12, 30), "Lunch", 100));
        mealRepository.addMeal(new Meal(LocalDateTime.of(2019, Month.OCTOBER, 7, 21, 50), "Dinner", 1100));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals");
        response.setContentType("text/html;charset=utf-8");
        final String action = request.getParameter("action");
        final String id = request.getParameter("id");
        log.debug(request.getRequestURI() + " params : id=" + id + " action=" + action);

        if (action == null) {
            List<Meal> mealList = mealRepository.findAllMeal();
            List<MealTo> mealToList = MealsUtil.getNotFilteredWithExcees(mealList, 2000);
            request.setAttribute("mealToFromServer", mealToList);
            request.getRequestDispatcher(mealsJp).forward(request, response);
            return;
        }

        Meal meal;
        switch (action) {
            case "add":
                request.setAttribute("meal", new Meal());
                request.getRequestDispatcher(mealsJp).forward(request, response);
                return;

            case "edit":
                meal = mealRepository.getMealById(Integer.parseInt(id));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher(mealsJp).forward(request, response);
                return;

            case "delete":
                mealRepository.deleteMeal(Integer.parseInt(id));
                response.sendRedirect("meals");
                return;

            default:
                response.sendRedirect(mealsJp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug(request.getRequestURI());
        request.setCharacterEncoding("UTF-8");
        final String id = request.getParameter("id");
        final String description = request.getParameter("description");
        final int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        Meal meal = new Meal(localDateTime, description, calories);
        if (id == null || id.isEmpty())
            mealRepository.addMeal(meal);
        else {
            meal.setId(Integer.parseInt(id));
            mealRepository.updateMeal(meal);
        }
        response.sendRedirect("meals");
    }
}

