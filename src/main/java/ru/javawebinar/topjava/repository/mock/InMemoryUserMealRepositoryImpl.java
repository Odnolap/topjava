package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(userMeal -> save(userMeal, userMeal.getOwnerUserId()));
    }

    @Override
    public UserMeal save(UserMeal userMeal, int ownerUserId) {
        if (userMeal.getOwnerUserId() == null || userMeal.getOwnerUserId() != ownerUserId)
            return null;

        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id, int  ownerUserId) {
        UserMeal userMealForDelete = repository.get(id);
        if (userMealForDelete != null && userMealForDelete.getOwnerUserId() == ownerUserId) {
            repository.remove(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public UserMeal get(int id, int ownerUserId) {
        UserMeal result = repository.get(id);
        if (result.getOwnerUserId() != null && result.getOwnerUserId() == ownerUserId)
            return result;
        else
            return null;
    }

    @Override
    public List<UserMeal> getAll(int ownerUserId) {
        return repository
                .values()
                .stream()
                .filter(userMeal -> userMeal.getOwnerUserId() != null && userMeal.getOwnerUserId() == ownerUserId)
                .sorted((o1, o2) -> -o1.getDateTime().compareTo(o2.getDateTime()))
                .collect(Collectors.toList());
    }
}
