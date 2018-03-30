/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.util.List;
import entities.User;

/**
 *
 * @author bader
 */
public interface IUserService {
        public void ajouterUser(User u);
        public void modifierUser(User u,int id);
        public void supprimerUser(User u,int id);
        public List<User> afficherUser();
        public String recevoirMDPavecUserName(String username);
        public String recevoirMDPavecId(int id);
    
}
