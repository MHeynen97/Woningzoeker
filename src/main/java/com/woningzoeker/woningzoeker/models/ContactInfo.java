package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ContactInformatie")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long profielId;

    private long gebruikerId;

    private String email;

    private String telefoonnummer;

    //constructor
    public ContactInfo(long profielId, long gebruikerId, String email, String telefoonnummer){
        this.profielId = profielId;
        this.gebruikerId = gebruikerId;
        this.email = email;
        this.telefoonnummer = telefoonnummer;
    }

    public ContactInfo() {}

    //getters en setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProfielId() {
        return profielId;
    }

    public void setProfielId(long profielId) {
        this.profielId = profielId;
    }

    public long getGebruikerId() {
        return gebruikerId;
    }

    public void setGebruikerId(long gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
