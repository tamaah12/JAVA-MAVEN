package com.ism.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dette {
    // Constantes pour les statuts
    public static final String STATUT_NON_SOLDE = "Non soldée";
    public static final String STATUT_SOLDE = "Soldée";

    // Attributs
    private Long id;
    private Double montant;
    private Double montantVerse;
    private LocalDate dateCreation;
    private LocalDate datePaiement;
    private String statut;
    private Client client;
    private List<Paiement> paiements;

    // Constructeur par défaut
    public Dette() {
        this.montantVerse = 0.0; // Initialisation du montant versé à zéro
        this.statut = STATUT_NON_SOLDE; // Statut par défaut
        this.dateCreation = LocalDate.now(); // Date de création initialisée à aujourd'hui
        this.paiements = new ArrayList<>(); // Initialisation de la liste des paiements
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getMontantVerse() {
        return montantVerse;
    }

    public void setMontantVerse(Double montantVerse) {
        this.montantVerse = montantVerse;
        verifierStatut(); // Vérification du statut après mise à jour
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

    // Méthode pour ajouter un paiement (Paiement en objet)
    public void ajouterPaiement(Paiement paiement) {
        if (paiement == null || paiement.getMontant() <= 0) {
            throw new IllegalArgumentException("Le paiement doit être valide avec un montant positif.");
        }
        paiements.add(paiement);
        montantVerse += paiement.getMontant(); // Mise à jour du montant versé
        verifierStatut(); // Vérification du statut après ajout
    }

    // Méthode pour solder la dette
    public void solder() {
        this.statut = STATUT_SOLDE; // Mise à jour du statut
        this.datePaiement = LocalDate.now(); // Enregistrement de la date de paiement
    }

    // Vérification si la dette est soldée
    public boolean isSolde() {
        return montantVerse >= montant;
    }

    // Méthode pour vérifier et mettre à jour le statut de la dette
    private void verifierStatut() {
        if (isSolde()) {
            solder();
        } else {
            this.statut = STATUT_NON_SOLDE; // Maintenir le statut si non soldée
        }
    }

    // Méthode pour ajouter un paiement avec un montant directement
    public void ajouterMontantPaiement(Double montantPaiement) {
        if (montantPaiement == null || montantPaiement <= 0) {
            throw new IllegalArgumentException("Le montant du paiement doit être positif.");
        }
        // Créer un objet Paiement pour garder un historique
        Paiement paiement = new Paiement();
        paiement.setMontant(montantPaiement);
        paiement.setDatePaiement(LocalDate.now());
        paiement.setDette(this);

        paiements.add(paiement); // Ajouter le paiement à la liste
        this.montantVerse += montantPaiement; // Mettre à jour le montant versé
        verifierStatut(); // Vérifier et mettre à jour le statut
    }
}
