package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public class UserMealRestController {
    private ProfileRestController profileRestController = new ProfileRestController();
    User user = profileRestController.get();
    private UserMealService service = new UserMealServiceImpl();
    
    public UserMeal save(String id, LocalDateTime dateTime, String description, int calories) {
        return service.save(new UserMeal(id == null ? null : Integer.valueOf(id), dateTime, description, calories, user.getId()), user.getId());
    }

    public List<UserMeal> getAll() {
        return service.getAll(user.getId());
    }

    public void delete(int id) {
        service.delete(id, user.getId());
    }

    public UserMeal get(int id) {
        return service.get(id, user.getId());
    }

}
