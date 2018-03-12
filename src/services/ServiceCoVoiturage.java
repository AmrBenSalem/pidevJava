/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CoVoiturage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pidev.DataSource;

/**
 *
 * @author Justpro
 */
public class ServiceCoVoiturage {
    public Connection con = DataSource.getInstance().getCon();
    public Statement st;
    
    public ServiceCoVoiturage() throws SQLException{
        st = con.createStatement();
    }
    
    public void addCoVoiturage(CoVoiturage cov) throws SQLException{
        String req = "INSERT INTO co_voiturage (`user`, `type`, `depart`, `destination`, `date`, `onetime`, `placedisponibles`, `depart_id`, `destination_id`, `created`, `updated`, `depart_lat`, `depart_lng`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,cov.getUser());
        pre.setString(2,cov.getType());
        pre.setString(3,cov.getDepart());
        pre.setString(4,cov.getDestination());
        pre.setTimestamp(5,cov.getDate());
        pre.setString(6,cov.getOnetime());
        pre.setInt(7,cov.getPlacedisponibles());
        pre.setString(8,cov.getDepart_id());
        pre.setString(9,cov.getDestination_id());
        pre.setTimestamp(10,cov.getCreated());
        pre.setTimestamp(11,cov.getUpdated());
        pre.setDouble(12,cov.getDepart_lat());
        pre.setDouble(13,cov.getDepart_lng());
        pre.execute();
    }
   
    public void updateCoVoiturage(CoVoiturage cov) throws SQLException{
        String req = "UPDATE co_voiturage SET `user` = ?, `type` = ?, `depart` = ?, `destination` = ?, `date` = ?, `onetime` = ?, `placedisponibles` = ?, `depart_id` = ?, `destination_id` = ?, `created` = ?, `updated` = ?, `depart_lat` = ?, `depart_lng` = ? WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,cov.getUser());
        pre.setString(2,cov.getType());
        pre.setString(3,cov.getDepart());
        pre.setString(4,cov.getDestination());
        pre.setTimestamp(5,cov.getDate());
        pre.setString(6,cov.getOnetime());
        pre.setInt(7,cov.getPlacedisponibles());
        pre.setString(8,cov.getDepart_id());
        pre.setString(9,cov.getDestination_id());
        pre.setTimestamp(10,cov.getCreated());
        pre.setTimestamp(11,cov.getUpdated());
        pre.setDouble(12,cov.getDepart_lat());
        pre.setDouble(13,cov.getDepart_lng());
        pre.setInt(14,cov.getId());
        System.out.println(req);
        pre.execute();
    }
    
    public void deleteCoVoiturage(CoVoiturage cov) throws SQLException{
        String req = "DELETE FROM co_voiturage WHERE `id` = ? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,cov.getId());
        pre.execute();
    }
    
    public ArrayList<CoVoiturage> readCoVoiturage() throws SQLException{
        String req = "SELECT * FROM co_voiturage";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet rs =  pre.executeQuery();
        ArrayList<CoVoiturage> co = new ArrayList<> ();
        while (rs.next()){
             co.add(new CoVoiturage(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getTimestamp(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getTimestamp(11),rs.getTimestamp(12),rs.getDouble(13),rs.getDouble(14)));
        }
            return co;
    }
    
    public CoVoiturage readCoVoiturage(int id) throws SQLException{
        String req = "SELECT * FROM co_voiturage WHERE `id` = ? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,id);
        ResultSet rs =  pre.executeQuery();
        if (rs.next()){
            return new CoVoiturage(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getTimestamp(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getTimestamp(11),rs.getTimestamp(12),rs.getDouble(13),rs.getDouble(14));
        }
        else
            return null;
    }
    
    public ArrayList<CoVoiturage> readCoVoiturage(String type) throws SQLException{
        String req = "SELECT * FROM co_voiturage WHERE `type` = ? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,type);
        ResultSet rs =  pre.executeQuery();
        ArrayList<CoVoiturage> co = new ArrayList<> ();
        while (rs.next()){
            co.add(new CoVoiturage(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getTimestamp(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getTimestamp(11),rs.getTimestamp(12),rs.getDouble(13),rs.getDouble(14)));
        }
        return co;
    }
}
