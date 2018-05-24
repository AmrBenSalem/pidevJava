/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Interaction;
import entities.Objet;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

/**
 *
 * @author bader
 */
public class ServiceInteraction {

    Connection con = DataSource.getInstance().getConnection();

    public void ajouterinteraction(Interaction i) throws SQLException {
        String req = "INSERT INTO interaction(user,objet,Statut) VALUES(?,?,?)";
        PreparedStatement pst = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, i.getUser());
        pst.setInt(2, i.getObjet());
        pst.setString(3, i.getStatut());
        boolean res = pst.execute();
        if (!res) {
            System.out.println("Interaction ajouté");
        } else {
            System.out.println("Interaction n'est pas ajouté");
        }

    }

    public void supprimerinteraction(int id) {
        try {
            String req = "DELETE FROM interaction WHERE id=?";
            PreparedStatement pst = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Objet supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Interaction getByIdObjet(int id) {
        Interaction i = new Interaction();
        try {

            String req = "SELECT * FROM interaction WHERE objet=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                i.setId(rs.getInt("id"));
                i.setUser(rs.getInt("user"));
                i.setObjet(rs.getInt("objet"));
                i.setStatut(rs.getString("Statut"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public void getUserInteractionByIdObjet(int id) {
        Interaction i = new Interaction();
       try {

            String req = "SELECT user FROM interaction WHERE objet=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
            i.setUser(rs.getInt("user"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }

}
