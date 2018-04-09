/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author bader
 */
public class User {

    private int id;
    private String userName;
    private String username_canonical;
    private Date dateNaissance;
    private String sexe;
    private String Classe;
    private String Telephone;
    private String email;
    private String password;
    private int enabled;
    private String roles;
    private String nom;
    private String prenom;

    public User(String userName, String email, String password/*, int enabled, String roles*/, String nom, String prenom) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        //this.enabled = enabled;
        // this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(String userName, Date dateNaissance, String sexe, String Classe, String Telephone, String email, String password, String nom, String prenom) {
        this.userName = userName;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.Classe = Classe;
        this.Telephone = Telephone;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(int id, String userName, String email, String password, int enabled, String nom, String prenom) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.nom = nom;
        this.prenom = prenom;
    }

 

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getClasse() {
        return Classe;
    }

    public void setClasse(String Classe) {
        this.Classe = Classe;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }
    
    

    public User(String userName, String email, String password/*, int enabled, String roles*/, String nom, String prenom, int enabled, int id) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        // this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", dateNaissance=" + dateNaissance + ", sexe=" + sexe + ", Classe=" + Classe + ", Telephone=" + Telephone + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.userName);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + this.enabled;
        hash = 67 * hash + Objects.hashCode(this.roles);
        hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + Objects.hashCode(this.prenom);
        return hash;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return true;
    }

}
