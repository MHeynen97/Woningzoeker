package com.woningzoeker.woningzoeker.models;

import jakarta.persistence.*;

@Entity
@Table(name = "huis_foto")
public class HuisFoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    @ManyToOne
    @JoinColumn(name = "huis_id")
    private Huis huis;

    public HuisFoto(String fileName) {
        this.fileName = fileName;
    }

    public HuisFoto() {
    }

    // getters
    public Long getId() {
        return id;
    }
    public String getFileName() {
        return fileName;
    }
    public Huis getHuis() {
        return huis;
    }


    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setHuis(Huis huis) {
        this.huis = huis;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
