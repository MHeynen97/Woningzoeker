package com.woningzoeker.woningzoeker.dtos;

import java.sql.Date;

public class BiedingResponseDTO {

    private long id;
    private long huisId;
    private int prijs;
    private long bieder; //gebruiker_id
    private long eigenaar; // gebruiker_id
    private Date eindDatum;
    private int bod;

    // getters
    public long getId() {
        return id;
    }
    public long getHuisId() {
        return huisId;
    }
    public int getPrijs() {
        return prijs;
    }
    public long getBieder() {
        return bieder;
    }
    public long getEigenaar() {
        return eigenaar;
    }
    public Date getEindDatum() {
        return eindDatum;
    }
    public int getBod() {
        return bod;
    }

    // setters
    public void setId(long id) {
        this.id = id;
    }
    public void setHuisId(long huisId) {
        this.huisId = huisId;
    }
    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }
    public void setBieder(long bieder) {
        this.bieder = bieder;
    }
    public void setEigenaar(long eigenaar) {
        this.eigenaar = eigenaar;
    }
    public void setEindDatum(Date eindDatum) {
        this.eindDatum = eindDatum;
    }
    public void setBod(int bod) {
        this.bod = bod;
    }
}
