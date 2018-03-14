/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Colocation;
import util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douha
 */
public class ColocationCRUD {
  Connection cnx = DataSource.getInstance().getConnection();
  
  public void ajouterColocation(Colocation c){
        try {
            String requete = "INSERT INTO colocation (loyer,titre,type,meuble,description,photo,photo1,photo2,ville,x,y,nature,user_id,enable) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setFloat(1, c.getLoyer());
            pst.setString(2, c.getTitre());
            pst.setString(3, c.getType());
            pst.setString(4, c.getMeuble());
            pst.setString(5, c.getDescription());
            pst.setString(6, c.getPhoto());
            pst.setString(7, c.getPhoto1());
            pst.setString(8, c.getPhoto2());
            pst.setString(9, c.getVille());
            pst.setInt(13, c.getUser_id());
            pst.setInt(10, c.getX());
            pst.setInt(11, c.getY());
            pst.setInt(14, c.getEnable());
            pst.setString(12, c.getNature());
        
    
            boolean res= pst.execute();
            if(res)
            System.out.println("Colocation ajoutée");
            else
                System.out.println("Colocation ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
    public List<Colocation> afficherColocation(){
         List<Colocation> myList = new ArrayList<Colocation>();
        try {
           
            String requete2 = "SELECT * FROM colocation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            
            while(rs.next()){
                Colocation c = new Colocation();
                c.setId(rs.getInt(1));
                c.setLoyer(rs.getInt(2));
                c.setTitre(rs.getString(3));
                 c.setType(rs.getString(4));
                  c.setMeuble(rs.getString(5));
                   c.setDescription(rs.getString(6));
                    c.setPhoto(rs.getString(7));
                   
                myList.add(c);
            }
            
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    public void supprimerColocation(Colocation c,int id){
        try {
            String requete = "DELETE FROM"
                    + " colocation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("colocation supprimée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void modifierColocation(Colocation c,int id){
        try {
            String requete = "UPDATE  colocation SET loyer=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2,id);
            pst.setFloat(1, c.getLoyer());
           
            pst.executeUpdate();
            System.out.println("Colocation modifié");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
