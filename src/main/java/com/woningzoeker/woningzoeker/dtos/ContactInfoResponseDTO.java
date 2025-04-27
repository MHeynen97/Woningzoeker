package com.woningzoeker.woningzoeker.dtos;

public class ContactInfoResponseDTO {

    private long id;
    private long profielId;
    private long gebruikerId;
    private String email;
    private String telefoonnummer;

    // getters
    public long getId() {
        return id;
    }
    public long getProfielId() {
        return profielId;
    }
    public long getGebruikerId() {
        return gebruikerId;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefoonnummer() {
        return telefoonnummer;
    }


    // setters

    public void setId(long id) {
        this.id = id;
    }
    public void setProfielId(long profielId) {
        this.profielId = profielId;
    }
    public void setGebruikerId(long gebruikerId) {
        this.gebruikerId = gebruikerId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }
}
