/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CoVoiturage;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServiceCoVoiturage;

/**
 *
 * @author Justpro
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CoVoiturage cov = new CoVoiturage(86,5, "o", "xxxxxxxxxxxxxxxxxxxxx", "cccccccccccccccccccccc", new Timestamp(System.currentTimeMillis()) ,"on", 4, "ccc", "dddd", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 4, 5);
        try {
            ServiceCoVoiturage scov = new ServiceCoVoiturage();
            CoVoiturage cc = scov.readCoVoiturage(cov.getId());
            System.out.println(cc.toString());
        } catch (SQLException ex) {
            Logger.getLogger(Pidev.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
