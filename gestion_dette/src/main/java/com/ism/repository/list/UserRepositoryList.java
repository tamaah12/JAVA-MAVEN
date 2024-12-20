package com.ism.repository.list;


import com.ism.entities.User;

public class UserRepositoryList extends RepositoryListImpl<User> {

    public User selectByLogin(String login) {
        return list.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }
    
public void activateUser(String login) {
    User user = selectByLogin(login);
    if (user != null){
        user.activer();
    }
}

public void desactivateUser(String login){
    User user = selectByLogin(login);
    if (user != null){
        user.desactiver();
    }
}
}
