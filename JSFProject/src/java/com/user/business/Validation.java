/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DaoImp;
import model.User;

/**
 *
 * @author habuomran
 */
public class Validation {
   private DaoImp d ;
    
    public Validation (){
        d = new DaoImp();
    }  
    
    public User authenticate(User user){        
       return d.getclinet(user);
 
    }
       
    
    
    
}
