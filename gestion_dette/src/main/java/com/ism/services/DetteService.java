package com.ism.services;

import java.util.List;
import java.util.Scanner;

import com.ism.entities.Client;
import com.ism.entities.Dette;
import com.ism.repository.DetteRepository;

public class DetteService {

    private DetteRepository detteRepository;


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


            dette.ajouterMontantPaiement(montantPaiement);

            if (dette.isSolde()) {
                System.out.println("La dette est maintenant soldée.");
            } else {
                System.out.println("Montant restant à payer : " + (dette.getMontant() - dette.getMontantVerse()) + " €.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'entrée du montant. Assurez-vous d'entrer un nombre valide.");
        } finally {
            scanner.close();
        }
    }


    public List<Dette> findDettesByStatut(String statut) {
        return detteRepository.findByStatut(statut);
    }


    public List<Dette> findDettesByClient(Client client) {
        return detteRepository.findByClient(client);
    }
}
