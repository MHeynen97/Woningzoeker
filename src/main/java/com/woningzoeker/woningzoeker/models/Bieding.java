package com.woningzoeker.woningzoeker.models;

import com.woningzoeker.woningzoeker.Validaties.BodHogerDanPrijs;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

@BodHogerDanPrijs
@Entity
@Table(name = "Biedingen")
public class Bieding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private long huisId;

    @Min(0)
    private int prijs;

    @NotNull
    private long bieder; //gebruiker_id

    @NotNull
    private long eigenaar; // gebruiker_id

    private Date eindDatum;

    private int bod;

    // constructor;
    public Bieding(long huisId, int prijs, long bieder, long eigenaar, int bod, Date eindDatum){
        this.huisId = huisId;
        this.prijs = prijs;
        this.bieder = bieder;
        this.eigenaar = eigenaar;
        this.bod = bod;
        this.eindDatum = eindDatum;
    }

    public Bieding(){
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getHuisId() {
        return huisId;
    }
    public void setHuisId(long huisId) {
        this.huisId = huisId;
    }

    public int getPrijs() {
        return prijs;
    }
    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public long getBieder() {
        return bieder;
    }
    public void setBieder(long bieder) {
        this.bieder = bieder;
    }

    public long getEigenaar() {
        return eigenaar;
    }
    public void setEigenaar(long eigenaar) {
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
