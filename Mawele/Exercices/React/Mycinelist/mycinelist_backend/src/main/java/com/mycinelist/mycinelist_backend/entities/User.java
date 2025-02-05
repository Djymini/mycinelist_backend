package com.mycinelist.mycinelist_backend.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {
    private int id;

    @Email(message = "L'adresse mail rentrée n'est pas valide")
    private String mail;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Le mot de passe doit contenir minimum 8 caractères, avoir une lettre majuscule, une lettre miniscule, un chiffre et un caractère spécial")
    private String password;

    private String role;

    public User() {
    }

    public User(int id, String mail, String password, String role) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

    public User(String mail, String password, String role) {
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
