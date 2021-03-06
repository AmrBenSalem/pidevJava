/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CoVoiturage;
import entities.CoVoiturageRequests;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    ServiceCoVoiturage scov = new ServiceCoVoiturage();
    
    public ServiceCoVoiturageRequests(){
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturageRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            if (scov.readCoVoiturage(cov.getIdc()).getType().equals("o") &&  scov.hasPlaceDisponible(cov.getIdc())){
                CoVoiturage c = scov.readCoVoiturage(cov.getIdc());
                c.setPlacedisponibles(c.getPlacedisponibles()-1);
                scov.updateCoVoiturage(c);
            }
            String req = "UPDATE co_voiturage_requests SET etat = ? WHERE `id` = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1,"c");
            System.out.println(cov.getId());
            pre.setInt(2,cov.getId());
            pre.executeUpdate();
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
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturageRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteRequestOffre(CoVoiturageRequests cov){
        try {
            if (scov.readCoVoiturage(cov.getIdc()).getType().equals("o") ){
                CoVoiturage c = scov.readCoVoiturage(cov.getIdc());
                c.setPlacedisponibles(c.getPlacedisponibles()+1);
                scov.updateCoVoiturage(c);
            }
            String req = "DELETE FROM co_voiturage_requests WHERE `id` = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1,cov.getId());
            
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturageRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<CoVoiturageRequests> GetOwnCovoiturageRequests(int id) throws SQLException {
        String req = "SELECT * FROM co_voiturage_requests WHERE user = ? ORDER BY etat DESC , created DESC";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        ArrayList<CoVoiturageRequests> co = new ArrayList<>();
        while (rs.next()) {
            co.add(new CoVoiturageRequests(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5)));
        }
        return co;
    }
    
    public ArrayList<CoVoiturageRequests> GetOwnCovoiturageRequestsIdc(int idc) throws SQLException {
        String req = "SELECT * FROM co_voiturage_requests WHERE idc = ? ORDER BY etat DESC , created DESC";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, idc);
        ResultSet rs = pre.executeQuery();
        ArrayList<CoVoiturageRequests> co = new ArrayList<>();
        while (rs.next()) {
            co.add(new CoVoiturageRequests(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5)));
        }
        return co;
    }
    
    public ArrayList<CoVoiturageRequests> GetOwnCovoiturageRequests(int id,int idc) throws SQLException {
        String req = "SELECT * FROM co_voiturage_requests WHERE user = ? AND idc = ? ORDER BY etat DESC , created DESC";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        pre.setInt(2, idc);
        ResultSet rs = pre.executeQuery();
        ArrayList<CoVoiturageRequests> co = new ArrayList<>();
        
        while (rs.next()) {
            co.add(new CoVoiturageRequests(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5)));
        }
        
        return co;
    }
    
    public ArrayList<CoVoiturageRequests> GetOwnCovoiturageRequests() {
        try {
            String req = "SELECT * FROM co_voiturage_requests ORDER BY etat DESC , created DESC";
            PreparedStatement pre = con.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            ArrayList<CoVoiturageRequests> co = new ArrayList<>();
            
            while (rs.next()) {
                co.add(new CoVoiturageRequests(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5)));
            }
            
            return co;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturageRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean hasRequests(User user){
        try {
            String req = "SELECT * FROM co_voiturage_requests WHERE user = ? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, user.getId());
            ResultSet rs = pre.executeQuery();
            ArrayList<CoVoiturageRequests> co = new ArrayList<>();
            
            if (rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturageRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
