package controller.mocks;

import model.User;
import model.enums.UserType;
import service.LoginService;

public class LoginServiceMock implements LoginService {
    @Override
    public User login(String id, String password) {
        if (id.equals("161250105") && password.equals("123"))
            return new User(id, "乔鑫", UserType.UNDERGRADUATE, "12580", "34900270@qq.com");
        if (id.equals("admin") && password.equals("admin"))
            return new User(id, "管理员乔鑫", UserType.ADMINISTRATOR);
        return null;
    }
}
