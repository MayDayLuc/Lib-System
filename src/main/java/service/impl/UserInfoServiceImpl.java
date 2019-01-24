package service.impl;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserInfoService;

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
}
