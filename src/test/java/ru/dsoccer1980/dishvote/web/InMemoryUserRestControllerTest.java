package ru.dsoccer1980.dishvote.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.repository.mock.InMemoryUserRepositoryImpl;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;
import ru.dsoccer1980.dishvote.web.user.UserRestController;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.dsoccer1980.dishvote.testdata.TestUtil.userAuth;
import static ru.dsoccer1980.dishvote.testdata.UserTestData.*;

@ContextConfiguration({"classpath:spring/test-spring-app.xml", "classpath:spring/test-spring-db.xml"})
@RunWith(SpringRunner.class)
public class InMemoryUserRestControllerTest {

    @Autowired
    private UserRestController userRestController;

    @Autowired
    private RootController controller;

    @Autowired
    private InMemoryUserRepositoryImpl repository;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        repository.init();
    }
//
//    @Test
//    public void testDelete() throws Exception {
//        controller.delete(USER_ID1);
//        List<User> users = controller.getAll();
//        Assert.assertEquals(users.size(), 1);
//        Assert.assertEquals(users.iterator().next(), ADMIN);
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void testDeleteNotFound() throws Exception {
//        controller.delete(10);
//    }
//
    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(view().name("loginPage"))
//                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
//        User user = controller.get(USER_ID1);
//        assertMatch(user, USER1);
    }
//
//    @Test
//    public void testCreate() {
//        User newUser = new User(null, "newUser", "new@mail.ru", "password");
//        controller.create(newUser);
//        assertMatch(controller.getAll(), USER1, ADMIN, newUser);
//    }
//
//    @Test
//    public void testUpdate() {
//        User updateUser = new User(USER1);
//        updateUser.setName("new Name");
//        controller.update(updateUser);
//        assertMatch(controller.getAll(), updateUser, ADMIN);
//    }

}
