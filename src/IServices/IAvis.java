/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entities.Avis;
import entities.Event;
import java.util.ArrayList;

/**
 *
 * @author Liwa
 */
public interface IAvis {
    public void addAvis(int avis , Event e);
    public ArrayList<Avis> consulterAvis(int idevent);
    
}
