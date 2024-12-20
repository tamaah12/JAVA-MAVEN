package com.ism.services;

import java.util.List;

import com.ism.entities.User;

public interface UserService {

    void createUser(User user);
    List<User> findAllUser();
    void activateUser(String login);
    void desactivateUser(String user);
    User findByLogin(String login);
    void associateUserWithClient(String login, String clientId);

}
