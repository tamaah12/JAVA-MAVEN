package com.ism.services.impl;

import java.util.List;

import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.entities.User;
import com.ism.repository.ClientRepository;
import com.ism.repository.Repository;
import com.ism.services.ClientService;

public class ClientServiceImpl implements ClientService {
    
    private ClientRepository clientRepository;


    public ClientServiceImpl(ClientRepository clientRepository, Repository<User> userRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public void createClient(Client client) {
        clientRepository.insert(client);
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.selectAll();
    }

    @Override
    public Client search(String telephone) {
        return clientRepository.selectByTelephone(telephone);
    }

    @Override
    public Client searchClientBySurname(String surname) {
        return clientRepository.selectBySurname(surname);
    }

    @Override
    public List<Client> findClientsWithUser() {
        return clientRepository.selectClientsWithUser();
    }

    @Override
    public List<Client> findClientsWithoutUser() {
        return clientRepository.selectClientsWithoutUser();
    }


    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id); 
    }


    public List<Dette> findDettesByClient(Client client) {
        return clientRepository.findDettesByClient(client);
    }


    public Dette findDetteById(Long id) {
        return clientRepository.findDetteById(id);
    }


    public void updateClient(Client client) {
        clientRepository.update(client);
    }
    
    @Override
    public Client getClientByPhone(String phoneNumber) {
        return clientRepository.findByPhoneNumber(phoneNumber);
    }
}
