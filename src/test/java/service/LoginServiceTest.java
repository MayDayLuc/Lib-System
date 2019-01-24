package service;

import model.User;
import model.enums.UserType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class LoginServiceTest {

    private LoginService loginService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        loginService = (LoginService) applicationContext.getBean("loginService");
    }

    @Test
    public void login() {
        User user = loginService.login("161250105", "123");
        assertEquals(user.getType(), UserType.UNDERGRADUATE);
        assertEquals(user.getName(), "乔鑫");
        System.out.println(user.getEmail());
    }
}