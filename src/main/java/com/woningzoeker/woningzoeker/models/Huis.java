package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table(name= "Huizen")
public class Huis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String adres; // verwijzing tussen deze en locatie

    private int prijs; //verwijzing tussen deze en bieding

    private int aantalKamers;

    private String energieLabel;

    private String fotos; // juiste formaat? Upload en download optie.

    private String omschrijving;

   // private Enum<huurKoop>{


    //Constructor
    public Huis(String adres,int prijs, int aantalKamers, String energieLabel, String fotos, String omschrijving){
        this.adres = adres;
        this.prijs = prijs;
        this.aantalKamers = aantalKamers;
        this.energieLabel = energieLabel;
        this.fotos = fotos;
        this.omschrijving = omschrijving;
        // Enum
    }
    public Huis(){
    }

    // getters en setters
    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }

    public String getAdres() {
        return adres;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getPrijs() {
        return prijs;
    }
    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public int getAantalKamers() {
        return aantalKamers;
    }
    public void setAantalKamers(int aantalKamers) {
        this.aantalKamers = aantalKamers;
    }

    public String getEnergieLabel() {
        return energieLabel;
    }
    public void setEnergieLabel(String energieLabel) {
        this.energieLabel = energieLabel;
    }

    public String getFotos() {
        return fotos;
    }
    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public String getOmschrijving() {
        return omschrijving;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
}
