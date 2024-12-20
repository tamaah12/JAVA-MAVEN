package com.ism.services.impl;

import java.time.LocalDate;
import java.util.Scanner;

import com.ism.entities.Dette;
import com.ism.entities.Paiement;
import com.ism.repository.DetteRepository;
import com.ism.repository.list.DetteRepositoryList;

public class PaiementServiceImpl {

    private DetteRepository detteRepository = new DetteRepositoryList();


    public void enregistrerPaiement(Double montantPaiement, Long detteId) {
        Dette dette = findDetteById(detteId);
        if (dette == null) {
            System.out.println("Dette introuvable.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Montant actuel versé : " + dette.getMontantVerse() + " €");
            System.out.print("Entrez le montant du paiement : ");
            montantPaiement = scanner.nextDouble();


            LocalDate localDatePaiement = LocalDate.now();
            Paiement paiement = new Paiement(montantPaiement, localDatePaiement, dette);


            dette.ajouterPaiement(paiement);
            updateDette(dette);

            if (dette.isSolde()) {
                System.out.println("La dette est maintenant soldée.");
            } else {
                System.out.println("Montant restant à payer : " + (dette.getMontant() - dette.getMontantVerse()) + " €.");
            }
        } catch (Exception e) {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }
    }


    public Dette findDetteById(Long id) {
        return detteRepository.selectAll().stream()
            .filter(dette -> dette.getId().equals(id))
            .findFirst()
            .orElse(null);
    }


    public void updateDette(Dette dette) {
        detteRepository.update(dette);
    }
}
