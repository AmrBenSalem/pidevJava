/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entities.Avis;
import entities.Event;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Liwa
 */
public interface IAvis {
    public void addAvis(int avis , int iduser,int idevent);
    public ArrayList<Avis> consulterAvis(int idevent);
    public void ajouterParticipation(int avis ,int idevent,int iduser);
    public void annulerParticipation(int idevent ,int iduser) throws SQLException;
    public Avis consulterParticipations(Event e, int iduser);
    
}
