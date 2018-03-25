package ru.dsoccer1980.dishvote;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.repository.UserRepository;
import ru.dsoccer1980.dishvote.service.UserService;

import static ru.dsoccer1980.dishvote.UserTestData.USER;
import static ru.dsoccer1980.dishvote.UserTestData.USER_ID;
import static ru.dsoccer1980.dishvote.UserTestData.assertMatch;

@ContextConfiguration(locations = {
        "classpath:spring/test-spring-app.xml",
        "classpath:spring/test-spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    protected UserService service;

    @Autowired
    @Qualifier("inMemoryUserRepositoryImpl")
    protected UserRepository userRepository;


    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

}
