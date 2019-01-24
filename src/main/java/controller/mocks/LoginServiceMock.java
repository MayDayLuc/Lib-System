package controller.mocks;

import model.User;
import model.enums.UserType;
import service.LoginService;

public class LoginServiceMock implements LoginService {
    @Override
    public User login(String id, String password) {
        if (id.equals("161250105") && password.equals("123"))
            return new User(id, password, "乔鑫", UserType.UNDERGRADUATE, "12580", "34900270@qq.com");
        return null;
    }
}
