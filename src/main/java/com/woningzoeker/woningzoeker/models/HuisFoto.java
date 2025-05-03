package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HuisFoto {
    @Id
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "huis_id")
    private Huis huis;

    public HuisFoto(String fileName) {
        this.fileName = fileName;
    }

    public HuisFoto() {
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Huis getHuis() {
        return huis;
    }
    public void setHuis(Huis huis) {
        this.huis = huis;
    }
}
