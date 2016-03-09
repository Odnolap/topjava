package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal {
    public static final List<UserMeal> TEST_USER_MEAL_LIST = new ArrayList<>();
    static {
        TEST_USER_MEAL_LIST.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        TEST_USER_MEAL_LIST.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        TEST_USER_MEAL_LIST.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        TEST_USER_MEAL_LIST.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        TEST_USER_MEAL_LIST.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        TEST_USER_MEAL_LIST.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }
    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }
}
