package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.JpaUtil;

/**
 * Created by Evgeny.Balanda on 15.04.2016.
 */
public abstract class AbstractUserServiceTestNonJdbc extends AbstractUserServiceTestJdbc {
    @Autowired
    protected JpaUtil jpaUtil;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
