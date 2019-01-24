package service;

import model.User;

public interface LoginService {
    public User login(String id, String password);
}
