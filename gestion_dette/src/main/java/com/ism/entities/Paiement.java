package com.ism.entities;

import java.time.LocalDate;

public class Paiement {
    private Long id;
    private Double montant;
    private LocalDate datePaiement;
    private Dette dette;

    // Constructeur par défaut
    public Paiement() {}

    // Constructeur personnalisé avec LocalDate pour la date de paiement
    public Paiement(Double montant, LocalDate date, Dette dette) {
        this.montant = montant;
        this.datePaiement = date; // Utilisation directe de LocalDate
        this.dette = dette;
    }

    // Getter et Setter
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

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    // Nouvelle méthode pour accepter un Date et le convertir en LocalDate
    public void setDatePaiement(LocalDate datePaiement) {
        if (datePaiement != null) {
            this.datePaiement = datePaiement;
        }
    }

    public Dette getDette() {
        return dette;
    }

    public void setDette(Dette dette) {
        this.dette = dette;
    }
}
