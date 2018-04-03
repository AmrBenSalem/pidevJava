/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CoVoiturage;
import entities.CoVoiturageDays;
import entities.CoVoiturageRequests;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

/**
 *
 * @author Justpro
 */
public class ServiceCoVoiturageDays {
    public Connection con = DataSource.getInstance().getConnection();
    public Statement st;
    
    public ServiceCoVoiturageDays(){
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturageDays.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addDays(CoVoiturageDays cov) {
        try {
            String req = "INSERT INTO co_voiturage_days (`lundi`, `mardi`, `mercredi`, `jeudi`, `vendredi`, `samedi`, `idc`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1,cov.getLundi());
            pre.setString(2,cov.getMardi());
            pre.setString(3,cov.getMercredi());
            pre.setString(4,cov.getJeudi());
            pre.setString(5,cov.getVendredi());
            pre.setString(6,cov.getSamedi());
            pre.setInt(7,cov.getIdc());
   
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public CoVoiturageDays GetCovoiturageDays(CoVoiturage cov) {
        try {
            String req = "SELECT * FROM `co_voiturage_days` WHERE `idc` = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, cov.getId());
            ResultSet rs = pre.executeQuery();
            ArrayList<CoVoiturageRequests> co = new ArrayList<>();
            
            rs.next();
            
            return new CoVoiturageDays(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),  rs.getString(5) ,  rs.getString(6),rs.getString(7),rs.getInt(8));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturageDays.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    void update(CoVoiturageDays cov) {
        try {
            String req = "UPDATE co_voiturage_days  SET `lundi` = ?, `mardi` = ?, `mercredi` = ?, `jeudi` = ?, `vendredi` = ?, `samedi` = ? WHERE id = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1,cov.getLundi());
            pre.setString(2,cov.getMardi());
            pre.setString(3,cov.getMercredi());
            pre.setString(4,cov.getJeudi());
            pre.setString(5,cov.getVendredi());
            pre.setString(6,cov.getSamedi());
            pre.setInt(7,cov.getId());
   
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
