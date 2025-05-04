package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "locaties")
public class Locatie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String adres;
    private String woonplaats;
    private String postcode;

    @OneToOne(mappedBy = "locatie", cascade = CascadeType.ALL)
    private Huis huis;

    @OneToOne(mappedBy = "locatie", cascade = CascadeType.ALL)
    private Profiel profiel;

    // Constructors
    public Locatie(long id, String adres, String woonplaats, String postcode) {
        this.id = id;
        this.adres = adres;
        this.woonplaats = woonplaats;
        this.postcode = postcode;
    }

    public Locatie() {}

    // Getters en setters
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

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Huis getHuis() {
        return huis;
    }

    public void setHuis(Huis huis) {
        this.huis = huis;
    }

    public Profiel getProfiel() {
        return profiel;
    }

    public void setProfiel(Profiel profiel) {
        this.profiel = profiel;
    }
}
