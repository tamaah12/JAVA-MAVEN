package com.ism.repository;

import java.util.List;

import com.ism.entities.Client;
import com.ism.entities.Dette;

public interface DetteRepository extends Repository<Dette> {
    void insert(Dette dette);
    List<Dette> selectAll();
    List<Dette> selectSoldees();  // Utiliser selectSoldees() en camelCase
    void archiveSoldes();
    void update(Dette dette);

    // Ajouter les méthodes suivantes dans l'interface
    List<Dette> findByStatut(String statut);
    List<Dette> findByClient(Client client);
}
