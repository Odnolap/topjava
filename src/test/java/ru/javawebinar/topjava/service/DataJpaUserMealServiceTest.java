package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.ADMIN;

/**
 * Created by Odnolap on 05.04.2016.
 */
@ActiveProfiles({Profiles.POSTGRES, Profiles.DATAJPA})
public class DataJpaUserMealServiceTest extends UserMealServiceTest {

    @Test
    public void getMealWithUser() throws Exception {
        UserMeal actualUserMeal = service.getMealWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL, actualUserMeal);
        UserTestData.MATCHER.assertEquals(ADMIN, actualUserMeal.getUser());
    }

    @Test
    public void getUserWithMeal() throws Exception {
        Collection<UserMeal> actualUserMeals = service.getUserWithMeal(USER_ID);
        User actualUser = actualUserMeals.iterator().next().getUser();
        UserTestData.MATCHER.assertEquals(USER, actualUser);
        MATCHER.assertCollectionEquals(USER_MEALS, actualUserMeals);
    }

}
