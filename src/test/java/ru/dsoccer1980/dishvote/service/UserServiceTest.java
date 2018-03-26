package ru.dsoccer1980.dishvote.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;

import java.util.List;

import static ru.dsoccer1980.dishvote.testdata.UserTestData.*;

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "newUser", "m@mail.ru", "12345");
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), USER1, USER2, ADMIN, newUser);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID1);
        assertMatch(user, USER1);
    }

    @Test
    public void getAll() throws Exception {
        List<User> userList = service.getAll();
        assertMatch(userList, USER1, USER2, ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER1);
        updated.setName("UpdatedUser");
        service.update(updated);
        assertMatch(service.get(USER_ID1), updated);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID1);
        assertMatch(service.getAll(), USER2, ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "user1@yandex.ru", "newPass"));
    }
}