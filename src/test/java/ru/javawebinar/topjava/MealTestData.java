package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final int USER_MEAL_1_ID = START_SEQ + 2;
    public static final int USER_MEAL_2_ID = START_SEQ + 3;
    public static final int USER_MEAL_3_ID = START_SEQ + 4;
    public static final int USER_MEAL_4_ID = START_SEQ + 5;
    public static final int USER_MEAL_5_ID = START_SEQ + 6;
    public static final int USER_MEAL_6_ID = START_SEQ + 7;
    public static final int ADMIN_MEAL_1_ID = START_SEQ + 8;
    public static final int ADMIN_MEAL_2_ID = START_SEQ + 9;

    public static final UserMeal USER_MEAL_1 = new UserMeal(USER_MEAL_1_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final UserMeal USER_MEAL_2 = new UserMeal(USER_MEAL_2_ID, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final UserMeal USER_MEAL_3 = new UserMeal(USER_MEAL_3_ID, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final UserMeal USER_MEAL_4 = new UserMeal(USER_MEAL_4_ID, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final UserMeal USER_MEAL_5 = new UserMeal(USER_MEAL_5_ID, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final UserMeal USER_MEAL_6 = new UserMeal(USER_MEAL_6_ID, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final UserMeal ADMIN_MEAL_1 = new UserMeal(ADMIN_MEAL_1_ID, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final UserMeal ADMIN_MEAL_2 = new UserMeal(ADMIN_MEAL_2_ID, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);



}
