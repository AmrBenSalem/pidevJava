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
    public void addAvis(int avis, Event e) {
        String sql = "INSERT INTO `avis`(`iduser`, `idevent`, `avis`) VALUES (?,?,?)";
   PreparedStatement statement;
        try {
       statement = con.prepareStatement(sql);
 
       statement.setInt(1, e.getIduser());
       statement.setInt(2, e.getId());
       statement.setInt(3, avis);
       System.out.println(sql);
         int rowsInserted = statement.executeUpdate();
         if (rowsInserted > 0) {
            System.out.println("**************Avis  ajouté************* ");}
        } catch (SQLException ex) {
            System.out.println("**************echec d'ajout***********");
        }
    }

    @Override
    public ArrayList<Avis> consulterAvis(int idevent) {
        String sql= "SELECT * FROM `avis` WHERE `idevent`'"+idevent+"' order by note DESC ";
        PreparedStatement statement;
        ArrayList<Avis> list = new ArrayList<Avis>(); 
       try {
        statement = con.prepareStatement(sql);
        ResultSet result = statement.executeQuery(sql); 

        while (result.next()){
            int id = result.getInt("id");
            int avis = result.getInt("note");
            int iduser = result.getInt("iduser");
            int ide = result.getInt("idevent");
            
            list.add(new Avis(id,avis,ide,iduser)); 
        }
      
      } catch (SQLException ex) {
            Logger.getLogger(Avis.class.getName()).log(Level.SEVERE, null, ex);}
             return (list); 
    }
    
    
    
}
