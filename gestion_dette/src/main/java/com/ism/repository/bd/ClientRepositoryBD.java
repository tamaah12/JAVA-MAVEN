package com.ism.repository.bd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.repository.ClientRepository;

public class ClientRepositoryBD extends RepositoryBDImpl<Client> implements ClientRepository {

    private List<Client> clients = new ArrayList<>(); // Liste des clients simulée
    private List<Dette> dettes = new ArrayList<>();   // Liste des dettes simulée

    @Override
    public Client selectByTelephone(String telephone) {
        // Recherche d'un client par téléphone
        for (Client client : clients) {
            if (client.getTelephone().equals(telephone)) {
                return client;
            }
        }
        return null; // Aucun client trouvé
    }

    @Override
    public void insert(Client data) {
        clients.add(data); // Ajout du client à la liste
    }

    @Override
    public List<Client> selectAll() {
        return clients; // Retourne tous les clients
    }

    @Override
    public Client selectBySurname(String surname) {
        // Recherche d'un client par surnom
        for (Client client : clients) {
            if (client.getSurname().equals(surname)) {
                return client;
            }
        }
        return null; // Aucun client trouvé
    }

    @Override
    public List<Client> selectClientsWithUser() {
        return clients.stream()
                      .filter(client -> client.getUser() != null) // Filtrer les clients ayant un utilisateur associé
                      .collect(Collectors.toList());
    }

    @Override
    public List<Client> selectClientsWithoutUser() {
        return clients.stream()
                      .filter(client -> client.getUser() == null) // Filtrer les clients sans utilisateur associé
                      .collect(Collectors.toList());
    }

    // Implémentation de la méthode findById
    @Override
    public Client findById(Long id) {
        // Recherche d'un client par ID
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null; // Aucun client trouvé
    }

    // Implémentation de la méthode findDettesByClient
    @Override
    public List<Dette> findDettesByClient(Client client) {
        // Filtre les dettes associées au client
        return dettes.stream()
                     .filter(dette -> dette.getClient().equals(client)) // Filtrer les dettes du client
                     .collect(Collectors.toList());
    }

    // Implémentation de la méthode findDetteById
    @Override
    public Dette findDetteById(Long id) {
        // Recherche d'une dette par ID
        for (Dette dette : dettes) {
            if (dette.getId().equals(id)) {
                return dette;
            }
        }
        return null; // Aucune dette trouvée
    }

    // Implémentation de la méthode update
    @Override
    public void update(Client client) {
        // Mise à jour du client (si un client avec le même ID existe, on le remplace)
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId().equals(client.getId())) {
                clients.set(i, client); // Remplace le client existant par le nouveau client
                return;
            }
        }
        // Si aucun client avec cet ID n'existe, on l'ajoute
        clients.add(client);
    }

    // Fonctionnalité supplémentaire : Rechercher un client par téléphone
    public Client findClientByPhoneNumber(String phoneNumber) {
        for (Client client : clients) {
            if (client.getTelephone().equals(phoneNumber)) {
                return client;
            }
        }
        return null;  // Aucun client trouvé avec ce numéro
    }
    @Override
public Client findByPhoneNumber(String phoneNumber) {
    // Recherche du client par numéro de téléphone
    for (Client client : clients) {
        if (client.getTelephone().equals(phoneNumber)) {
            return client;
        }
    }
    return null;  // Aucun client trouvé
}
}
