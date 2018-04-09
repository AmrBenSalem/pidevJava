/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava;

import entities.Avis;
import entities.Event;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import services.ServiceAvis;
import services.ServiceEvent;

/**
 *
 * @author Liwa
 */
public class TestEvents {
    public static void main(String[] args) throws SQLException {
        ServiceAvis sa = new ServiceAvis();
        //Avis a = new Avis( 0, 2, 3);
        //sa.addAvis(1, 2,1);
        //List <Avis> listavis = new ArrayList<Avis>();
        //listavis=sa.consulterAvis(1);
        //System.out.println(listavis.size());
        ServiceEvent se = new ServiceEvent();
        List<Event> le = new ArrayList<Event>();
        le=se.listEventsUser(2);
        System.out.println(le.size());
        
}
}
