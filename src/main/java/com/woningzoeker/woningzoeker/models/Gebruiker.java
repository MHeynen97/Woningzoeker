package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "gebruikers")
public class Gebruiker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String gebruikersnaam;

    private String email;

    // Constructors, Getters, and Setters
    public Gebruiker(String gebruikersnaam, String email) {
        this.gebruikersnaam = gebruikersnaam;
        this.email = email;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
