package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "biedingen")
public class Bieding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne // Verbinding naar het Huis
    @JoinColumn(name = "huis_id", nullable = false)
    private Huis huis; // Verander dit naar een Huis object in plaats van huisId

    private int prijs;

    @ManyToOne // Verbindt de Bieder en Eigenaar als Gebruikers
    @JoinColumn(name = "bieder_id", nullable = false)
    private Gebruiker bieder;

    @ManyToOne
    @JoinColumn(name = "eigenaar_id", nullable = false)
    private Gebruiker eigenaar;

    private Date eindDatum;
    private int bod;

    // Constructor
    public Bieding(Huis huis, int prijs, Gebruiker bieder, Gebruiker eigenaar, int bod, Date eindDatum) {
        this.huis = huis;
        this.prijs = prijs;
        this.bieder = bieder;
        this.eigenaar = eigenaar;
        this.bod = bod;
        this.eindDatum = eindDatum;
    }

    public Bieding() {}

    // Getters en Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Huis getHuis() {
        return huis;
    }
    public void setHuis(Huis huis) {
        this.huis = huis;
    }

    public int getPrijs() {
        return prijs;
    }
    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public Gebruiker getBieder() {
        return bieder;
    }
    public void setBieder(Gebruiker bieder) {
        this.bieder = bieder;
    }

    public Gebruiker getEigenaar() {
        return eigenaar;
    }
    public void setEigenaar(Gebruiker eigenaar) {
        this.eigenaar = eigenaar;
    }

    public Date getEindDatum() {
        return eindDatum;
    }
    public void setEindDatum(Date eindDatum) {
        this.eindDatum = eindDatum;
    }

    public int getBod() {
        return bod;
    }
    public void setBod(int bod) {
        this.bod = bod;
    }
}
