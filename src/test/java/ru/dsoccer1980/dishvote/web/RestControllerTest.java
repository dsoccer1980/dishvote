package ru.dsoccer1980.dishvote.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import ru.dsoccer1980.dishvote.repository.mock.InMemoryUserRepositoryImpl;

import javax.annotation.PostConstruct;


import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.dsoccer1980.dishvote.testdata.TestUtil.userAuth;
import static ru.dsoccer1980.dishvote.testdata.UserTestData.*;

@WebAppConfiguration
@ContextConfiguration({"classpath:spring/test-spring-app.xml", "classpath:spring/test-spring-db.xml", "classpath:spring/test-spring-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RestControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    @Autowired
    private InMemoryUserRepositoryImpl repository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .apply(springSecurity())
                .build();
        repository.init();
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login")
                .with(userAuth(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage"));
    }

    @Test
    public void testRedirect() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(get("/rest/restaurant/show"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testAdminVoteShow() throws Exception {
        mockMvc.perform(get("/rest/adminVote/show")
                .with(userAuth(ADMIN)))
                .andExpect(view().name("voteForDateForm"));
    }

    @Test
    public void testUserOnForbiddenPage() throws Exception {
        mockMvc.perform(get("/rest/adminVote/show")
                .with(userAuth(USER1)))
                .andExpect(status().is4xxClientError());
    }
}
