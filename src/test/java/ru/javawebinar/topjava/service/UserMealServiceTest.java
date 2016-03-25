package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;

import static org.junit.Assert.*;

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
    public void testSave() throws Exception {
        UserMeal testUserMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 12, 0), "Запоздалый завтрак", 500);
        UserMeal created = service.save(testUserMeal, ADMIN_ID);
        testUserMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL_2, ADMIN_MEAL_1, testUserMeal), service.getAll(ADMIN_ID));
    }
}