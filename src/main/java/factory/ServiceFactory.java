package factory;

import org.springframework.context.ApplicationContext;
import service.*;

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

    public static BorrowService getBorrowService () {
        return (BorrowService) applicationContext.getBean("borrowService");
    }

    public static OverdueService getOverdueService() {
        return (OverdueService) applicationContext.getBean("overdueService");
    }

    public static NotifyService getNotifyService() {
        return (NotifyService) applicationContext.getBean("notifyService");
    }
}
