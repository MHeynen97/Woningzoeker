package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name= "profielen")
public class Profiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String gebruikersnaam; // linken aan gebruiker
    private String naam;
    private Date geboortedatum;
    private String woonplaats;
    private String email; // linken aan contactInfo
    private String telefoonnummer; // linken aan contactInfo
    private String omschrijving;
    private int favorieteHuizen; //linken aan huisId.
    private String inkomendeBerichten;
    private String uitgaandeBerichten;

    //Constructor
    public Profiel(String gebruikersnaam, String naam, Date geboortedatum, String woonplaats, String email, String telefoonnummer,String omschrijving, int favorieteHuizen, String inkomendeBerichten, String uitgaandeBerichten){
        this.gebruikersnaam = gebruikersnaam;
        this.naam = naam;
        this.geboortedatum = geboortedatum;
        this.woonplaats = woonplaats;
        this.email = email;
        this.telefoonnummer = telefoonnummer;
        this.omschrijving = omschrijving;
        this.favorieteHuizen = favorieteHuizen;
        this.inkomendeBerichten = inkomendeBerichten;
        this.uitgaandeBerichten = uitgaandeBerichten;
    }
    public Profiel() {
    }

    //getters en setters
    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }
    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }
    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getWoonplaats() {
        return woonplaats;
    }
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }
    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public String getOmschrijving() {
        return omschrijving;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public int getFavorieteHuizen() {
        return favorieteHuizen;
    }
    public void setFavorieteHuizen(int favorieteHuizen) {
        this.favorieteHuizen = favorieteHuizen;
    }

    public String getInkomendeBerichten() {
        return inkomendeBerichten;
    }
    public void setInkomendeBerichten(String inkomendeBerichten){
        this.inkomendeBerichten = this.inkomendeBerichten;
    }

    public String getUitgaandeBerichten() {
        return uitgaandeBerichten;
    }
    public void setUitgaandeBerichten(String uitgaandeBerichten){
        this.uitgaandeBerichten = this.uitgaandeBerichten;
    }
}
