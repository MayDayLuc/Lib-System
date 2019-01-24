package dao.impl;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public User getUser(String id) {
        return (User) load(User.class, "161250105");
    }

    @Override
    public String getPassword(String id) {
        return getUser(id).getPassword();
    }

    @Override
    public void updateUser(User user) {
        update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return getAllList(User.class);
    }
}
