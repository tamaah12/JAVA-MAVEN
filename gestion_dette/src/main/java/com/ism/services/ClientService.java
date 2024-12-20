package com.ism.services;

import java.util.List;

import com.ism.entities.Client;
import com.ism.entities.Dette;

public interface ClientService {

    void createClient(Client client);
    List<Client> findAllClients();
    Client search(String telephone);
    Client searchClientBySurname(String surname);


    List<Client> findClientsWithUser();
    List<Client> findClientsWithoutUser();


    Client findClientById(Long id);
    List<Dette> findDettesByClient(Client client);
    Dette findDetteById(Long id);
    void updateClient(Client client);
    Client getClientByPhone(String phoneNumber);
}
