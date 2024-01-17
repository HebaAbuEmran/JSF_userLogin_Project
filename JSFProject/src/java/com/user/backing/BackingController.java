/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.backing;


import dao.DaoImp;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.User;
import service.Validation;

@ManagedBean(name = "controller", eager = true)
@SessionScoped

public class BackingController {

    Validation validate;
    DaoImp dao;
    private User loginBean = new User();
    private User search = new User();
    private List<User> users = new ArrayList<>();
    private User userOperation = new User(); 
    private String notfication;
    private String action;
    

    public BackingController() {
        validate = new Validation();
        dao = new DaoImp();
    }

    public String loginForm() {
         loginBean= validate.authenticate(loginBean);
        if (loginBean.getRole() != null) {

            if (loginBean.getRole().equals("admin")) {
                users = dao.getAll();
                return "masterAdmin";
            }
            loginBean = dao.getclinet(loginBean);
            return "client";}
         else {
            return "login";
        }
    }
    public String operation(){
        String out = "masterAdmin"; 
        
        switch(action){
            case "update" :
               userOperation = dao.getclinet(userOperation);
               out = "operation";
                break;
            case "add" :
               userOperation = new User();
               out = "operation";
               break;    
            case "delete":
                 if(dao.delete(userOperation.getId())){
                    notfication = "Employee Deleted Successfully!";
                    users = dao.getAll();
                 }
                 out = "masterAdmin";
                 break;
            case "view" :
                userOperation = dao.getclinet(userOperation);
                out = "operation";
                break;   
        }
        return out;
    }
    public String add(){
        dao.insert(userOperation);
        users = dao.getAll();
        return "masterAdmin";
    }
     public String update(){
        dao.update(userOperation);
        return "masterAdmin";
    }
     public String view(){
       return "masterAdmin";
    }
     public String searchInEmp(){
        users = dao.searchUser(search);
         return "masterAdmin";
     }
     public String reset(){
         users = dao.getAll();
         return "masterAdmin";
     }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getNotfication() {
        return notfication;
    }

    public void setNotfication(String notfication) {
        this.notfication = notfication;
    }

    public Validation getValidate() {
        return validate;
    }

    public void setValidate(Validation validate) {
        this.validate = validate;
    }

    public DaoImp getDao() {
        return dao;
    }

    public void setDao(DaoImp dao) {
        this.dao = dao;
    }

    public User getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(User loginBean) {
        this.loginBean = loginBean;
    }

    public User getUserOperation() {
        return userOperation;
    }

    public void setUserOperation(User userOperation) {
        this.userOperation = userOperation;
    }

    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public User getSearch() {
        return search;
    }

    public void setSearch(User search) {
        this.search = search;
    }
    

    
    
    

}
