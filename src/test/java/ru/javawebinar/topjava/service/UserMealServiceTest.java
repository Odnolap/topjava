package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    protected DbPopulator dbPopulator;


    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testSaveNew() throws Exception {
        UserMeal testUserMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 12, 0), "Запоздалый завтрак", 500);
        service.save(testUserMeal, ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL_2, ADMIN_MEAL_1, testUserMeal), service.getAll(ADMIN_ID));
//        Assert.assertEquals(Arrays.asList(ADMIN_MEAL_2, ADMIN_MEAL_1, testUserMeal), service.getAll(ADMIN_ID)); // Тут нужен метод equals у UserMeal
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updatedAdminMeal1FromDB = service.save(new UserMeal(ADMIN_MEAL_1_ID, LocalDateTime.of(2015, Month.MAY, 29, 11, 0), "Новое имя для ланча Админа", 700), ADMIN_ID);
        // Copying ADMIN_MEAL_1 - otherwise ADMIN_MEAL_1 will be changed in other tests
        UserMeal testUserMeal = new UserMeal(ADMIN_MEAL_1_ID, ADMIN_MEAL_1.getDateTime(), ADMIN_MEAL_1.getDescription(), ADMIN_MEAL_1.getCalories());
        testUserMeal.setDateTime(LocalDateTime.of(2015, Month.MAY, 29, 11, 0));
        testUserMeal.setDescription("Новое имя для ланча Админа");
        testUserMeal.setCalories(700);
        MATCHER.assertEquals(testUserMeal, updatedAdminMeal1FromDB);
        MATCHER.assertEquals(testUserMeal, service.get(ADMIN_MEAL_1_ID, ADMIN_ID));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(ADMIN_MEAL_1_ID, ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL_2), service.getAll(ADMIN_ID));
    }

    @Test
    public void testGet() throws Exception {
        UserMeal adminMeal1FromDB = service.get(ADMIN_MEAL_1_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL_1, adminMeal1FromDB);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL_2, ADMIN_MEAL_1), service.getAll(ADMIN_ID));
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        Collection<UserMeal> betweenDatesFromDB = service.getBetweenDates(LocalDate.of(2015, Month.MAY, 1), LocalDate.of(2015, Month.MAY, 30), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_3, USER_MEAL_2, USER_MEAL_1), betweenDatesFromDB);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<UserMeal> betweenDateTimesFromDB = service.getBetweenDateTimes(
                LocalDateTime.of(2015, Month.MAY, 1, 0, 0),
                LocalDateTime.of(2015, Month.MAY, 30, 23, 59, 59),
                USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_3, USER_MEAL_2, USER_MEAL_1), betweenDateTimesFromDB);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateForeign() throws Exception {
        service.save(new UserMeal(ADMIN_MEAL_1_ID, LocalDateTime.of(2015, Month.MAY, 29, 12, 0), "User изменяет еду админка", 800), USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteForeign() throws Exception {
        service.delete(ADMIN_MEAL_1_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testGetForeign() throws Exception {
        service.get(ADMIN_MEAL_1_ID, USER_ID);
    }
}