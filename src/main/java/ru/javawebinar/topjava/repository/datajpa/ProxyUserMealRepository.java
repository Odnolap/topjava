package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Odnolap on 03.04.2016.
 */

@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal um WHERE um.id = :id AND um.user.id = :userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    UserMeal findOne(Integer integer);

    @Modifying
    @Query("SELECT m FROM UserMeal m WHERE m.id = :id AND m.user.id = :userId")
    List<UserMeal> get(@Param("id") int id, @Param("userId") int userId);

    @Modifying
    @Query("SELECT m FROM UserMeal m WHERE m.user.id = :userId ORDER BY m.dateTime DESC")
    List<UserMeal> getAll(@Param("userId") int userId);

    @Modifying
    @Query("SELECT m FROM UserMeal m WHERE m.user.id = :userId AND m.dateTime >= :startDate and m.dateTime < :endDate ORDER BY m.dateTime DESC")
    List<UserMeal> getBetween(@Param("userId") int userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Override
    @Transactional
    UserMeal save(UserMeal entity);
}
