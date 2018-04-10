/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.services;

import EspaceEtude.entities.Documents;
import EspaceEtude.entities.Matiere;
import EspaceEtude.entities.Notification;
import EspaceEtude.entities.Section;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

/**
 *
 * @author oussema
 */
public class EspaceEtudeService {
    public Connection con = DataSource.getInstance().getConnection();
    public Statement st;
    
    public EspaceEtudeService(){
        
    }
    public ObservableList getAllSectionByNiveau(String niveau){
        if(niveau.equals("premiereA"))
            niveau="1A";
        if(niveau.equals("deuxiemeA"))
            niveau="2A";
        if(niveau.equals("deuxiemeB"))
            niveau="2B";
        if(niveau.equals("troisiemeA"))
            niveau="3A";
        if(niveau.equals("troisiemeB"))
            niveau="3B";
        if(niveau.equals("quatriemeA"))
            niveau="4A";
        if(niveau.equals("quatriemeB"))
            niveau="4B";
        if(niveau.equals("cinquiemeA"))
            niveau="5A";
        if(niveau.equals("cinquiemeB"))
            niveau="5B";
        
        ArrayList<Section> sectionList=new ArrayList();
        try {
            String req="select * from section where niveau='"+niveau+"'";
            st=con.createStatement();
            ResultSet rs=st.executeQuery(req); 
            
            while(rs.next()){
                System.out.println(rs.getString("libelle"));
                Section s=new Section();
                s.setId(rs.getInt("id"));
                s.setLibelle(rs.getString("libelle"));
                s.setNiveau(rs.getString("niveau"));
                sectionList.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.observableArrayList(sectionList);
        
    }
    public ObservableList getAllMatiereBySection(Section section){
        ArrayList<Matiere> matiereList=new ArrayList();
        try {
            String req="select * from section_matiere where section_id='"+section.getId()+"'";
            st=con.createStatement();
            ResultSet rs=st.executeQuery(req); 
            while(rs.next()){
                String req2="select * from matiere where id="+rs.getInt("matiere_id");
                st=con.createStatement();
                ResultSet rs2=st.executeQuery(req2);
                Matiere m=new Matiere();
                rs2.next();
                m.setId(rs2.getInt("id"));
                m.setLibelle(rs2.getString("libelle"));
                m.setCoefficient(rs2.getString("coefficient"));
                m.setType(rs2.getString("type"));
                matiereList.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.observableArrayList(matiereList);
        
    }
    public void addDocument(String path, String libelle, String date, String typeDocument, Double size, String language,int user,int matiere ,String image, int flag){
        String sql="insert into documents(path,libelle,date,typeDocument,size,language,id_matiere,id_user,img,flag) values (?,?,?,?,?,?,?,?,?,?)"; 
          try {
                PreparedStatement pr = con.prepareStatement(sql);
                pr.setString(1, path);
                pr.setString(2, libelle);
                pr.setString(3, date);
                pr.setString(4, typeDocument);
                pr.setDouble(5, size);
                pr.setString(6, language);
                pr.setInt(7, matiere);
                pr.setInt(8, user);
                pr.setString(9, image);
                pr.setInt(10, flag);
                pr.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
          String sql2="SELECT * FROM documents ORDER BY id DESC LIMIT 1 ";
          Documents  d=new Documents();
        try {
            st=con.createStatement();
            ResultSet rs=st.executeQuery(sql2);
            rs.next();
            d.setId(rs.getInt("id"));
                d.setLibelle(rs.getString("libelle"));
                d.setLanguage(rs.getString("language"));
                d.setPath(rs.getString("path"));
                d.setDate(rs.getString("date"));
                d.setTypeDocument(rs.getString("typeDocument"));
                d.setSize(rs.getDouble("size"));
                d.setImage(rs.getString("img"));
                d.setFlag(rs.getInt("flag"));
                d.setUser(rs.getInt("id_user"));
                d.setMatiere(rs.getInt("id_matiere"));
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          createNotification(d, matiere);
          
    }
    public ArrayList<Documents> getAllDocumentsValid(int idMatiere){
         ArrayList<Documents> documentList=new ArrayList();
        try {
            String req="select * from documents where flag="+1+" and id_matiere="+idMatiere;
            st=con.createStatement();
            ResultSet rs=st.executeQuery(req); 
            
            while(rs.next()){
                Documents d=new Documents();
                d.setId(rs.getInt("id"));
                d.setLibelle(rs.getString("libelle"));
                d.setLanguage(rs.getString("language"));
                d.setPath(rs.getString("path"));
                d.setDate(rs.getString("date"));
                d.setTypeDocument(rs.getString("typeDocument"));
                d.setSize(rs.getDouble("size"));
                d.setImage(rs.getString("img"));
                d.setFlag(rs.getInt("flag"));
                d.setUser(rs.getInt("id_user"));
                d.setMatiere(rs.getInt("id_matiere"));
                documentList.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentList;
    }
    public void supprimerDocument(int idDocument){
        String sql="delete from documents where id="+idDocument;
        try {
            st=con.createStatement();
             st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public void modifierDocument(Documents doc){
        try {
            String sql="Update documents set libelle='"+doc.getLibelle()+"', language='"+doc.getLanguage()+"' where id="+doc.getId();
            st=con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public String getUserRole(int idUser){
        String role="";
        try {
            String sql="select roles from user where id="+idUser;
            st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            rs.next();
            role=rs.getString("roles");
            
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }

    public void AccepterDocument(int idDoc) {
        String sql="update documents set flag=1 where id="+idDoc;
        try{
        st=con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Documents> getAllDocumentsInValid(int idMatiere) {
        ArrayList<Documents> documentList=new ArrayList();
        try {
            String req="select * from documents where flag="+0+" and id_matiere="+idMatiere;
            st=con.createStatement();
            ResultSet rs=st.executeQuery(req); 
            
            while(rs.next()){
                Documents d=new Documents();
                d.setId(rs.getInt("id"));
                d.setLibelle(rs.getString("libelle"));
                d.setLanguage(rs.getString("language"));
                d.setPath(rs.getString("path"));
                d.setDate(rs.getString("date"));
                d.setTypeDocument(rs.getString("typeDocument"));
                d.setSize(rs.getDouble("size"));
                d.setImage(rs.getString("img"));
                d.setFlag(rs.getInt("flag"));
                d.setUser(rs.getInt("id_user"));
                d.setMatiere(rs.getInt("id_matiere"));
                documentList.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentList;
    }
    public void createNotification(Documents doc,int idMatiere){
        String sql="insert into notification_documents(id_matiere,id_doc,date,vu) values(?,?,?,?) ";
        try {
                PreparedStatement pr = con.prepareStatement(sql);
                pr.setInt(1, idMatiere);
                pr.setInt(2, doc.getId());
                pr.setString(3, new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                pr.setInt(4, 0);
                pr.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
    }
    public ArrayList<Notification> getAllnotif(){
        String sql="select * from notification_documents order by id DESC ";
        ArrayList<Notification> notifList=new ArrayList<>();
        try {
            st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                Notification n=new Notification();
                n.setId(rs.getInt("id"));
                n.setMatiere(getMatiereById(rs.getInt("id_matiere")));
                n.setDoc(getDocumentById(rs.getInt("id_doc")));
                n.setDate(rs.getString("date"));
                n.setVu(rs.getInt("vu"));
                notifList.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notifList;
            
    }
    public void setNotificationRead(Notification n){
        String sql="update notification_documents set vu=1 where id="+n.getId();
        try{
        st=con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Matiere getMatiereById(int id){
        Matiere m=new Matiere();
        try {
            String sql="select * from Matiere where id="+id;
            st=con.createStatement();
            ResultSet rs2=st.executeQuery(sql);
            rs2.next();
            m.setId(rs2.getInt("id"));
            m.setLibelle(rs2.getString("libelle"));
            m.setCoefficient(rs2.getString("coefficient"));
            m.setType(rs2.getString("type"));
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    public Documents getDocumentById(int id){
        Documents d=new Documents();
        try {
            String req="select * from documents where id="+id;
            st=con.createStatement();
            ResultSet rs=st.executeQuery(req); 
            rs.next();
            d.setId(rs.getInt("id"));
            d.setLibelle(rs.getString("libelle"));
            d.setLanguage(rs.getString("language"));
            d.setPath(rs.getString("path"));
            d.setDate(rs.getString("date"));
            d.setTypeDocument(rs.getString("typeDocument"));
            d.setSize(rs.getDouble("size"));
            d.setImage(rs.getString("img"));
            d.setFlag(rs.getInt("flag"));
            d.setUser(rs.getInt("id_user"));
            d.setMatiere(rs.getInt("id_matiere"));
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
  
      public void removeSection (Section s){
        String sql="delete from section where id="+s.getId();
        try {
            st=con.createStatement();
             st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void removeMatiere (Matiere m){
        String sql="delete from matiere where id="+m.getId();
        try {
            st=con.createStatement();
             st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void addSection(String libelle,String niveau){
        String sql="insert into section (libelle,niveau) values (?,?)";
        if(niveau.equals("premiereA"))
            niveau="1A";
        if(niveau.equals("deuxiemeA"))
            niveau="2A";
        if(niveau.equals("deuxiemeB"))
            niveau="2B";
        if(niveau.equals("troisiemeA"))
            niveau="3A";
        if(niveau.equals("troisiemeB"))
            niveau="3B";
        if(niveau.equals("quatriemeA"))
            niveau="4A";
        if(niveau.equals("quatriemeB"))
            niveau="4B";
        if(niveau.equals("cinquiemeA"))
            niveau="5A";
        if(niveau.equals("cinquiemeB"))
            niveau="5B";
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, libelle);
            pr.setString(2, niveau);
            pr.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void addMatiere(String libelle,String coefficient,String type,Section s){
          String sql="insert into matiere (libelle,coefficient,type) values (?,?,?)"; 
          String sql2="insert into section_matiere (section_id,matiere_id) values (?,?)";
          String sql3="SELECT * FROM matiere ORDER BY id DESC LIMIT 1 ";
          try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, libelle);
            pr.setString(2, coefficient);
            pr.setString(3, type);
            pr.execute();
            st=con.createStatement();
            ResultSet rs=st.executeQuery(sql3);
            rs.next();
            int matiereId=rs.getInt("id");
            PreparedStatement pr2=con.prepareStatement(sql2);
            pr2.setInt(1, s.getId());
            pr2.setInt(2, matiereId);
            pr2.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(EspaceEtudeService.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
          
    
        
}
