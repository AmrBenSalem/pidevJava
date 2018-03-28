package entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author douha
 */
public class Colocation {
    int id;
    float loyer;
    String titre;
    String type;
    String meuble;
    String description;
   String photo;
    String photo1;
    String photo2;
    String ville;
    double x;
    double y;
    String nature;
    int user_id;
    int enable;
    

    public Colocation() {
    }

    public Colocation(float loyer, String titre, String type, String meuble, String description, String photo, String photo1, String photo2, String ville, int x, int y, String nature, int user_id, int enable) {
        this.loyer = loyer;
        this.titre = titre;
        this.type = type;
        this.meuble = meuble;
        this.description = description;
        this.photo = photo;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.ville = ville;
        this.x = x;
        this.y = y;
        this.nature = nature;
        this.user_id = user_id;
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLoyer() {
        return loyer;
    }

    public void setLoyer(float loyer) {
        this.loyer = loyer;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeuble() {
        return meuble;
    }

    public void setMeuble(String meuble) {
        this.meuble = meuble;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    

    public void setY(int y) {
        this.y = y;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
    
            
            

    
    
}
