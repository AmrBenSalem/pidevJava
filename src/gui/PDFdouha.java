/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Colocation;
import services.ColocationCRUD;

/**
 *
 * @author douha
 */
public class PDFdouha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Colocation c1 = new Colocation(100,"colocation","type","meuble","description","photo","photo1","photo2","ville",2,2552,"nature",3,4);
        
        
        // TODO code application logic here
     ColocationCRUD col = new ColocationCRUD();
  //   col.ajouterColocation(c1);
        
          
//     for(Colocation c : col.afficherColocation()){
//         System.out.println(c);
//     }
     
   //col.supprimerColocation(c1,83);
         //col.modifierColocation(c1, 83);
    }
    
    
}
