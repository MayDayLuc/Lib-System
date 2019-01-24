package dao;

import model.User;

import java.util.List;

public interface UserDao {
    public User getUser(String id);

    public String getPassword(String id);

    public void insertUser(User user);

    public void updateUser(User user);

    public List<User> getAllUsers();
}
