/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author bader
 */
public class Traitafter {
    private int id;
    private int user;
    private String statut;
    private int interaction;

    public Traitafter() {
    }

    public Traitafter(int user, String statut, int interaction) {
        this.user = user;
        this.statut = statut;
        this.interaction = interaction;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getInteraction() {
        return interaction;
    }

    public void setInteraction(int interaction) {
        this.interaction = interaction;
    }

    @Override
    public String toString() {
        return "Traitafter{" + "id=" + id + ", user=" + user + ", statut=" + statut + ", interaction=" + interaction + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
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
        final Traitafter other = (Traitafter) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
