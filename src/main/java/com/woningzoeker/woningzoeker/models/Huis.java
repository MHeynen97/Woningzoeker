package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "Huizen")
public class Huis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String adres; // verwijzing tussen deze en locatie

    private int prijs; //verwijzing tussen deze en bieding

    private int aantalKamers;

    private String energieLabel;

    private String omschrijving;

    @Enumerated(EnumType.STRING)
    private HuurKoop huurkoop;

    public enum HuurKoop{
        Huur,
        Koop
    }

    @OneToMany
    @JoinColumn(name = "huis_id")
    private List<HuisFoto> huisFotos = new ArrayList<>();

    //Constructor
    public Huis(String adres,int prijs, int aantalKamers, String energieLabel, String omschrijving, HuurKoop huurkoop){
        this.adres = adres;
        this.prijs = prijs;
        this.aantalKamers = aantalKamers;
        this.energieLabel = energieLabel;
        this.omschrijving = omschrijving;
        this.huurkoop = huurkoop;
    }
    public Huis(){
    }

    // getters en setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public String getOmschrijving() {
        return omschrijving;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public HuurKoop getHuurkoop() {
        return huurkoop;
    }
    public void setHuurkoop(HuurKoop huurkoop) {
        this.huurkoop = huurkoop;
    }

    public List<HuisFoto> getHuisFoto() {
        return huisFotos;
    }
    public void setHuisFoto(List<HuisFoto> huisFotos) {
        this.huisFotos = huisFotos;
    }
}
