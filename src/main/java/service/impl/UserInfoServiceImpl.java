package service.impl;

import dao.UserDao;
import model.User;
import model.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserInfoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void updateUserInfo(User user) {
        user.setPassword(userDao.getPassword(user.getId()));
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (User user: userDao.getAllUsers()){
            if (user.getType() == UserType.ADMINISTRATOR)
                continue;
            user.setPassword(null);
            users.add(user);
        }
        return users;
    }
}
