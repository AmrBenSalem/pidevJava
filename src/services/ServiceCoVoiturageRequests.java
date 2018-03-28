/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CoVoiturage;
import entities.CoVoiturageRequests;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

/**
 *
 * @author Justpro
 */
public class ServiceCoVoiturageRequests {
    public Connection con = DataSource.getInstance().getConnection();
    public Statement st;
    
    public ServiceCoVoiturageRequests() throws SQLException{
        st = con.createStatement();
    }
    
    public void addRequest(CoVoiturageRequests cov) {
        try {
            String req = "INSERT INTO co_voiturage_requests (`idc`, `user`, `etat`, `created`) VALUES (?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1,cov.getIdc());
            pre.setInt(2,cov.getUser());
            pre.setString(3,cov.getEtat());
            pre.setTimestamp(4,cov.getCreated());
   
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void acceptRequestOffre(CoVoiturageRequests cov){
        try {
            cov.setEtat("c");
            String req = "UPDATE co_voiturage_requests SET etat = ? WHERE `id` = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1,cov.getEtat());
            pre.setInt(2,cov.getId());
            
            System.out.println(req);
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturageRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void declineRequestOffre(CoVoiturageRequests cov){
        try {
            cov.setEtat("r");
            String req = "UPDATE co_voiturage_requests SET etat = ? WHERE `id` = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1,cov.getEtat());
            pre.setInt(2,cov.getId());
            
            System.out.println(req);
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturageRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteRequestOffre(CoVoiturageRequests cov){
        try {
            String req = "DELETE FROM co_voiturage_requests WHERE `id` = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1,cov.getId());
            
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturageRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
