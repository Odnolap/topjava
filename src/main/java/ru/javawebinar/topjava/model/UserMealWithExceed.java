package ru.javawebinar.topjava.model;


import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {
    public static final Map<Integer, UserMealWithExceed> USER_MEAL_WITH_EXCEED_MAP = new ConcurrentHashMap<>();
    static {
        int i = 0;
        for (UserMealWithExceed umwe : UserMealsUtil.getFilteredMealsWithExceeded(UserMeal.TEST_USER_MEAL_LIST, LocalTime.of(0, 0), LocalTime.of(23, 59, 59), 2000)) {
            USER_MEAL_WITH_EXCEED_MAP.put(i++, umwe);
        }
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

}
