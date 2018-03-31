/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Timestamp;

/**
 *
 * @author Justpro
 */
public class CoVoiturageRequests {
    
    private int id;
    private int idc;
    private int user;
    private String etat;
    private Timestamp created;

    public CoVoiturageRequests(int idc, int user, String etat, Timestamp created) {
        this.idc = idc;
        this.user = user;
        this.etat = etat;
        this.created = created;
    }
    
    public CoVoiturageRequests(int id,int idc, int user, String etat, Timestamp created) {
        this.idc = idc;
        this.user = user;
        this.etat = etat;
        this.created = created;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
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
        final CoVoiturageRequests other = (CoVoiturageRequests) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
