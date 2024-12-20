package com.ism.repository;

import com.ism.entities.User;

public interface UserRepository extends Repository<User> {
    User selectByLogin(String login);
    void activateUser(String login);
    void desactivateUser(String login);

    
}
