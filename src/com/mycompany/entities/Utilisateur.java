/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Jokser
 */
public class Utilisateur {
    private int iduser ;
    private String nom;
    private String prenom;
    private String pwd ;
    private String cin ;
    private String permis ;
    private int nbpoint;
    private int is_blocked;
    
    private String reset_token ;
    private String auth_code ;
    private String email ;
   
  
    

    public Utilisateur() {
    }

    public Utilisateur(int iduser, String text, String text0, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
 
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public int getNbpoint() {
        return nbpoint;
    }

    public void setNbpoint(int nbpoint) {
        this.nbpoint = nbpoint;
    }

    public int getIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(int is_blocked) {
        this.is_blocked = is_blocked;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Utilisateur(int iduser, String nom, String prenom, String pwd, String cin, String permis, int nbpoint, int is_blocked, String password, String reset_token, String auth_code, String email) {
        this.iduser = iduser;
        this.nom = nom;
        this.prenom = prenom;
        this.pwd = pwd;
        this.cin = cin;
        this.permis = permis;
        this.nbpoint = nbpoint;
        this.is_blocked = is_blocked;
       
        this.reset_token = reset_token;
        this.auth_code = auth_code;
        this.email = email;
    }

  

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (this.iduser != other.iduser) {
            return false;
        }
        return true;
    }

    public Utilisateur(String nom, String pwd, String email) {
        this.nom = nom;
        this.pwd = pwd;
        this.email = email;
    }
    
    

}
