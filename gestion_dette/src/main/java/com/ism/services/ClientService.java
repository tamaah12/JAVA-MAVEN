package com.ism.services;

import java.util.List;

import com.ism.entities.Client;
import com.ism.entities.Dette;

public interface ClientService {

    void createClient(Client client);
    List<Client> findAllClients();
    Client search(String telephone);
    Client searchClientBySurname(String surname);

    // Méthodes existantes
    List<Client> findClientsWithUser();
    List<Client> findClientsWithoutUser();

    // Méthodes manquantes ajoutées
    Client findClientById(Long id);  // Trouver un client par ID
    List<Dette> findDettesByClient(Client client);  // Trouver les dettes d'un client
    Dette findDetteById(Long id);  // Trouver une dette par ID
    void updateClient(Client client);  // Mettre à jour un client
    Client getClientByPhone(String phoneNumber);  // Méthode à implémenter
}
