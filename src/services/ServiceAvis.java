/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import IServices.IAvis;
import entities.Avis;
import entities.Event;
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
 * @author Liwa
 */
public class ServiceAvis  implements IAvis{

    public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;
    @Override
    public void addAvis(int avis, int iduser, int idevent) {
        String sql = "Update  `avis` set `avis`='"+avis+"' where iduser='"+iduser+"' and idevent='"+idevent+"' ";
   PreparedStatement statement;
        try {
       statement = con.prepareStatement(sql);
 
       statement.executeUpdate();
       System.out.println(sql);
         int rowsInserted = statement.executeUpdate();
         if (rowsInserted > 0) {
            System.out.println("**************Avis  ajouté************* ");}
        } catch (SQLException ex) {
            System.out.println("**************echec d'ajout***********");
        }
    }
    
    
    public void ajouterParticipation(int avis ,int idevent,int iduser){
        String sql = "INSERT INTO `avis`(`iduser`, `idevent`, `avis`) VALUES (?,?,?)";
   PreparedStatement statement;
        try {
       statement = con.prepareStatement(sql);
 
       statement.setInt(1, iduser);
       statement.setInt(2, idevent);
       statement.setInt(3, 0);
       //System.out.println(sql);
         int rowsInserted = statement.executeUpdate();
         if (rowsInserted > 0) {
            System.out.println("**************Participation  éffectuée************* ");}
        } catch (SQLException ex) {
            System.out.println("**************echec de participation***********");
        }
    }
    
    public void annulerParticipation(int idevent ,int iduser) throws SQLException{
        
        String req = "DELETE FROM `avis` WHERE `idevent`='"+idevent+"' and avis='"+0+"' and iduser='"+iduser+"' ";
        Statement pre=con.createStatement();
        pre.executeUpdate(req);
        System.out.println("Participation supprimée");
        
    }
    

    @Override
    public ArrayList<Avis> consulterAvis(int idevent) {
        String sql= "SELECT * FROM `avis` WHERE `idevent`='"+idevent+"' and avis>='"+1+"' order by avis DESC ";
        PreparedStatement statement;
        ArrayList<Avis> list = new ArrayList<Avis>(); 
       try {
        statement = con.prepareStatement(sql);
        ResultSet result = statement.executeQuery(sql); 

        while (result.next()){
            
            int id = result.getInt(4);
            int avis = result.getInt(3);
            int iduser = result.getInt(2);
            int ide = result.getInt(1);
            list.add(new Avis(id,avis,ide,iduser)); 
            
        }
      
      } catch (SQLException ex) {
            Logger.getLogger(Avis.class.getName()).log(Level.SEVERE, null, ex);}
             return (list); 
    }
    
    
    public Avis consulterParticipations(Event e, int iduser){
        String sql= "SELECT * FROM `avis` WHERE `idevent` = '"+e.getId()+"'and `iduser` ='"+iduser+"' order by avis DESC ";
        PreparedStatement ste;
        Avis a = new Avis();
        try {
            ste = con.prepareStatement(sql);
            ResultSet result = ste.executeQuery(sql); 
            while (result.next())
            {
                
                  a.setIduser(result.getInt("iduser"));
                  a.setIdevent(result.getInt("idevent"));
                  a.setAvis(result.getInt("avis"));
                  a.setId(result.getInt("id"));
                  
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    
    
    
    
    
}
