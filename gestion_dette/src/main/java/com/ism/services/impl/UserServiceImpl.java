package com.ism.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ism.entities.Client;
import com.ism.entities.User;
import com.ism.repository.ClientRepository;
import com.ism.repository.UserRepository;
import com.ism.services.UserService;

public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    private ClientRepository clientRepository;


    public UserServiceImpl(UserRepository userRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void createUser(User user) {

        if (userRepository.selectByLogin(user.getLogin()) != null) {
            throw new IllegalArgumentException("L'utilisateur avec ce login existe déjà.");
        }

        userRepository.insert(user);
        System.out.println("Utilisateur créé : " + user.getLogin());
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.selectAll();
    }

    @Override
    public void activateUser(String login) {
        User user = userRepository.selectByLogin(login);
        if (user != null) {
            userRepository.activateUser(login);
            System.out.println("Utilisateur activé : " + login);
        } else {
            System.out.println("Aucun utilisateur trouvé avec ce login pour l'activation : " + login);
        }
    }

    @Override
    public void desactivateUser(String login) {
        User user = userRepository.selectByLogin(login);
        if (user != null) {
            userRepository.desactivateUser(login);
            System.out.println("Utilisateur désactivé : " + login);
        } else {
            System.out.println("Aucun utilisateur trouvé avec ce login pour la désactivation : " + login);
        }
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.selectByLogin(login);
    }

    @Override
public void associateUserWithClient(String login, String clientId) {

    User user = userRepository.selectByLogin(login);
    if (user == null) {
        throw new IllegalArgumentException("L'utilisateur avec ce login n'existe pas.");
    }


    Long clientIdAsLong;
    try {
        clientIdAsLong = Long.parseLong(clientId);
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("L'ID du client doit être un nombre valide.");
    }


    Client client = clientRepository.findById(clientIdAsLong);
    if (client == null) {
        throw new IllegalArgumentException("Aucun client trouvé avec cet ID.");
    }


    if (client.getUser() != null) {
        throw new IllegalArgumentException("Ce client a déjà un compte utilisateur.");
    }


    client.setUser(user);
    clientRepository.update(client);
    System.out.println("Compte utilisateur associé au client : " + clientId);
}



public List<User> findActiveUsers() {
        return userRepository.selectAll().stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }


    public List<User> findUsersByRole(String role) {
        return userRepository.selectAll().stream()
                .filter(user -> user.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }
}
