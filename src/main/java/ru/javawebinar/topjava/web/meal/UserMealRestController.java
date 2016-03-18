package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    @Autowired
    private UserMealService service; // = new UserMealServiceImpl();

    public UserMeal save(String id, LocalDateTime dateTime, String description, int calories) {
        return service.save(new UserMeal(id.isEmpty() ? null : Integer.valueOf(id), dateTime, description, calories, LoggedUser.id()), LoggedUser.id());
    }

    public List<UserMealWithExceed> getAll() {
        return UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id(), null, null), LoggedUser.getCaloriesPerDay());
    }

    public List<UserMealWithExceed> getAll(LocalDate dateFrom, LocalDate dateTo, LocalTime startTime, LocalTime endTime) {
        return UserMealsUtil.getFilteredWithExceeded(service.getAll(LoggedUser.id(), dateFrom, dateTo), startTime, endTime, LoggedUser.getCaloriesPerDay());
    }

    public void delete(int id) {
        service.delete(id, LoggedUser.id());
    }

    public UserMeal get(int id) {
        return service.get(id, LoggedUser.id());
    }

    public UserMeal create(LocalDateTime dateTime, String description, int calories) {
        return new UserMeal(dateTime, description, calories, LoggedUser.id());
    }

}
