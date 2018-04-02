/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entities.Event;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Liwa
 */
public interface IEvent {
    public void ajouterEvent(Event e)throws SQLException;
    public  void updateEvent( Event e,int id)throws SQLException;
    public  void supprimerEvent( int id)throws SQLException;
    public  List<Event> selectEvent()throws SQLException;
    public Event getEvent(int id);
}
