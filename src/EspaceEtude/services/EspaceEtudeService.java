/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.services;

import EspaceEtude.entities.Matiere;
import EspaceEtude.entities.Section;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
}
