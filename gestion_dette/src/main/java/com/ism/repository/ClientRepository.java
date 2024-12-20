package com.ism.repository;

import java.util.List;

import com.ism.entities.Client;
import com.ism.entities.Dette;

public interface ClientRepository extends Repository<Client> {
    Client selectByTelephone(String telephone);
    Client selectBySurname(String surname);
    List<Client> selectClientsWithUser();
    List<Client> selectClientsWithoutUser();
    
    // Méthodes ajoutées :
    Client findById(Long id);  // Trouver un client par ID
    List<Dette> findDettesByClient(Client client);  // Trouver les dettes d'un client
    Dette findDetteById(Long id);  // Trouver une dette par ID
    void update(Client client);  // Mettre à jour un client
    Client findByPhoneNumber(String phoneNumber);  // Signature de la méthode
}
