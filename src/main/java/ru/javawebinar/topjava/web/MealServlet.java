package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to MealList");
        resp.setContentType("text/html;charset=utf-8");

        List<UserMealWithExceed> testUmweList = UserMealsUtil.getFilteredMealsWithExceeded(UserMeal.TEST_USER_MEAL_LIST, LocalTime.of(0, 0), LocalTime.of(23, 59, 59), 2000);
        req.setAttribute("UMWE_List", testUmweList);

        LOG.debug("forward to MealList");
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }


}
