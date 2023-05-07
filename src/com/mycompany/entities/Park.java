/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author mhcab
 */
public class Park {
     private int idpark,nbspot;
    private String ville,nom,statut;

    public Park(int nbspot, String ville, String nom, String statut) {
        this.nbspot = nbspot;
        this.ville = ville;
        this.nom = nom;
        this.statut = statut;
    }

    public void setIdpark(int idpark) {
        this.idpark = idpark;
    }
    

    public int getIdpark() {
        return idpark;
    }

    public int getNbspot() {
        return nbspot;
    }

    public String getVille() {
        return ville;
    }

    public String getNom() {
        return nom;
    }

    public String getStatut() {
        return statut;
    }

    public void setNbspot(int nbspot) {
        this.nbspot = nbspot;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Park{" + "idpark=" + idpark + ", nbspot=" + nbspot + ", ville=" + ville + ", nom=" + nom + ", statut=" + statut + '}';
    }

    public Park() {
    }
    
}
