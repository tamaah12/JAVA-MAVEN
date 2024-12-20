package com.ism.repository.bd;

import java.util.ArrayList;
import java.util.List;

import com.ism.entities.Paiement;
import com.ism.repository.PaiementRepository;

public class PaiementRepositoryBD implements PaiementRepository {

    private List<Paiement> paiements = new ArrayList<>();

    @Override
    public void insert(Paiement paiement) {
        paiements.add(paiement);
    }

    @Override
    public List<Paiement> findByDette(Long detteId) {
        List<Paiement> paiementsPourDette = new ArrayList<>();
        for (Paiement paiement : paiements) {
            if (paiement.getDette().getId().equals(detteId)) {
                paiementsPourDette.add(paiement);
            }
        }
        return paiementsPourDette;
    }
}
