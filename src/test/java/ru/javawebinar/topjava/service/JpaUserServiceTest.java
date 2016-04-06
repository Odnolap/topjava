package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Odnolap on 05.04.2016.
 */
@ActiveProfiles({Profiles.POSTGRES, Profiles.JPA})
public class JpaUserServiceTest extends UserServiceTest {
}