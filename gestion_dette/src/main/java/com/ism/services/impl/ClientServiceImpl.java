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

    // Injection de dépendance
    public ClientServiceImpl(ClientRepository clientRepository, Repository<User> userRepository) {
        this.clientRepository = clientRepository;
    }

    // Use cases client
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

    // Méthode ajoutée pour trouver un client par ID (point 1)
    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id); // Assurez-vous que cette méthode existe dans ClientRepository
    }

    // Méthode pour rechercher les dettes d'un client (point 4)
    public List<Dette> findDettesByClient(Client client) {
        return clientRepository.findDettesByClient(client); // Implémenter cette méthode dans ClientRepository si nécessaire
    }

    // Méthode pour rechercher une dette par ID (point 5)
    public Dette findDetteById(Long id) {
        return clientRepository.findDetteById(id); // Vérifiez également son existence dans ClientRepository
    }

    // Méthode ajoutée pour mettre à jour un client (point 10)
    public void updateClient(Client client) {
        clientRepository.update(client); // Assurez-vous que cette méthode est implémentée dans ClientRepository
    }
    
    @Override
    public Client getClientByPhone(String phoneNumber) {
        return clientRepository.findByPhoneNumber(phoneNumber);  // Appel à la méthode du repository
    }
}
