package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Gebruikers")
public class Gebruiker {


    //ToDo toevoegen van checks op velden. Bijvoorbeeld : not blank. Niet voor od na een bepaalde datum etc.
    //velden
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String gebruikersnaam;
    private String wachtwoord;

    private String rol;



    //constructor
    public Gebruiker(String gebruikersnaam, String wachtwoord, String rol) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.rol = rol;
    }

    public Gebruiker() {}

    //Getters, and Setters
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

    public String getWachtwoord() {
        return wachtwoord;
    }
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
}
