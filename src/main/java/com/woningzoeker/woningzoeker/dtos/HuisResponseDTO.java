package com.woningzoeker.woningzoeker.dtos;

import com.woningzoeker.woningzoeker.models.Huis;

import java.util.List;

public class HuisResponseDTO {

    private long Id;
    private String adres; // verwijzing tussen deze en locatie
    private int prijs; //verwijzing tussen deze en bieding
    private int aantalKamers;
    private String energieLabel;
    private List<String> fotos; // juiste formaat? Upload en download optie.
    private String omschrijving;
    private Huis.HuurKoop huurkoop;

    // getters
    public long getId() {
        return Id;
    }
    public String getAdres() {
        return adres;
    }
    public int getPrijs() {
        return prijs;
    }
    public int getAantalKamers() {
        return aantalKamers;
    }
    public String getEnergieLabel() {
        return energieLabel;
    }
    public List<String> getFotos() {
        return fotos;
    }
    public String getOmschrijving() {
        return omschrijving;
    }
    public Huis.HuurKoop getHuurkoop() {
        return huurkoop;
    }


    // setters
    public void setId(long id) {
        Id = id;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }
    public void setAantalKamers(int aantalKamers) {
        this.aantalKamers = aantalKamers;
    }
    public void setEnergieLabel(String energieLabel) {
        this.energieLabel = energieLabel;
    }
    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public void setHuurkoop(Huis.HuurKoop huurkoop) {
        this.huurkoop = huurkoop;
    }
}
