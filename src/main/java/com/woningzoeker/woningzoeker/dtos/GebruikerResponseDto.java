package com.woningzoeker.woningzoeker.dtos;

// wordt het model wat je naar buiten wilt sturen
public class GebruikerResponseDto {

    private Long id;
    private String gebruikersnaam;
    private String wachtwoord;
    private String rol;


    // getters
    public Long getId() {
        return id;
    }
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }
    public String getWachtwoord() {
        return wachtwoord;
    }
    public String getRol() {
        return rol;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
}
