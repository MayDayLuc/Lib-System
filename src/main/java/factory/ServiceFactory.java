package factory;

import controller.mocks.BorrowInfoServiceMock;
import controller.mocks.LoginServiceMock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.BorrowInfoService;
import service.LoginService;

public class ServiceFactory {

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static LoginService getLoginService () {
        return new LoginServiceMock();
    }

    public static BorrowInfoService getBorrowInfoService () { return new BorrowInfoServiceMock(); }
}
