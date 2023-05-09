package com.mycompany.entities;


public class Badge {
    
    private int id;
    private String typebadge;
    private int nbpoint;

    public Badge() {
    }

    public Badge(int id, String typebadge, int nbpoint) {
        this.id = id;
        this.typebadge = typebadge;
        this.nbpoint = nbpoint;
    }

    public Badge(String typebadge, int nbpoint) {
        this.typebadge = typebadge;
        this.nbpoint = nbpoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypebadge() {
        return typebadge;
    }

    public void setTypebadge(String typebadge) {
        this.typebadge = typebadge;
    }

    public int getNbpoint() {
        return nbpoint;
    }

    public void setNbpoint(int nbpoint) {
        this.nbpoint = nbpoint;
    }
    
    
    
    
}
