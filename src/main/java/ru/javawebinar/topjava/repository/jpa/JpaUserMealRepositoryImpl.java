package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.isNew()) {
            User userRef = em.getReference(User.class, userId);
            userMeal.setUser(userRef);
            em.persist(userMeal);
            return userMeal;
        } else {
            UserMeal oldMeal = em.getReference(UserMeal.class, userMeal.getId());
            User userFromDB = oldMeal.getUser();
            if (!userFromDB.getId().equals(userId)) {
                return null;
            } else {
                userMeal.setUser(userFromDB);
                return em.merge(userMeal);
            }
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        List<UserMeal> resultList = em.createNamedQuery(UserMeal.GET_ONE, UserMeal.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else if (resultList.size() > 1) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class)
                .setParameter("userId", userId)
                .setParameter("startDateTime", startDate)
                .setParameter("endDateTime", endDate)
                .getResultList();
    }
}