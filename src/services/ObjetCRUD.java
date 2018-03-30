/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Objet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import util.DataSource;
/**
 *
 * @author bader
 */
public class ObjetCRUD {
    Connection cnx = DataSource.getInstance().getConnection();
     public void ajouterObjet(Objet o){
        try {
            String requete = "INSERT INTO objet (user,Type,Description,Date,Nature,Lieu,Photo,enable) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, o.getUser());
            pst.setString(2, o.getType());
            pst.setString(3, o.getDescription());
            pst.setDate(4, new java.sql.Date(o.getDate().getTime()));
            pst.setString(5, o.getNature());
            pst.setString(6, o.getLieu());
            pst.setString(7, o.getPhoto());
            pst.setBoolean(8, o.getEnable());
                 
    
            boolean res= pst.execute();
            if(res)
            System.out.println("Objet ajouté");
            else
                System.out.println("pas ajouté");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
         public List<Objet> afficherObjet(){
         List<Objet> myList = new ArrayList<Objet>();
        try {
           
            String requete2 = "SELECT * FROM objet";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            
            while(rs.next()){
                Objet o = new Objet();
                o.setDescription(rs.getString(1));
                o.setNature(rs.getString(2));
                o.setType(rs.getString(3));
                o.setPhoto(rs.getString(4));
                o.setLieu(rs.getString(5));
                o.setUser(rs.getInt(6));
                o.setEnable(rs.getBoolean(7));
                o.setDate(rs.getDate(8));
                   
                myList.add(o);
            }
            
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
        return myList;
    }
         
         public void supprimerColocation(Objet o,int id){
        try {
            String requete = "DELETE FROM"
                    + " objet WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Objet supprimé");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
         
         public void modifierColocation(Objet o,int id){
        try {
            String requete = "UPDATE  objet SET Nature=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2,id);
            pst.setString(1, o.getNature());
           
            pst.executeUpdate();
            System.out.println("Objet modifié");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}