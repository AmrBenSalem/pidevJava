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

    public void ajouterColocation(Colocation c) {
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
            pst.setDouble(10, c.getX());
            pst.setDouble(11, c.getY());
            pst.setInt(14, c.getEnable());
            pst.setString(12, c.getNature());

            pst.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Colocation> afficherColocation() {
        List<Colocation> myList = new ArrayList<Colocation>();
        try {

            String requete2 = "SELECT * FROM colocation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete2);

            while (rs.next()) {
                Colocation c = new Colocation();
                c.setId(rs.getInt(1));
                c.setLoyer(rs.getInt(2));
                c.setTitre(rs.getString(3));
                c.setType(rs.getString(4));
                c.setMeuble(rs.getString(5));
                c.setDescription(rs.getString(6));
                c.setPhoto(rs.getString(7));
                 c.setPhoto1(rs.getString(8));
                  c.setPhoto2(rs.getString(9));
                c.setX(rs.getDouble(12));
                 c.setY(rs.getDouble(13));
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    
     public List<Colocation> afficherColocationDemande() {
        List<Colocation> myList = new ArrayList<Colocation>();
        try {

            String requete2 = "SELECT * FROM colocation where nature='Demande' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete2);

            while (rs.next()) {
                Colocation c = new Colocation();
                c.setId(rs.getInt(1));
                c.setLoyer(rs.getInt(2));
                c.setTitre(rs.getString(3));
                c.setType(rs.getString(4));
                c.setMeuble(rs.getString(5));
                c.setDescription(rs.getString(6));
                c.setPhoto(rs.getString(7));
                 c.setPhoto1(rs.getString(8));
                  c.setPhoto2(rs.getString(9));
                c.setX(rs.getDouble(12));
                 c.setY(rs.getDouble(13));
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public List<Colocation> afficherColocationByUser(int id) {
        List<Colocation> myList = new ArrayList<Colocation>();
        try {

            String requete = "SELECT * FROM colocation where user_id=?";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Colocation c = new Colocation();
                c.setId(rs.getInt(1));
                c.setLoyer(rs.getInt(2));
                c.setTitre(rs.getString(3));
                c.setType(rs.getString(4));
                c.setMeuble(rs.getString(5));
                c.setDescription(rs.getString(6));
                 c.setPhoto(rs.getString(7));
                 c.setPhoto1(rs.getString(8));
                  c.setPhoto2(rs.getString(9));
                c.setNature(rs.getString("nature"));
                c.setVille(rs.getString("ville"));
                 c.setX(rs.getDouble("x"));
                 c.setY(rs.getDouble("y"));
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public void supprimerColocation(int id) {
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

    public void modifierColocation(Colocation c) {
        try {
            String requete = "UPDATE  colocation SET loyer=? ,nature=?,"
                    + " type=?, meuble=?,  titre=?, photo=?, photo1=?, "
                    + "photo2 =?, description=?,ville=? ,x=?,y=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setFloat(1, c.getLoyer());
            pst.setString(5, c.getTitre());
            pst.setString(3, c.getType());
            pst.setString(4, c.getMeuble());
            pst.setString(9, c.getDescription());
            pst.setString(6, c.getPhoto());
            pst.setString(7, c.getPhoto1());
            pst.setString(8, c.getPhoto2());
            pst.setString(2, c.getNature());
            pst.setString(10, c.getVille());
            pst.setDouble(11, c.getX());
            pst.setDouble(12, c.getY());
            pst.setInt(13, c.getId());


            pst.executeUpdate();
            System.out.println("Colocation modifié");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
