/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CoVoiturageDays;
import entities.CoVoiturageRequests;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    public ServiceCoVoiturageDays() throws SQLException{
        st = con.createStatement();
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
    
}
