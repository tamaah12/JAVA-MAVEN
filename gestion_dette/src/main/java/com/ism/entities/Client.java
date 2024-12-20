package com.ism.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Client {
    private Long id; // Assurez-vous que cet attribut existe
    private String Surname; // Nom de famille
    private String Telephone; // Numéro de téléphone
    private String Address; // Adresse
    private User user; // Relation avec un utilisateur
    private List<Dette> dettes = new ArrayList<>(); // Liste des dettes associées

    // Constructeurs
    public Client() {}

    public Client(String surname, String telephone, String address) {
        this.Surname = surname;
        this.Telephone = telephone;
        this.Address = address;
    }

    // Méthode pour obtenir le nom du client (retourne Surname ici)
    public String getName() {
        return Surname;
    }

    // Méthode pour ajouter une dette à la liste
    public void addDette(Dette dette) {
        this.dettes.add(dette);
    }

    // Autres getters et setters sont gérés par Lombok
}
