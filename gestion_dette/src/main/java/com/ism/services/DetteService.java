package com.ism.services;

import java.util.List;
import java.util.Scanner;

import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.repository.DetteRepository;

public class DetteService {

    private DetteRepository detteRepository;

    // Constructor pour injecter le repository si nécessaire
    public DetteService(DetteRepository detteRepository) {
        this.detteRepository = detteRepository;
    }

    public void enregistrerPaiement(Dette dette) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Montant actuel versé : " + dette.getMontantVerse() + " €");
            System.out.print("Entrez le montant du paiement : ");
            Double montantPaiement = scanner.nextDouble();

            if (montantPaiement <= 0) {
                System.out.println("Le montant du paiement doit être positif.");
                return;
            }

            // Utilisation de la méthode ajouterMontantPaiement
            dette.ajouterMontantPaiement(montantPaiement);

            if (dette.isSolde()) {
                System.out.println("La dette est maintenant soldée.");
            } else {
                System.out.println("Montant restant à payer : " + (dette.getMontant() - dette.getMontantVerse()) + " €.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'entrée du montant. Assurez-vous d'entrer un nombre valide.");
        } finally {
            scanner.close(); // Fermeture du scanner pour éviter les fuites de ressources
        }
    }

    // Nouvelle méthode pour rechercher les dettes par statut
    public List<Dette> findDettesByStatut(String statut) {
        return detteRepository.findByStatut(statut);
    }

    // Nouvelle méthode pour rechercher les dettes d'un client
    public List<Dette> findDettesByClient(Client client) {
        return detteRepository.findByClient(client); // Appelle la méthode du dépôt
    }
}
