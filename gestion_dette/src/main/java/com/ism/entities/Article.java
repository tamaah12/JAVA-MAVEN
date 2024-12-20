package com.ism.entities;

import lombok.Data;

@Data
public class Article {
    private String Libelle;
    private Double Reference;
    private int QuantiteEnStock;
    private String Description;

    public Article() {
    }

    public Article(String libelle, double reference, int quantiteEnStock, String description) {
        this.Libelle = libelle;
        this.Reference = reference;
        this.QuantiteEnStock = quantiteEnStock;
        this.Description = description;
    }

    public void mettreAJourQuantite(int quantite) {
        if (quantite < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative.");
        }
        this.QuantiteEnStock = quantite;
    }

    public boolean estDisponible() {
        return this.QuantiteEnStock > 0;
    }

    public String getNom() {
        return Libelle;
    }
}
