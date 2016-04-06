package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal get(int id, int userId) {
        return ExceptionUtil.check(repository.get(id, userId), id);
    }

    @Override
    @CacheEvict(value = "userMeals", allEntries = true)
    public void delete(int id, int userId) {
        ExceptionUtil.check(repository.delete(id, userId), id);
    }

    @Override
    public Collection<UserMeal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    @Cacheable("userMeals")
    public Collection<UserMeal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    @CacheEvict(value = "userMeals", allEntries = true)
    public UserMeal update(UserMeal meal, int userId) {
        return ExceptionUtil.check(repository.save(meal, userId), meal.getId());
    }

    @Override
    @CacheEvict(value = "userMeals", allEntries = true)
    public UserMeal save(UserMeal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    @CacheEvict(value = "userMeals", allEntries = true)
    public void evictCache() {
    }

    @Override
    public Collection<UserMeal> getUserWithMeal(int userId) {
        return repository.getUserWithMeal(userId);
    }

    @Override
    public UserMeal getMealWithUser(int id, int userId) throws NotFoundException {
        return repository.getMealWithUser(id, userId);
    }
}
