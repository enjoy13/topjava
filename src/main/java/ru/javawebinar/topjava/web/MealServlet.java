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
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private MealRepository mealRepository;

    @Override
    public void init() throws ServletException {
        this.mealRepository = new MealRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals");
        response.setContentType("text/html;charset=utf-8");
        List<Meal> mealList = mealRepository.findAllMealTo();
        List<MealTo> mealToList = MealsUtil.getNotFilteredWithExcees(mealList, 2000);
        request.setAttribute("mealToFromServer", mealToList);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
