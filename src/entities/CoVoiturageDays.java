/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Justpro
 */
public class CoVoiturageDays {
    
    private int id;
    private String lundi;
    private String mardi;
    private String mercredi;
    private String jeudi;
    private String vendredi;
    private String samedi;
    private int idc;

    public CoVoiturageDays(String lundi, String mardi, String mercredi, String jeudi, String vendredi, String samedi, int idc) {
        this.lundi = lundi;
        this.mardi = mardi;
        this.mercredi = mercredi;
        this.jeudi = jeudi;
        this.vendredi = vendredi;
        this.samedi = samedi;
        this.idc = idc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLundi() {
        return lundi;
    }

    public void setLundi(String lundi) {
        this.lundi = lundi;
    }

    public String getMardi() {
        return mardi;
    }

    public void setMardi(String mardi) {
        this.mardi = mardi;
    }

    public String getMercredi() {
        return mercredi;
    }

    public void setMercredi(String mercredi) {
        this.mercredi = mercredi;
    }

    public String getJeudi() {
        return jeudi;
    }

    public void setJeudi(String jeudi) {
        this.jeudi = jeudi;
    }

    public String getVendredi() {
        return vendredi;
    }

    public void setVendredi(String vendredi) {
        this.vendredi = vendredi;
    }

    public String getSamedi() {
        return samedi;
    }

    public void setSamedi(String samedi) {
        this.samedi = samedi;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
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
        final CoVoiturageDays other = (CoVoiturageDays) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
