package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "huizen")
public class Huis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "locatie_id")
    private Locatie locatie;
    private int prijs;

    @OneToMany(mappedBy = "huis")
    private List<Bieding> biedingen = new ArrayList<>();//verwijzing tussen deze en bieding

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
    public Huis(int prijs, int aantalKamers, String energieLabel, String omschrijving, HuurKoop huurkoop){
        this.prijs = prijs;
        this.aantalKamers = aantalKamers;
        this.energieLabel = energieLabel;
        this.omschrijving = omschrijving;
        this.huurkoop = huurkoop;
    }
    public Huis(){
    }

    // getters
    public long getId() {
        return id;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public int getPrijs() {
        return prijs;
    }

    public List<Bieding> getBiedingen() {
        return biedingen;
    }

    public int getAantalKamers() {
        return aantalKamers;
    }

    public String getEnergieLabel() {
        return energieLabel;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public HuurKoop getHuurkoop() {
        return huurkoop;
    }

    public List<HuisFoto> getHuisFotos() {
        return huisFotos;
    }

    // setters
    public void setId(long id) {
        this.id = id;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public void setBiedingen(List<Bieding> biedingen) {
        this.biedingen = biedingen;
    }

    public void setAantalKamers(int aantalKamers) {
        this.aantalKamers = aantalKamers;
    }

    public void setEnergieLabel(String energieLabel) {
        this.energieLabel = energieLabel;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public void setHuurkoop(HuurKoop huurkoop) {
        this.huurkoop = huurkoop;
    }

    public void setHuisFotos(List<HuisFoto> huisFotos) {
        this.huisFotos = huisFotos;
    }
}
