package dao;

import model.User;

public interface UserDao {
    public User getUser(String id);

    public String getPassword(String id);

    public void updateUser(User user);
}
