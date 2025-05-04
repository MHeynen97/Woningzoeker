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

    @OneToOne
    @JoinColumn(name = "locatie_id")
    private Locatie locatie;

    @OneToOne(mappedBy = "profiel", cascade = CascadeType.ALL)
    private ContactInfo contactInfo;

    private String omschrijving;

    @ManyToMany
    private List<Huis> favorieteHuizen; //linken aan huisid.

    @OneToOne
    @JoinColumn(name = "gebruiker_id")
    Gebruiker gebruiker;

    //Constructor
    public Profiel(String naam, Date geboortedatum, String omschrijving, Locatie locatie, ContactInfo contactInfo, List<Huis> favorieteHuizen, Gebruiker gebruiker) {
        this.naam = naam;
        this.geboortedatum = geboortedatum;
        this.omschrijving = omschrijving;
        this.locatie = locatie;
        this.contactInfo = contactInfo;
        this.favorieteHuizen = favorieteHuizen;
        this.gebruiker = gebruiker;
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

    public Locatie getLocatie() { return locatie; }
    public void setLocatie(Locatie locatie) { this.locatie = locatie; }

    public ContactInfo getContactInfo() { return contactInfo; }
    public void setContactInfo(ContactInfo contactInfo) { this.contactInfo = contactInfo; }

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

    public Gebruiker getGebruiker(){
        return gebruiker;
    }
    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }
}
