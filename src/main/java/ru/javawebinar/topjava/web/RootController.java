package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMealRestController mealController;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String root2() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        LoggedUser.setId(userId);
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", mealController.getAll());
        return "mealList";
    }

/*
    @RequestMapping(value = "/meals", method = RequestMethod.GET, params = {"action", "id"})
    public String mealListDeleteOrUpdateAction(@RequestParam("action") String action, @RequestParam("id") String id, Model model) {
        if ("delete".equals(action)) {
            int intId = Integer.valueOf(id);
            mealController.delete(intId);
            return "redirect:meals";
        } else if ("update".equals(action)) {
            int intId = Integer.valueOf(id);
            UserMeal userMeal = mealController.get(intId);
            model.addAttribute("meal", userMeal);
            return "redirect:mealEdit";
        }
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET, params = {"action"})
    public String mealListCreateAction(@RequestParam("action") String action, Model model) {
        if ("create".equals(action)) {
            UserMeal userMeal = new UserMeal(LocalDateTime.now(), "", 1000);
            model.addAttribute("meal", userMeal);
            return "redirect:mealEdit";
        }
        return "redirect:meals";
    }
*/

    @RequestMapping(value = "/meals/delete/{id}", method = RequestMethod.GET)
    public String mealDelete(@PathVariable Integer id) {
        mealController.delete(id);
        return "redirect:/meals";
    }

    @RequestMapping(value = "/meals/update/{id}", method = RequestMethod.GET)
    public String mealEdit(@PathVariable Integer id, Model model) {
        UserMeal userMeal = mealController.get(id);
        model.addAttribute("meal", userMeal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals/create", method = RequestMethod.GET)
    public String mealCreate(Model model) {
        UserMeal userMeal = new UserMeal(LocalDateTime.now(), "", 1000);
        model.addAttribute("meal", userMeal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String saveUserMeal(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        final UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            mealController.create(userMeal);
        } else {
            String paramId = Objects.requireNonNull(request.getParameter("id"), "parameter id  must not be null");
            mealController.update(userMeal, Integer.valueOf(paramId));
        }
        return ("redirect:meals");
    }

    @RequestMapping(value = "/meals/filter", method = RequestMethod.POST)
    public String filterUserMeal(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException {
        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request));
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request));
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request));
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request));

        model.addAttribute("mealList", mealController.getBetween(startDate, startTime, endDate, endTime));
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        return "";
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }

}
