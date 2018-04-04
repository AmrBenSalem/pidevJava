/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CoVoiturage;
import entities.CoVoiturageDays;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

/**
 *
 * @author Justpro
 */
public class ServiceCoVoiturage {

    public Connection con = DataSource.getInstance().getConnection();

    public ServiceCoVoiturage() throws SQLException {

    }

    public void addCoVoiturage(CoVoiturage cov) {
        try {
            String req = "INSERT INTO co_voiturage (`user`, `type`, `depart`, `destination`, `date`, `onetime`, `placedisponibles`, `depart_id`, `destination_id`, `created`, `updated`, `depart_lat`, `depart_lng`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, cov.getUser());
            pre.setString(2, cov.getType());
            pre.setString(3, cov.getDepart());
            pre.setString(4, cov.getDestination());
            pre.setTimestamp(5, cov.getDate());
            pre.setString(6, cov.getOnetime());
            pre.setInt(7, cov.getPlacedisponibles());
            pre.setString(8, cov.getDepart_id());
            pre.setString(9, cov.getDestination_id());
            pre.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
            pre.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
            pre.setDouble(12, cov.getDepart_lat());
            pre.setDouble(13, cov.getDepart_lng());
            //pre.execute();

            pre.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addCoVoiturage(CoVoiturage cov, CoVoiturageDays cod) {
        try {
            String req = "INSERT INTO co_voiturage (`user`, `type`, `depart`, `destination`, `date`, `onetime`, `placedisponibles`, `depart_id`, `destination_id`, `created`, `updated`, `depart_lat`, `depart_lng`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, cov.getUser());
            pre.setString(2, cov.getType());
            pre.setString(3, cov.getDepart());
            pre.setString(4, cov.getDestination());
            pre.setTimestamp(5, null);
            pre.setString(6, cov.getOnetime());
            pre.setInt(7, cov.getPlacedisponibles());
            pre.setString(8, cov.getDepart_id());
            pre.setString(9, cov.getDestination_id());
            pre.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
            pre.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
            pre.setDouble(12, cov.getDepart_lat());
            pre.setDouble(13, cov.getDepart_lng());
            //pre.execute();

            pre.executeUpdate();

            ResultSet rs = pre.getGeneratedKeys();
            rs.next();
            int idc = rs.getInt(1);
            ServiceCoVoiturageDays scod = new ServiceCoVoiturageDays();
            cod.setIdc(idc);
            scod.addDays(cod);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCoVoiturage(CoVoiturage cov) throws SQLException {
        String req = "UPDATE co_voiturage SET `user` = ?, `type` = ?, `depart` = ?, `destination` = ?, `date` = ?, `onetime` = ?, `placedisponibles` = ?, `depart_id` = ?, `destination_id` = ?, `created` = ?, `updated` = ?, `depart_lat` = ?, `depart_lng` = ? WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, cov.getUser());
        pre.setString(2, cov.getType());
        pre.setString(3, cov.getDepart());
        pre.setString(4, cov.getDestination());
        pre.setTimestamp(5, cov.getDate());
        pre.setString(6, cov.getOnetime());
        pre.setInt(7, cov.getPlacedisponibles());
        pre.setString(8, cov.getDepart_id());
        pre.setString(9, cov.getDestination_id());
        pre.setTimestamp(10, cov.getCreated());
        pre.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
        pre.setDouble(12, cov.getDepart_lat());
        pre.setDouble(13, cov.getDepart_lng());
        pre.setInt(14, cov.getId());
        System.out.println(req);
        pre.execute();
    }
    
    public void updateCoVoiturage(CoVoiturage cov , CoVoiturageDays cod) throws SQLException {
        String req = "UPDATE co_voiturage SET `user` = ?, `type` = ?, `depart` = ?, `destination` = ?, `date` = ?, `onetime` = ?, `placedisponibles` = ?, `depart_id` = ?, `destination_id` = ?, `created` = ?, `updated` = ?, `depart_lat` = ?, `depart_lng` = ? WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, cov.getUser());
        pre.setString(2, cov.getType());
        pre.setString(3, cov.getDepart());
        pre.setString(4, cov.getDestination());
        pre.setTimestamp(5, cov.getDate());
        pre.setString(6, cov.getOnetime());
        pre.setInt(7, cov.getPlacedisponibles());
        pre.setString(8, cov.getDepart_id());
        pre.setString(9, cov.getDestination_id());
        pre.setTimestamp(10, cov.getCreated());
        pre.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
        pre.setDouble(12, cov.getDepart_lat());
        pre.setDouble(13, cov.getDepart_lng());
        pre.setInt(14, cov.getId());
        System.out.println(req);
        pre.executeUpdate();
        
            ServiceCoVoiturageDays scod = new ServiceCoVoiturageDays();
            cod.setIdc(cov.getId());
            if (scod.GetCovoiturageDays(cov) == null){
              scod.addDays(cod);  
            } else {
                scod.update(cod);
            }
            
    }

    public void deleteCoVoiturage(CoVoiturage cov) throws SQLException {
        String req = "DELETE FROM co_voiturage_requests WHERE `idc` = ? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, cov.getId());
        pre.execute();

        req = "DELETE FROM co_voiturage_days WHERE `idc` = ? ";
        pre = con.prepareStatement(req);
        pre.setInt(1, cov.getId());
        pre.execute();

        req = "DELETE FROM co_voiturage WHERE `id` = ? ";
        pre = con.prepareStatement(req);
        pre.setInt(1, cov.getId());
        pre.execute();
    }

    public ArrayList<CoVoiturage> readCoVoiturage() throws SQLException {
        String req = "SELECT * FROM co_voiturage";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet rs = pre.executeQuery();
        ArrayList<CoVoiturage> co = new ArrayList<>();
        while (rs.next()) {
            co.add(new CoVoiturage(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getTimestamp(11), rs.getTimestamp(12), rs.getDouble(13), rs.getDouble(14)));
        }
        return co;
    }

    public CoVoiturage readCoVoiturage(int id) {
        try {
            String req = "SELECT * FROM co_voiturage WHERE `id` = ? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return new CoVoiturage(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getTimestamp(11), rs.getTimestamp(12), rs.getDouble(13), rs.getDouble(14));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoVoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CoVoiturage> GetCovoituragePerType(String type) throws SQLException {
        String req ;
        if (type.equals("d")){
            req = "SELECT * FROM co_voiturage WHERE `type` = ? AND ( date > ? OR onetime = 'on' ) ORDER BY updated DESC , created DESC";
        } else {
            req = "SELECT * FROM co_voiturage WHERE `type` = ? AND ( date > ? OR onetime = 'on' ) AND placedisponibles > 0 ORDER BY updated DESC , created DESC"; 
        }
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, type);
        pre.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        ResultSet rs = pre.executeQuery();
        ArrayList<CoVoiturage> co = new ArrayList<>();
        while (rs.next()) {
            co.add(new CoVoiturage(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getTimestamp(11), rs.getTimestamp(12), rs.getDouble(13), rs.getDouble(14)));
        }
        return co;
    }
    
    public ArrayList<CoVoiturage> GetOwnCovoituragePerType(String type,int id) throws SQLException {
        String req;
        if (type.equals("d")){
            req = "SELECT * FROM co_voiturage WHERE `type` = ? AND ( date > ? OR onetime = 'on' ) AND user = ? ORDER BY updated DESC , created DESC";
        } else {
            req = "SELECT * FROM co_voiturage WHERE `type` = ? AND ( date > ? OR onetime = 'on' ) AND placedisponibles > 0 AND user = ? ORDER BY updated DESC , created DESC";
        }
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, type);
        pre.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        pre.setInt(3, id);
        ResultSet rs = pre.executeQuery();
        ArrayList<CoVoiturage> co = new ArrayList<>();
        while (rs.next()) {
            co.add(new CoVoiturage(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getTimestamp(11), rs.getTimestamp(12), rs.getDouble(13), rs.getDouble(14)));
        }
        return co;
    }
    

}
