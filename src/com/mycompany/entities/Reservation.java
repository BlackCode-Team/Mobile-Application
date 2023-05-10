
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author ychaa
 */
public class Reservation {
        private int idreservation;
    private Date datedebut;
    private Date datefin;
    private int idvehicule;
    private int iduser;
    private int iditineraire;
   // private String pointarrivee;
    private String Status;
  //  public  Utilisateur utilisateur;
//    private EtatReservation status;
////    private String cin;
////    private String matricule;
    private float prixreservation ;

    public Reservation() {
    }

    public Reservation(float prixreservation) {
        this.prixreservation = prixreservation;
    }

    public Reservation(int idreservation, Date datedebut, Date datefin, int idvehicule, int iduser, int iditineraire, String Status, float prixreservation) {
        this.idreservation = idreservation;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.idvehicule = idvehicule;
        this.iduser = iduser;
        this.iditineraire = iditineraire;
        this.Status = Status;
        this.prixreservation = prixreservation;
    }

    public Reservation(Date datedebut, Date datefin, int idvehicule, int iduser, int iditineraire, String Status, float prixreservation) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.idvehicule = idvehicule;
        this.iduser = iduser;
        this.iditineraire = iditineraire;
        this.Status = Status;
        this.prixreservation = prixreservation;
    }


  

    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public int getIdvehicule() {
        return idvehicule;
    }

    public void setIdvehicule(int idvehicule) {
        this.idvehicule = idvehicule;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIditineraire() {
        return iditineraire;
    }

    public void setIditineraire(int iditineraire) {
        this.iditineraire = iditineraire;
    }

//    public String getPointarrivee() {
//        return pointarrivee;
//    }
//
//    public void setPointarrivee(String pointarrivee) {
//        this.pointarrivee = pointarrivee;
//    }

  //  public Utilisateur getUtilisateur() {
  //      return utilisateur;
  //  }

 //   public void setUtilisateur(Utilisateur utilisateur) {
  //      this.utilisateur = utilisateur;
  //  }

    public float getPrixreservation() {
        return prixreservation;
    }

    public void setPrixreservation(float prixreservation) {
        this.prixreservation = prixreservation;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idreservation=" + idreservation + ", datedebut=" + datedebut + ", datefin=" + datefin + ", idvehicule=" + idvehicule + ", iduser=" + iduser + ", iditineraire=" + iditineraire + ", Status=" + Status + ", prixreservation=" + prixreservation + '}';
    }

    
    
    
}
