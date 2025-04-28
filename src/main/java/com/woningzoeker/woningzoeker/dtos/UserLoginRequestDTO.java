package com.woningzoeker.woningzoeker.dtos;

public class UserLoginRequestDTO {
    private String gebruikersnaam;
    private String wachtwoord;

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }
    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}