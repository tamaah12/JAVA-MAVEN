package com.ism.repository.bd;

import java.util.ArrayList;
import java.util.List;

import com.ism.entities.User;
import com.ism.repository.UserRepository;

public class UserRepositoryBD extends RepositoryBDImpl<User> implements UserRepository {

    @Override
    public void insert (User data){

    }

    @Override
    public  List<User> selectAll(){
        List<User> users = new ArrayList<>();
        return users;

    }

    @Override
    public void desactivateUser(String login){

    }

    @Override
    public void activateUser(String login){

    }

    @Override
    public User selectByLogin(String login){
        User user = null;
        return user;

    }



}
