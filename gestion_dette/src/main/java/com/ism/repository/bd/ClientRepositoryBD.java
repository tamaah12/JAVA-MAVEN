package com.ism.repository.bd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.repository.ClientRepository;

public class ClientRepositoryBD extends RepositoryBDImpl<Client> implements ClientRepository {

    private List<Client> clients = new ArrayList<>();
    private List<Dette> dettes = new ArrayList<>();

    @Override
    public Client selectByTelephone(String telephone) {

        for (Client client : clients) {
            if (client.getTelephone().equals(telephone)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public void insert(Client data) {
        clients.add(data);
        
    }

    @Override
    public List<Client> selectAll() {
        return clients;
    }

    @Override
    public Client selectBySurname(String surname) {
        
        for (Client client : clients) {
            if (client.getSurname().equals(surname)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public List<Client> selectClientsWithUser() {
        return clients.stream()
                      .filter(client -> client.getUser() != null)
                      .collect(Collectors.toList());
    }

    @Override
    public List<Client> selectClientsWithoutUser() {
        return clients.stream()
                      .filter(client -> client.getUser() == null) 
                      .collect(Collectors.toList());
    }


    @Override
    public Client findById(Long id) {

        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    
    @Override
    public List<Dette> findDettesByClient(Client client) {

        return dettes.stream()
                     .filter(dette -> dette.getClient().equals(client))
                     .collect(Collectors.toList());
    }


    @Override
    public Dette findDetteById(Long id) {

        for (Dette dette : dettes) {
            if (dette.getId().equals(id)) {
                return dette;
            }
        }
        return null;
    }

    
    @Override
    public void update(Client client) {
        
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId().equals(client.getId())) {
                clients.set(i, client);
                return;
            }
        }
       
        clients.add(client);
    }


    public Client findClientByPhoneNumber(String phoneNumber) {
        for (Client client : clients) {
            if (client.getTelephone().equals(phoneNumber)) {
                return client;
            }
        }
        return null;
    }
    @Override
public Client findByPhoneNumber(String phoneNumber) {

    for (Client client : clients) {
        if (client.getTelephone().equals(phoneNumber)) {
            return client;
        }
    }
    return null;
}
}
