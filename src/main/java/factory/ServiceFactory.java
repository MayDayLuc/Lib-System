package factory;

import controller.mocks.BorrowInfoServiceMock;
import controller.mocks.LoginServiceMock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.BookInfoService;
import service.BorrowInfoService;
import service.LoginService;
import service.UserInfoService;

public class ServiceFactory {

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ServiceFactory.applicationContext = applicationContext;
    }

    private static ApplicationContext applicationContext;

    public static LoginService getLoginService () {
        return (LoginService) applicationContext.getBean("loginService");
    }

    public static UserInfoService getUserInfoService () {
        return (UserInfoService) applicationContext.getBean("userInfoService");
    }

    public static BorrowInfoService getBorrowInfoService () {
        return (BorrowInfoService) applicationContext.getBean("borrowInfoService");
    }
    public static BookInfoService getBookInfoService (){
        return (BookInfoService) applicationContext.getBean("bookInfoService");
    }
}
