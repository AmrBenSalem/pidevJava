/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import IServices.IUserService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;
import entities.User;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.scene.control.Label;
import util.BCrypt;

/**
 *
 * @author bader
 */
public class UserCRUD implements IUserService {
        
    Statement ste ;
    Connection myconnection;
    
        public UserCRUD() {
        try {
            myconnection = DataSource.getInstance().getConnection();
            ste = myconnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void ajouterUser(User u) {
        
        try {                                   
            String reqAjout = "insert into user(username,username_canonical,email,email_canonical ,enabled ,password ,roles ,nom ,prenom,dateNaissance,adresse,sexe,classe,telephone,path_image) values (?,?,?,?,0,?,'a:0:{}',?,?,?,'Tunis',?,?,?,'introuvable')";
            PreparedStatement ps = myconnection.prepareStatement(reqAjout);
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getUserName().toLowerCase());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getEmail().toLowerCase());
            ps.setString(5, u.getPassword());
            ps.setString(6, u.getNom());
            ps.setString(7, u.getPrenom());
            ps.setDate(8, new java.sql.Date(u.getDateNaissance().getTime()));
            ps.setString(9, u.getSexe());
            ps.setString(10, u.getClasse());
            ps.setString(11, u.getTelephone());
             
            
           ps.executeUpdate();
           
           System.out.println("envoyé");
           
         
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("n'est pas envoyé");
        }
      
    }

    @Override
    public void modifierUser(User u,int id) {
               try {
            String reqUpdate = "update user set"
                    + " email=?,"
                    + "password=?,"
                    + "nom=?,"
                    + "prenom=? where ?";
            PreparedStatement ps = myconnection.prepareStatement(reqUpdate);
            ps.setString(1, u.getEmail()); 
            ps.setString(2, u.getPassword());   
            ps.setString(3, u.getNom());
            ps.setString(4, u.getPrenom());
            ps.setInt(5,id);
            ps.executeUpdate();
            
            System.out.println("envoyé");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimerUser(User u,int id) {
                       try {
            String reqDelete = "delete from user where id=?";

            PreparedStatement ps = myconnection.prepareStatement(reqDelete);
            ps.setInt(1,id);
            ps.executeUpdate();
            
            System.out.println("envoyé");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    @Override
    public List<User> afficherUser() {
        List<User> users = new ArrayList<>();
        try {
          String req = "select username,email,password,nom,prenom from user";
            PreparedStatement ps = myconnection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                User user = new User( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                users.add(user);
            }
                    
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }
    
@Override
        public String recevoirMDPavecUserName(String username)
        {
            String MDP ="vide";
            
          try{
              String reqRec = "select password from user where username=?";
               PreparedStatement ps = myconnection.prepareStatement(reqRec);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                MDP=rs.getString(1);
            }
          }
            
          catch (SQLException ex) {
          ex.printStackTrace();
        }
         return MDP;}
             @Override
        public String recevoirMDPavecId(int id)
        {
            String MDP ="vide";
            
          try{
              String reqRec = "select password from user where id=?";
               PreparedStatement ps = myconnection.prepareStatement(reqRec);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                MDP=rs.getString(1);
            }
          }
            
          catch (SQLException ex) {
          ex.printStackTrace();
        }
         return MDP;}
        
                public void changerMDP(String newMdp,int id)
        {
            try {
            String reqUpdate = "update user set password=? where id=?";
            PreparedStatement ps = myconnection.prepareStatement(reqUpdate);
          //  ps.setString(1, u.getEmail()); 
            ps.setString(1, BCrypt.hashpw(newMdp,BCrypt.gensalt(13)));   
          //  ps.setString(3, u.getNom());
         //   ps.setString(4, u.getPrenom());
            ps.setInt(2,id);
            ps.executeUpdate();
            
            System.out.println("envoyé");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
                public boolean uniqueUserName(String Username)
        { 
            boolean free=true;
          try{
              String reqRec = "select count(nom) from user where username=?";
               PreparedStatement ps = myconnection.prepareStatement(reqRec);
            ps.setString(1,Username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){  
            if(rs.getInt(1)!=0)
                free=false;
          }}
            
          catch (SQLException ex) {
          ex.printStackTrace();
        }
          return free;
        }
        
        public boolean uniqueMail(String mail)
        { 
            boolean free=true;
          try{
              String reqRec = "select count(nom) from user where email=?";
               PreparedStatement ps = myconnection.prepareStatement(reqRec);
            ps.setString(1,mail);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){  
            if(rs.getInt(1)!=0)
                free=false;
          }}
            
          catch (SQLException ex) {
          ex.printStackTrace();
        }
          return free;
        }
        
        public User recevoirUser(String username,Label l) {
        try {
          String req = "select username,email,password,nom,prenom,enabled,id,roles from user where username=?";
            PreparedStatement ps = myconnection.prepareStatement(req);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                User user = new User( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getInt(7));
                user.setRoles(rs.getString(8));
                return user;
            }
                    
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            l.setText("* verifier vos informations !");
        }
        return null;
    }
        public User getUser(int id) {
        try {
          String req = "select id,username,email,password ,enabled ,nom,prenom from user where id=?";
            PreparedStatement ps = myconnection.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                User user = new User( rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),rs.getString(7));
                return user;
            }
                    
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
        
               
        public String MD5(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException
        {
            byte[] bytesOfMessage = password.getBytes("UTF-8");
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] thedigest = md.digest(bytesOfMessage);
      BigInteger bigInt = new BigInteger(1,thedigest);
      String hashtext = bigInt.toString(16);
      while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
       }
      return hashtext;
        }
    
    
    public String recevoirNomavecId(int id)
        {
            String nom ="vide";
            
          try{
              String reqRec = "select username from user where id=?";
               PreparedStatement ps = myconnection.prepareStatement(reqRec);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                nom=rs.getString(1);
            }
          }
            
          catch (SQLException ex) {
          ex.printStackTrace();
        }
         return nom;}
   
        public String recevoirMailAvecId(int Id)
        {
            String mail ="vide";
            
          try{
              String reqRec = "select email from user where id=?";
               PreparedStatement ps = myconnection.prepareStatement(reqRec);
            ps.setInt(1,Id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                mail=rs.getString(1);
            }
          }
            
          catch (SQLException ex) {
          ex.printStackTrace();
        }
         return mail;}
        
         public void changerMail(String newmail,int id)
        {
            try {
            String reqUpdate = "update user set email=? where id=?";
            PreparedStatement ps = myconnection.prepareStatement(reqUpdate);
          //  ps.setString(1, u.getEmail()); 
            ps.setString(1, newmail);   
          //  ps.setString(3, u.getNom());
         //   ps.setString(4, u.getPrenom());
            ps.setInt(2,id);
            ps.executeUpdate();
            
            System.out.println("envoyé");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
}
