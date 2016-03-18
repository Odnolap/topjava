package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public class UserMealServiceImpl implements UserMealService {

    private UserMealRepository repository; // = new InMemoryUserMealRepositoryImpl();

    public void setRepository(UserMealRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserMeal save(UserMeal userMeal, int ownerUserId) throws NotFoundException{
        UserMeal result = repository.save(userMeal, ownerUserId);

        if (result == null) throw new NotFoundException("UserMeal with this ID doesn't exist or this UserMeal doesn't own to User!");

        return result;
    }

    @Override
    public void delete(int id, int ownerUserId) throws NotFoundException {
        if (!repository.delete(id, ownerUserId))
            throw new NotFoundException("UserMeal with this ID doesn't exist or this UserMeal doesn't own to User!");
    }

    @Override
    public UserMeal get(int id, int ownerUserId) throws NotFoundException {
        UserMeal result = repository.get(id, ownerUserId);

        if (result == null) throw new NotFoundException("UserMeal with this ID doesn't exist or this UserMeal doesn't own to User!");

        return result;
    }

    @Override
    public List<UserMeal> getAll(int ownerUserId) {
        return repository.getAll(ownerUserId, null, null);
    }

    @Override
    public List<UserMeal> getAll(int ownerUserId, LocalDate dateFrom, LocalDate dateTo) {
        return repository.getAll(ownerUserId, dateFrom, dateTo);
    }
}
