package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal {
    private Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final Integer ownerUserId;

    public UserMeal(LocalDateTime dateTime, String description, int calories, int ownerUserId) {
        this(null, dateTime, description, calories, ownerUserId);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories, Integer ownerUserId) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.ownerUserId = ownerUserId;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", ownerUserId=" + ownerUserId +
                '}';
    }
}
