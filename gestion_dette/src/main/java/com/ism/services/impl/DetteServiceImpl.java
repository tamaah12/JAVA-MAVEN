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
    private DetteRepository detteRepository = new DetteRepositoryList(); // Dépendance à l'interface

    // --- Méthodes existantes ---
    public void createDette(Dette dette) {
        detteRepository.insert(dette); // Utilise insert à la place de save
        System.out.println("Dette créée : " + dette);
    }

    // Trouver une dette par ID
    public Dette findDetteById(Long id) {
        return detteRepository.selectAll().stream()
            .filter(dette -> dette.getId().equals(id))
            .findFirst()
            .orElse(null); // Retourne null si aucune dette n'est trouvée
    }

    // Mise à jour d'une dette
    public void updateDette(Dette dette) {
        detteRepository.update(dette); // Utilise update pour la mise à jour
    }

    // --- Nouvelles méthodes ajoutées ---

    // Méthode pour enregistrer un paiement sur une dette
    public void enregistrerPaiement(Long detteId) {
        Dette dette = findDetteById(detteId);
        if (dette == null) {
            System.out.println("Dette introuvable.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) { // Utilisation de try-with-resources pour garantir la fermeture
            System.out.println("Montant actuel versé : " + dette.getMontantVerse() + " €");
            System.out.print("Entrez le montant du paiement : ");
            Double montantPaiement = scanner.nextDouble();

            // Créer un nouvel objet Paiement
            Paiement paiement = new Paiement();
            paiement.setMontant(montantPaiement);

            // Utilisation de LocalDate directement (sans conversion Date -> LocalDate)
            LocalDate localDatePaiement = LocalDate.now(); // Utilisation de LocalDate
            paiement.setDatePaiement(localDatePaiement); // Passer un LocalDate directement

            // Ajouter le paiement à la dette
            dette.ajouterPaiement(paiement);
            updateDette(dette); // Mise à jour de la dette dans le repository

            if (dette.isSolde()) {
                System.out.println("La dette est maintenant soldée.");
            } else {
                System.out.println("Montant restant à payer : " + (dette.getMontant() - dette.getMontantVerse()) + " €.");
            }
        } catch (Exception e) {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }
    }

    // Méthode pour rechercher les dettes par statut (par exemple, "En Cours", "Annulé")
    public List<Dette> findDettesByStatut(String statut) {
        return detteRepository.selectAll().stream()
            .filter(dette -> dette.getStatut().equalsIgnoreCase(statut))
            .collect(Collectors.toList());
    }

    // --- Méthode pour trouver les dettes d'un client donné ---
    public List<Dette> findDettesByClient(Client client) {
        return detteRepository.selectAll().stream()
            .filter(dette -> dette.getClient().equals(client)) // Vérifie si la dette appartient au client
            .collect(Collectors.toList());
    }
}
