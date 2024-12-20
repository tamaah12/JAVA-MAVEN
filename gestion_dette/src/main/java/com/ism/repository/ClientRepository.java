package com.ism.repository;

import java.util.List;

import com.ism.entities.Client;
import com.ism.entities.Dette;

public interface ClientRepository extends Repository<Client> {
    Client selectByTelephone(String telephone);
    Client selectBySurname(String surname);
    List<Client> selectClientsWithUser();
    List<Client> selectClientsWithoutUser();
    

    Client findById(Long id);
    List<Dette> findDettesByClient(Client client);
    Dette findDetteById(Long id);
    void update(Client client);
    Client findByPhoneNumber(String phoneNumber);
}
