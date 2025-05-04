package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "berichten")
public class Bericht {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String onderwerp;
    private String inhoud;
    private LocalDateTime verzondenOp;

    @ManyToOne
    @JoinColumn(name = "afzender_id")
    private Gebruiker afzender;

    @ManyToOne
    @JoinColumn(name = "ontvanger_id")
    private Gebruiker ontvanger;

    public Bericht() {
    }

    public Bericht(String onderwerp, String inhoud, LocalDateTime verzondenOp, Gebruiker afzender, Gebruiker ontvanger) {
        this.onderwerp = onderwerp;
        this.inhoud = inhoud;
        this.verzondenOp = verzondenOp;
        this.afzender = afzender;
        this.ontvanger = ontvanger;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getOnderwerp() {
        return onderwerp;
    }

    public String getInhoud() {
        return inhoud;
    }

    public LocalDateTime getVerzondenOp() {
        return verzondenOp;
    }

    public Gebruiker getAfzender() {
        return afzender;
    }

    public Gebruiker getOntvanger() {
        return ontvanger;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setOnderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public void setVerzondenOp(LocalDateTime verzondenOp) {
        this.verzondenOp = verzondenOp;
    }

    public void setAfzender(Gebruiker afzender) {
        this.afzender = afzender;
    }

    public void setOntvanger(Gebruiker ontvanger) {
        this.ontvanger = ontvanger;
    }
}
