package com.ism.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dette {
    
    public static final String STATUT_NON_SOLDE = "Non soldée";
    public static final String STATUT_SOLDE = "Soldée";

    
    private Long id;
    private Double montant;
    private Double montantVerse;
    private LocalDate dateCreation;
    private LocalDate datePaiement;
    private String statut;
    private Client client;
    private List<Paiement> paiements;

    
    public Dette() {
        this.montantVerse = 0.0;
        this.statut = STATUT_NON_SOLDE;
        this.dateCreation = LocalDate.now();
        this.paiements = new ArrayList<>();
    }

    
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
        verifierStatut();
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

    
    public void ajouterPaiement(Paiement paiement) {
        if (paiement == null || paiement.getMontant() <= 0) {
            throw new IllegalArgumentException("Le paiement doit être valide avec un montant positif.");
        }
        paiements.add(paiement);
        montantVerse += paiement.getMontant();
        verifierStatut();
    }

    
    public void solder() {
        this.statut = STATUT_SOLDE;
        this.datePaiement = LocalDate.now();
    }

    
    public boolean isSolde() {
        return montantVerse >= montant;
    }

    
    private void verifierStatut() {
        if (isSolde()) {
            solder();
        } else {
            this.statut = STATUT_NON_SOLDE;
        }
    }

    
    public void ajouterMontantPaiement(Double montantPaiement) {
        if (montantPaiement == null || montantPaiement <= 0) {
            throw new IllegalArgumentException("Le montant du paiement doit être positif.");
        }
        
        Paiement paiement = new Paiement();
        paiement.setMontant(montantPaiement);
        paiement.setDatePaiement(LocalDate.now());
        paiement.setDette(this);

        paiements.add(paiement);
        this.montantVerse += montantPaiement;
        verifierStatut();
    }
}
