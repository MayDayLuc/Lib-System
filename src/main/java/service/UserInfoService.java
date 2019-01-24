package service;

import model.User;

import java.util.List;

public interface UserInfoService {
    public void updateUserInfo(User user);

    public List<User> getAllUsers();
}
