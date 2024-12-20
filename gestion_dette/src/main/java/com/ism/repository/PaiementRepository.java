package com.ism.repository;

import java.util.List;

import com.ism.entities.Paiement;

public interface PaiementRepository {

    void insert(Paiement paiement);

    List<Paiement> findByDette(Long detteId);
}
