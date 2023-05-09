
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Rania2
 */
public class Vehicule {
    protected int id;
    private String img,type,status;
    protected String modele;
    protected int batterie;
    protected int puissance;
    protected String matricule;
    private String emplacement;
    private int prix;
    private int maxspeed;
    public Vehicule() {}

    public Vehicule( String matricule,int puissance, int prix) {
        this.puissance = puissance;
        this.matricule = matricule;
        this.prix = prix;
    }

    public Vehicule(String type, String modele, int batterie, int puissance, String matricule, int prix) {
        this.type = type;
        this.modele = modele;
        this.batterie = batterie;
        this.puissance = puissance;
        this.matricule = matricule;
        this.prix=prix;
    }

    public Vehicule(String type, String modele, String matricule,int batterie, int maxspeed,int prix) {
        this.type = type;
        this.modele = modele;
        this.batterie = batterie;
        this.maxspeed = maxspeed;
        this.matricule=matricule;
        this.prix=prix;
    }

    public Vehicule(String text, String text0, int parseInt, String text1, int parseInt0, String text2, int parseInt1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
    public int getBatterie() {
        return batterie;
    }
    public void setBatterie(int batterie) {
        this.batterie = batterie;
    }

    public String getEmplacement() {return emplacement;}
    public void setEmplacement(String emplacement) {this.emplacement = emplacement;}
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getPrix() {return prix;}
    public void setPrix(int prix) {this.prix = prix;}
   
    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    public int getPuissance() {
        return puissance;
    }
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }
    public int getMaxspeed() {
        return maxspeed;
    }
    public void setMaxspeed(int maxspeed) {
        this.maxspeed = maxspeed;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
            return "matricule:" + matricule + ", type:" + type+ ", puissance:" + puissance +
                    ", modele:" + modele + ", batterie:" + batterie + ", prix:" + prix + ", location:" + emplacement + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicule v = (Vehicule) o;
        return matricule == v.matricule && puissance == v.puissance;
    }
    
}
