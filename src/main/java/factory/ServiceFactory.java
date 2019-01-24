package factory;

import controller.mocks.LoginServiceMock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.LoginService;

public class ServiceFactory {

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static LoginService getLoginService () {
        return new LoginServiceMock();
    }
}
