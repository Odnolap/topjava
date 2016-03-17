package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public class UserMealRestController {
    private UserMealService service = new UserMealServiceImpl();
    
    public UserMeal save(UserMeal userMeal, User user) {
        return service.save(userMeal,user.getId());
    }

    public List<UserMeal> getAll(User user) {
        return service.getAll(user.getId());
    }

    public void delete(int id, User user) {
        service.delete(id,user.getId());
    }

    public UserMeal get(int id, User user) {
        return service.get(id, user.getId());
    }

}
