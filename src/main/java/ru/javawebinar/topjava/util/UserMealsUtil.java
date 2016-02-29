package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        List<UserMealWithExceed> resultList =
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000)
//        .toLocalDate();
//        .toLocalTime();
        ;

        System.out.println(resultList);
    }


    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


        // Map<LocalDate, Integer> caloriesSumPerDate;// = new HashMap<>();

        // Сбор информации о суммах колорий по дням
        /* Вариант с циклом.
        for (UserMeal userMeal : mealList){
            if (!caloriesSumPerDate.containsKey(userMeal.getDateTime().toLocalDate()))
                caloriesSumPerDate.put(userMeal.getDateTime().toLocalDate(), userMeal.getCalories());
            else
                caloriesSumPerDate.put(userMeal.getDateTime().toLocalDate(), userMeal.getCalories() + caloriesSumPerDate.get(userMeal.getDateTime().toLocalDate()));
        }
        */

        /*
        // Вариант со stream'ом 1
        mealList
                .stream()
                .forEach(um -> {
                    caloriesSumPerDate.computeIfPresent(um.getDateTime().toLocalDate(), (localDate, integer) -> integer + um.getCalories());
                    caloriesSumPerDate.putIfAbsent(um.getDateTime().toLocalDate(), um.getCalories());
                });
        */

        // Вариант со stream'ом 2
        /*
        mealList
                .stream()
                .forEach(um ->
                    caloriesSumPerDate
                            .compute(um.getDateTime().toLocalDate(), (localDate, integer) -> Objects.nonNull(integer) ? integer + um.getCalories() : um.getCalories())
                );
        */

        // Вариант со stream'ом 3
        /* Вариант актуален, если при суммировании надо что-то еще делать - фильтровать, модифицировать и т.п.
        // Тогда в Collectors.mapping мы указываем что берется в качестве базы для значения (1-й параметр), и какие манипуляции с этим проделываются при агрегации
        // Если ничего делать не надо, то лучше использовать 4-й вариант
        Map<LocalDate, Integer> caloriesSumPerDate = mealList
                .stream()
                .collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(), Collectors.mapping(UserMeal::getCalories, Collectors.summingInt(i -> i))));
        */

        // Вариант со stream'ом 4
        Map<LocalDate, Integer> caloriesSumPerDate = mealList
                .stream()
                .collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));


        // Создание отфильтрованного списка для возврата
        /* Вариант с циклом.
        List<UserMealWithExceed> result = new ArrayList<>();
        for (UserMeal userMeal : mealList){
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)){
                result.add(new UserMealWithExceed(
                        userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        caloriesSumPerDate.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        */

        // Вариант со stream'ом
        return mealList
                .stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), caloriesSumPerDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
