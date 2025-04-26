package com.woningzoeker.woningzoeker.dtos;

// wordt het model wat je naar buiten wilt sturen
public class GebruikerResponseDto {

    private Long id;

    private String gebruikersnaam;

    private String email;


    // getters
    public Long getId() {
        return id;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getEmail() {
        return email;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
