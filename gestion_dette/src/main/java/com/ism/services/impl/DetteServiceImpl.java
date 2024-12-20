package com.ism.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.entities.Paiement;
import com.ism.repository.DetteRepository;
import com.ism.repository.list.DetteRepositoryList;

public class DetteServiceImpl {
    private DetteRepository detteRepository = new DetteRepositoryList();


    public void createDette(Dette dette) {
        detteRepository.insert(dette);
        System.out.println("Dette créée : " + dette);
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




    public void enregistrerPaiement(Long detteId) {
        Dette dette = findDetteById(detteId);
        if (dette == null) {
            System.out.println("Dette introuvable.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Montant actuel versé : " + dette.getMontantVerse() + " €");
            System.out.print("Entrez le montant du paiement : ");
            Double montantPaiement = scanner.nextDouble();


            Paiement paiement = new Paiement();
            paiement.setMontant(montantPaiement);


            LocalDate localDatePaiement = LocalDate.now();
            paiement.setDatePaiement(localDatePaiement);



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


    public List<Dette> findDettesByStatut(String statut) {
        return detteRepository.selectAll().stream()
            .filter(dette -> dette.getStatut().equalsIgnoreCase(statut))
            .collect(Collectors.toList());
    }


    public List<Dette> findDettesByClient(Client client) {
        return detteRepository.selectAll().stream()
            .filter(dette -> dette.getClient().equals(client))
            .collect(Collectors.toList());
    }
}
