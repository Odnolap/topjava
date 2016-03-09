package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {
    public static final List<UserMealWithExceed> TEST_UMWE_LIST;
    static {
        TEST_UMWE_LIST = UserMealsUtil.getFilteredMealsWithExceeded(UserMeal.TEST_USER_MEAL_LIST, LocalTime.of(0, 0), LocalTime.of(23, 59, 59), 2000);
    }

    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    protected final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}
