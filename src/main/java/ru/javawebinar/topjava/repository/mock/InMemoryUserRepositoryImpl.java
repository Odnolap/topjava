package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        repository.put(1, new User(1, "User 1 (Admin)", "admin@mysite.com", "adminpass", Role.ROLE_ADMIN, Role.ROLE_USER));
        repository.put(2, new User(2, "User 2 (Simple user)", "user@mysite.com", "123456", Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew())
            user.setId(counter.incrementAndGet());
        repository.put(user.getId(), user);

        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return new ArrayList<>(repository.values());
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);

        User result = null;
        for (User user : repository.values()){
            if (user.getEmail() == null && email == null ||
                    user.getEmail() != null && email != null && email.equals(user.getEmail())){
                result = user;
                break;
            }
        }

        return result;
    }
}
