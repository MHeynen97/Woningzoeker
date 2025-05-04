package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "contactInformatie")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "profiel_id") // de naam van de kolom die de relatie zal houden
    private Profiel profiel;

    private String email;

    private String telefoonnummer;

    // constructor
    public ContactInfo(Profiel profiel, String email, String telefoonnummer) {
        this.profiel = profiel;
        this.email = email;
        this.telefoonnummer = telefoonnummer;
    }

    public ContactInfo() {}

    // getters en setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profiel getProfiel() {
        return profiel;
    }

    public void setProfiel(Profiel profiel) {
        this.profiel = profiel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }
}
