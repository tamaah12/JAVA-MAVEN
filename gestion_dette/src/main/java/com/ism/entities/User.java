package com.ism.entities;

import lombok.Data;

@Data
public class User {
    private String email;
    private String login;
    private String password;
    private boolean active;
    private String role;  // Ajout du rôle

    // Constructeur avec rôle
    public User(String email, String login, String password, String role) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.active = true;
        this.role = role;
    }

    public User() {
        this.active = true;
        this.role = "Boutiquier";  // Valeur par défaut
    }

    // Getter et setter pour le rôle
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void updatePassword(String newPassword) {
        if (newPassword == null || newPassword.length() < 6) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 6 caractères.");
        }
        this.password = newPassword;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void activer() {
        this.active = true;
    }

    public void desactiver() {
        this.active = false;
    }
}
