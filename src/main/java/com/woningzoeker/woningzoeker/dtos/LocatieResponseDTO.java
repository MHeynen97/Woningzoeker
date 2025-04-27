package com.woningzoeker.woningzoeker.dtos;

public class LocatieResponseDTO {

    private Long id;
    private String adres;
    private String woonplaats;
    private String postcode;

    // getters
    public Long getId() {
        return id;
    }
    public String getAdres() {
        return adres;
    }
    public String getWoonplaats() {
        return woonplaats;
    }
    public String getPostcode() {
        return postcode;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
