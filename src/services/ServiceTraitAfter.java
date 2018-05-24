/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Traitafter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import util.DataSource;

/**
 *
 * @author bader
 */
public class ServiceTraitAfter {

    Connection con = DataSource.getInstance().getConnection();

    public void ajouterTraitAfter(Traitafter t) throws SQLException {
        String req = "INSERT INTO interaction(user,statut,interaction) VALUES(?,?,?)";
        PreparedStatement pst = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, t.getUser());
        pst.setString(2, t.getStatut());
        pst.setInt(3, t.getInteraction());
              boolean res = pst.execute();
        if (res) {
            System.out.println("Traitafter ajouté");
        } else {
            System.out.println("Traitafter n'est pas ajouté");
        }

        }

    }


