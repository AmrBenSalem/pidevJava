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
    Connection con = DataSource.getInstance().getConnection();
     public void ajouterObjet(Objet o){
        try {
            String requete = "INSERT INTO objet (user,Type,Description,Date,Nature,Lieu,Photo,enable) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
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
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            
            while(rs.next()){
                Objet o = new Objet();
                o.setUser(rs.getInt(2));
                o.setType(rs.getString(3));
                o.setDescription(rs.getString(4));
                o.setDate(rs.getDate(5));
                o.setNature(rs.getString(6));
                o.setLieu(rs.getString(7));
                o.setPhoto(rs.getString(8));
                o.setEnable(rs.getBoolean(9));
                   
                myList.add(o);
            }
            
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
        return myList;
    }
         
         public List<Objet> affichobjperd(){
         List<Objet> myList = new ArrayList<Objet>();
        try {
           
            String requete2 = "SELECT * FROM objet where Nature='Objet Perdu' and enable=1";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            
            while(rs.next()){
                Objet o = new Objet();
                o.setUser(rs.getInt(2));
                o.setType(rs.getString(3));
                o.setDescription(rs.getString(4));
                o.setDate(rs.getDate(5));
                o.setNature(rs.getString(6));
                o.setLieu(rs.getString(7));
                o.setPhoto(rs.getString(8));
                o.setEnable(rs.getBoolean(9));
                   
                myList.add(o);
            }
            
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
        return myList;
    }
        public List<Objet> affichobjtrouv(){
         List<Objet> myList = new ArrayList<Objet>();
        try {
           
            String requete2 = "SELECT * FROM objet where Nature='Objet Trouvé' and enable=1";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            
            while(rs.next()){
                Objet o = new Objet();
                o.setUser(rs.getInt(2));
                o.setType(rs.getString(3));
                o.setDescription(rs.getString(4));
                o.setDate(rs.getDate(5));
                o.setNature(rs.getString(6));
                o.setLieu(rs.getString(7));
                o.setPhoto(rs.getString(8));
                o.setEnable(rs.getBoolean(9));
                   
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
            PreparedStatement pst = con.prepareStatement(requete);
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
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(2,id);
            pst.setString(1, o.getNature());
           
            pst.executeUpdate();
            System.out.println("Objet modifié");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}