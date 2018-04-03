/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 *
 * @author Justpro
 */
public class CoVoiturageSuggestion implements Comparator<CoVoiturageSuggestion> {
    int id;
    String username;
    int idUser;
    String depart;
    String destination;
    double value;
    Timestamp updated;

    public CoVoiturageSuggestion(int id, String username, int idUser, String depart, String destination, double value,Timestamp updated) {
        this.id = id;
        this.username = username;
        this.idUser = idUser;
        this.depart = depart;
        this.destination = destination;
        this.value = value;
        this.updated = updated;
    }

    public CoVoiturageSuggestion() {
    }
    
    @Override
   public int compare(CoVoiturageSuggestion p1, CoVoiturageSuggestion p2) {
        if (p1.getValue()< p2.getValue()) return -1;
        if (p1.getValue() > p2.getValue()) return 1;
        return 0;
    }  

    @Override
    public String toString() {
        return "CoVoiturageSuggestion{" + "id=" + id + ", username=" + username + ", idUser=" + idUser + ", depart=" + depart + ", destination=" + destination + ", value=" + value + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
    
    
    
}
