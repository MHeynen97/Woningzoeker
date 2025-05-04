package com.woningzoeker.woningzoeker.dtos;

import java.time.LocalDateTime;

public class BerichtResponseDTO {
    private Long id;
    private String onderwerp;
    private String inhoud;
    private LocalDateTime verzondenOp;
    private Long afzenderId;
    private Long ontvangerId;

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

    public Long getAfzenderId() {
        return afzenderId;
    }

    public Long getOntvangerId() {
        return ontvangerId;
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

    public void setAfzenderId(Long afzenderId) {
        this.afzenderId = afzenderId;
    }

    public void setOntvangerId(Long ontvangerId) {
        this.ontvangerId = ontvangerId;
    }
}