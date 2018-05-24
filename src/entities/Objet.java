/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author bader
 */
public class Objet {

    private int id;
    private int user;
    private String Type;
    private String Description;
    private Date Date;
    private String Nature;
    private String Lieu;
    private String Photo;
    private Boolean enable;

    public Objet() {
    }

    public Objet(int user, String Type, String Description, Date Date, String Nature, String Lieu, String Photo, Boolean enable) {
        
        this.user = user;
        this.Type = Type;
        this.Description = Description;
        this.Date = Date;
        this.Nature = Nature;
        this.Lieu = Lieu;
        this.Photo = Photo;
        this.enable = enable;
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
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getNature() {
        return Nature;
    }

    public void setNature(String Nature) {
        this.Nature = Nature;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Objet{" + "id=" + id + ", user=" + user + ", Type=" + Type + ", Description=" + Description + ", Date=" + Date + ", Nature=" + Nature + ", Lieu=" + Lieu + ", Photo=" + Photo + ", enable=" + enable + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
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
        final Objet other = (Objet) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
