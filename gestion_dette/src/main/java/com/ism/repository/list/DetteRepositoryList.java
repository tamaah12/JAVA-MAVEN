package com.ism.repository.list;

import java.util.ArrayList;
import java.util.List;

import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.repository.DetteRepository;

public class DetteRepositoryList implements DetteRepository {
    private List<Dette> list = new ArrayList<>();

    @Override
    public void insert(Dette dette) {
        list.add(dette);
    }

    @Override
    public List<Dette> selectAll() {
        return list;
    }

    @Override
    public List<Dette> selectSoldees() {
        return list.stream()
                .filter(Dette::isSolde)  // Assurez-vous que la méthode isSolde() existe dans la classe Dette
                .toList();
    }

    @Override
    public void archiveSoldes() {
        list.removeIf(Dette::isSolde);
    }

    @Override
    public void update(Dette dette) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(dette.getId())) {
                list.set(i, dette);
                break;
            }
        }
    }

    @Override
    public List<Dette> findByStatut(String statut) {
        return list.stream()
                .filter(dette -> dette.getStatut().equalsIgnoreCase(statut))
                .toList();
    }

    @Override
    public List<Dette> findByClient(Client client) {
        return list.stream()
                .filter(dette -> dette.getClient().equals(client))
                .toList();
    }
}
