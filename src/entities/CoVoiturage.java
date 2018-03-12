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
public class CoVoiturage {
    
    private int id;
    private int user;
    private String type;
    private String depart;
    private String destination;
    private Timestamp date;
    private String onetime;
    private int placedisponibles;
    private String depart_id;
    private String destination_id;
    private Timestamp created;
    private Timestamp updated;
    private double depart_lat;
    private double depart_lng;

    public CoVoiturage(int id, int user, String type, String depart, String destination, Timestamp date, String onetime, int placedisponibles, String depart_id, String destination_id, Timestamp created, Timestamp updated, double depart_lat, double depart_lng) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.depart = depart;
        this.destination = destination;
        this.date = date;
        this.onetime = onetime;
        this.placedisponibles = placedisponibles;
        this.depart_id = depart_id;
        this.destination_id = destination_id;
        this.created = created;
        this.updated = updated;
        this.depart_lat = depart_lat;
        this.depart_lng = depart_lng;
    }

    public CoVoiturage(int user, String type, String depart, String destination, Timestamp date, String onetime, int placedisponibles, String depart_id, String destination_id, Timestamp created, Timestamp updated, double depart_lat, double depart_lng) {
        this.user = user;
        this.type = type;
        this.depart = depart;
        this.destination = destination;
        this.date = date;
        this.onetime = onetime;
        this.placedisponibles = placedisponibles;
        this.depart_id = depart_id;
        this.destination_id = destination_id;
        this.created = created;
        this.updated = updated;
        this.depart_lat = depart_lat;
        this.depart_lng = depart_lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getOnetime() {
        return onetime;
    }

    public void setOnetime(String onetime) {
        this.onetime = onetime;
    }

    public int getPlacedisponibles() {
        return placedisponibles;
    }

    public void setPlacedisponibles(int placedisponibles) {
        this.placedisponibles = placedisponibles;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(String depart_id) {
        this.depart_id = depart_id;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public double getDepart_lat() {
        return depart_lat;
    }

    public void setDepart_lat(double depart_lat) {
        this.depart_lat = depart_lat;
    }

    public double getDepart_lng() {
        return depart_lng;
    }

    public void setDepart_lng(double depart_lng) {
        this.depart_lng = depart_lng;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
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
        final CoVoiturage other = (CoVoiturage) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CoVoiturage{" + "id=" + id + ", user=" + user + ", type=" + type + ", depart=" + depart + ", destination=" + destination + ", date=" + date + ", onetime=" + onetime + ", placedisponibles=" + placedisponibles + ", depart_id=" + depart_id + ", destination_id=" + destination_id + ", created=" + created + ", updated=" + updated + ", depart_lat=" + depart_lat + ", depart_lng=" + depart_lng + '}';
    }
    

    
}
