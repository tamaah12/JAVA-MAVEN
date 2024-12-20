package com.ism.repository.list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.repository.ClientRepository;

public class ClientRepositoryList extends RepositoryListImpl<Client> implements ClientRepository {

    private List<Client> clients;  // Déclaration de la liste des clients
    private List<Dette> dettes;    // Liste des dettes, à initialiser ou récupérer

    // Constructor : Il faut initialiser la liste des clients et des dettes si nécessaire
    public ClientRepositoryList() {
        super();
        this.clients = new ArrayList<>();  // Initialisation de la liste des clients
        this.dettes = new ArrayList<>();   // Initialisation de la liste des dettes
    }

    @Override
    public Client selectByTelephone(String telephone) {
        return list.stream()
                   .filter(client -> client.getTelephone().compareTo(telephone) == 0)
                   .findFirst()
                   .orElse(null);
    }

    @Override
    public Client selectBySurname(String surname) {
        return list.stream()
                   .filter(client -> client.getSurname().compareTo(surname) == 0)
                   .findFirst()
                   .orElse(null);
    }

    @Override
    public List<Client> selectClientsWithUser() {
        return list.stream()
                   .filter(client -> client.getUser() != null)
                   .collect(Collectors.toList());
    }

    @Override
    public List<Client> selectClientsWithoutUser() {
        return list.stream()
                   .filter(client -> client.getUser() == null)
                   .collect(Collectors.toList());
    }

    @Override
    public Client findById(Long id) {
        return list.stream()
                   .filter(client -> client.getId().equals(id))
                   .findFirst()
                   .orElse(null);
    }

    @Override
    public List<Dette> findDettesByClient(Client client) {
        // Si chaque client a une liste de dettes, cela doit être géré dans l'entité Client
        return client.getDettes();  // Assurez-vous que Client a la méthode getDettes()
    }

    @Override
    public Dette findDetteById(Long id) {
        return dettes.stream()
                     .filter(dette -> dette.getId().equals(id))
                     .findFirst()
                     .orElse(null);
    }

    @Override
    public void update(Client client) {
        // Recherche du client dans la liste et mise à jour
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(client.getId())) {
                list.set(i, client);
                break;
            }
        }
    }

    // Implémentation de la méthode findByPhoneNumber
    @Override
    public Client findByPhoneNumber(String phoneNumber) {
        // Recherche du client par numéro de téléphone
        for (Client client : clients) {
            if (client.getTelephone().equals(phoneNumber)) {
                return client;  // Retourne le client trouvé
            }
        }
        return null;  // Aucun client trouvé
    }
}
