package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Odnolap on 04.05.2016.
 */
@RestController
@RequestMapping("/ajax/meals")
public class UserMealAjaxController extends AbstractUserMealController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@RequestParam("id") int id,
                               @RequestParam("dateTime") String dateTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") String calories) {

        UserMeal userMeal = new UserMeal(id, LocalDateTime.parse(dateTime), description, Integer.parseInt(calories));
        if (id == 0) {
            super.create(userMeal);
        } else {
            super.update(userMeal, id);
        }
    }
}
