package com.woningzoeker.woningzoeker.dtos;

import java.sql.Date;

public class ProfielResponseDTO {

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

    //getters
    public long getId() {
        return Id;
    }
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }
    public String getNaam() {
        return naam;
    }
    public Date getGeboortedatum() {
        return geboortedatum;
    }
    public String getWoonplaats() {
        return woonplaats;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefoonnummer() {
        return telefoonnummer;
    }
    public String getOmschrijving() {
        return omschrijving;
    }
    public int getFavorieteHuizen() {
        return favorieteHuizen;
    }
    public String getInkomendeBerichten() {
        return inkomendeBerichten;
    }
    public String getUitgaandeBerichten() {
        return uitgaandeBerichten;
    }

    // setters
    public void setId(long id) {
        Id = id;
    }
    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public void setFavorieteHuizen(int favorieteHuizen) {
        this.favorieteHuizen = favorieteHuizen;
    }
    public void setInkomendeBerichten(String inkomendeBerichten) {
        this.inkomendeBerichten = inkomendeBerichten;
    }
    public void setUitgaandeBerichten(String uitgaandeBerichten) {
        this.uitgaandeBerichten = uitgaandeBerichten;
    }
}
