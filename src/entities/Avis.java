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
    private int iduser;
    private int idevent;
    private int avis;
    private int id;

    public Avis() {
    }

    public Avis(int id,int iduser, int idevent, int avis) {
        this.id = id;
        this.iduser = iduser;
        this.idevent = idevent;
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

    public int getAvis() {
        return avis;
    }

    public void setAvis(int avis) {
        this.avis = avis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.iduser;
        hash = 97 * hash + this.idevent;
        hash = 97 * hash + this.avis;
        hash = 97 * hash + this.id;
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
        final Avis other = (Avis) obj;
        if (this.iduser != other.iduser) {
            return false;
        }
        if (this.idevent != other.idevent) {
            return false;
        }
        if (this.avis != other.avis) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avis{" + "iduser=" + iduser + ", idevent=" + idevent + ", avis=" + avis + ", id=" + id + '}';
    }


    
    
}
