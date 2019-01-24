package dao.impl;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Repository;

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
}
