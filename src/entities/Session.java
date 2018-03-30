/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author bader
 */
public class Session {
     private static int IdThisUser=0;
     private static Date DateThisDay;    
     public static int idLocal;
     public static int idAnnonce;

   
    public static Date getDateThisDay() {
        return DateThisDay;
    }

    public static void setDateThisDay(Date DateThisDay) {
        Session.DateThisDay = DateThisDay;
    }

    public static int getIdThisUser() {
        return IdThisUser;
    }

    public static void setIdThisUser(int IdThisUser) {
        Session.IdThisUser = IdThisUser;
    }
    
     
}
