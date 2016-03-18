package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal, int ownerUserId);

    // false if not found
    boolean delete(int id, int ownerUserId);

    // null if not found
    UserMeal get(int id, int ownerUserId);

    List<UserMeal> getAll(int ownerUserId);

    List<UserMeal> getAll(int ownerUserId, LocalDate startDate, LocalDate endDate);
}
