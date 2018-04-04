/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Liwa
 */
public class Avis {
    int id;
    int avis;
    int iduser;
    int idevent;

    public Avis() {
    }

    public Avis(int id, int avis, int iduser, int idevent) {
        this.id = id;
        this.avis = avis;
        this.iduser = iduser;
        this.idevent = idevent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvis() {
        return avis;
    }

    public void setAvis(int avis) {
        this.avis = avis;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }
    
    
}
