/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.entities;

/**
 *
 * @author oussema
 */
public class Notification {
    private int id;
    private Matiere  matiere;
    private Documents doc;
    private String date;
    private int vu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Documents getDoc() {
        return doc;
    }

    public void setDoc(Documents doc) {
        this.doc = doc;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVu() {
        return vu;
    }

    public void setVu(int vu) {
        this.vu = vu;
    }

    public Notification() {
    }
    
}
