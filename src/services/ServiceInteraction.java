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
import java.sql.SQLException;
import java.sql.Statement;
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
        if (res) {
            System.out.println("Interaction ajouté");
        } else {
            System.out.println("Interaction n'est pas ajouté");
        }

        }

    public void supprimerinteraction(Interaction i, int id) throws SQLException {
        String req = "DELETE FROM interaction WHERE id=?";
        PreparedStatement pst = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Objet supprimé");

    }

}
