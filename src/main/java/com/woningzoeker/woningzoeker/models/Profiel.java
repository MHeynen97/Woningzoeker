package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name= "profielen")
public class Profiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private Date geboortedatum;
    private String woonplaats;
    private String email; // linken aan contactInfo
    private String telefoonnummer; // linken aan contactInfo
    private String omschrijving;
    @ManyToMany
    private List<Huis> favorieteHuizen; //linken aan huisid.
    private String inkomendeBerichten;
    private String uitgaandeBerichten;

    @OneToOne
    @JoinColumn(name = "gebruiker_id")
    Gebruiker gebruiker;

    //Constructor
    public Profiel(String naam, Date geboortedatum, String woonplaats, String email, String telefoonnummer,String omschrijving, List<Huis> favorieteHuizen, String inkomendeBerichten, String uitgaandeBerichten){
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
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public List<Huis> getFavorieteHuizen() {
        return favorieteHuizen;
    }
    public void setFavorieteHuizen(List<Huis> favorieteHuizen) {
        this.favorieteHuizen = favorieteHuizen;
    }

    public String getInkomendeBerichten() {
        return inkomendeBerichten;
    }
    public void setInkomendeBerichten(String inkomendeBerichten){
        this.inkomendeBerichten = inkomendeBerichten;
    }

    public String getUitgaandeBerichten() {
        return uitgaandeBerichten;
    }
    public void setUitgaandeBerichten(String uitgaandeBerichten){
        this.uitgaandeBerichten = uitgaandeBerichten;
    }

    public Gebruiker getGebruiker(){
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }
}
