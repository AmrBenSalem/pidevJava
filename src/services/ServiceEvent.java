/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import IServices.IEvent;
import entities.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

/**
 *
 * @author Liwa
 */
public class ServiceEvent implements IEvent{
    public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public ServiceEvent() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void ajouterEvent( Event e) throws SQLException{
       
        String req="INSERT INTO `event`(`user`, `titre`, `description`, `dateDebut`, `dateFin`, `lieu`, `photo`, `nb_max`, `enable`, `categorie`, `createdAt`, `x`, `y`)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        try {
            pre= con.prepareStatement(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        pre.setInt(1,e.getIduser()); 
        pre.setString(2, e.getTitre());
        pre.setString(3, e.getDescription());
        pre.setDate(4,e.getDateDebut());
        pre.setDate(5,e.getDateFin());
        pre.setString(6,e.getLieu());
        pre.setString(7, e.getPhoto());
        pre.setInt(8,e.getNb_max()); 
        pre.setInt(9,e.getEnable()); 
        pre.setString(10, e.getCategorie());
        pre.setDate(11,e.getCreatedAt());
        pre.setDouble(12,e.getX());
        pre.setDouble(13, e.getY());
        

        
        pre.executeUpdate();
       
        
        System.out.println("event Ajoutee");

    }
    public  void updateEvent( Event e,int id)throws SQLException
        {
              
                 String req = "UPDATE`event` SET`user`=?,`titre`=?,`description`=?,`dateDebut`=?,`dateFin`=?,`lieu`=?,`photo`=?,`nb_max`=?,`enable`=?,`categorie`=?,`createdAt`=?,`x`=?,`y`=? WHERE `id`=? ";
                 PreparedStatement pre= con.prepareStatement(req);
                
                 
                //pre.setInt(1,a.getAdresse_cabinet());
                pre.setInt(1,e.getIduser()); 
                pre.setString(2, e.getTitre());
                pre.setString(3, e.getDescription());
                pre.setDate(4,e.getDateDebut());
                pre.setDate(5,e.getDateFin());
                pre.setString(6,e.getLieu());
                pre.setString(7, e.getPhoto());
                pre.setInt(8,e.getNb_max()); 
                pre.setInt(9,e.getEnable()); 
                pre.setString(10, e.getCategorie());
                pre.setDate(11,e.getCreatedAt());
                pre.setDouble(12,e.getX());
                pre.setDouble(13, e.getY());
                pre.setInt(14,id);


                pre.executeUpdate();
                 System.out.println("Event mise à jour"); 

}

    
         public  void supprimerEvent( int id) throws SQLException 
        {
                 String req = "DELETE FROM `Event` WHERE id="+id;
                 Statement pre=con.createStatement();
                 pre.executeUpdate(req);
                  System.out.println("Event supprimer");
        }

    public  List<Event> selectEvent() throws SQLException
      {
       List<Event> list=new ArrayList<>();
         
            String req="SELECT * FROM Event";
            PreparedStatement ste= con.prepareStatement(req);
            ResultSet result=ste.executeQuery();
            
            while(result.next())
            {
            Event e= new Event (result.getInt("id"),result.getString("titre"),result.getString("description")
                    ,result.getDate("dateDebut"),result.getDate("dateFin")
                    ,result.getString("lieu"),result.getString("photo")
                    ,result.getInt("nb_max"),result.getInt("enable"),result.getString("categorie"),result.getDate("createdAt"),result.getDouble("x"),result.getDouble("y"));
            list.add(e);
            }
           
       return list;
      }
    
    
    public Event consulterEvent(int id)
     {
          Event  e =new Event();
        String sql = "SELECT * FROM `event` WHERE id='"+id+"'";
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql); 
            while (result.next())
            {
                  
                  e.setId(result.getInt(1));
                  e.setIduser(result.getInt(2));
                  e.setTitre(result.getString(3));
                  e.setDescription(result.getString(4));
                  e.setDateDebut(result.getDate(5));
                  e.setDateFin(result.getDate(6));
                  e.setLieu(result.getString(7));
                  e.setX(result.getDouble(8));
                  e.setY(result.getDouble(9));
                  e.setPhoto(result.getString(10));
                  e.setNb_max(result.getInt(11));
                  e.setEnable(result.getInt(12));
                  e.setCategorie(result.getString(13));
                  e.setCreatedAt(result.getDate(14));
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
     }

    
}
