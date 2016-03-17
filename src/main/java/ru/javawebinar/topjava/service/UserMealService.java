package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {
    UserMeal save(UserMeal userMeal, int ownerUserId) throws NotFoundException;

    void delete(int id, int ownerUserId) throws NotFoundException;

    UserMeal get(int id, int ownerUserId) throws NotFoundException;

    List<UserMeal> getAll(int ownerUserId);

    void update(UserMeal userMeal, int ownerUserId) throws NotFoundException;
}
