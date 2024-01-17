/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author habuomran
 */
public class DaoImp {

    public DaoImp() {
    }

    ResultSet resultset;
    Statement statement;

    PreparedStatement ps;

    private Connection newConnection() throws SQLException {
        Connection con = null;
        if (con != null) {
            return con;
        } else {
            try {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project", "postgres", "root");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }

        return con;

    }

    public List<User> getAll() {

        List<User> list = null;
        User user;

        try {
            list = new ArrayList<>();
            String sql = "SELECT * FROM  users";
            Connection connection = newConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                user = new User();
                user.setId(resultset.getInt("id"));
                user.setUserName(resultset.getString("userName"));
                user.setPassWord(resultset.getString("passWord"));
                user.setRole(resultset.getString("role"));
                user.setName(resultset.getString("name"));
                user.setAge(resultset.getInt("age"));
                user.setSkill(resultset.getString("skill"));
                user.setAdress(resultset.getString("address"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> searchUser(User user) {

        List<User> list = null;

        try {
            list = new ArrayList<>();
            String sql = "SELECT * FROM  users ";
            if (user.getId() != 0) {
                sql += " where id =" + user.getId() + " ";
            }
            if (user.getName() != null) {
                if (sql.contains("where")) {
                    sql += " and name like '%" + user.getName() + "%' ";
                } else {
                    sql += " where name like '%" + user.getName() + "%' ";
                }
            }
            if (user.getAge() != 0) {
                if (sql.contains("where")) {
                    sql += " and age =" + user.getAge()   ;
                } else {
                    sql += " where age =" +  user.getAge() ;
                }
            }
            if (user.getAdress() != null) {
                if (sql.contains("where")) {
                    sql += " and address like '%" + user.getAdress() + "%' ";
                } else {
                    sql += " where address like '%" + user.getAdress() + "%' ";
                }
            }
            if (user.getSkill() != null) {
                if (sql.contains("where")) {
                    sql += " and skill like '%" + user.getSkill() + "%' ";
                } else {
                    sql += " where skill like '%" + user.getSkill() + "%' ";
                }
            }

            Connection connection = newConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                user = new User();
                user.setId(resultset.getInt("id"));
                user.setUserName(resultset.getString("userName"));
                user.setPassWord(resultset.getString("passWord"));
                user.setRole(resultset.getString("role"));
                user.setName(resultset.getString("name"));
                user.setAge(resultset.getInt("age"));
                user.setSkill(resultset.getString("skill"));
                user.setAdress(resultset.getString("address"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public User getclinet(User user) {

        try {
            String sql = "SELECT * FROM users WHERE username='" + user.getUserName() + "'"
                    + "and password='" + user.getPassWord() + "'";
            Connection connection = newConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            if (resultset.next()) {
                user.setId(resultset.getInt("id"));
                user.setUserName(resultset.getString("username"));
                user.setPassWord(resultset.getString("password"));
                user.setRole(resultset.getString("role"));
                user.setName(resultset.getString("name"));
                user.setAge(resultset.getInt("age"));
                user.setSkill(resultset.getString("skill"));
                user.setAdress(resultset.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    public boolean insert(User u) {
        boolean flag = false;
        try {
            String sql = "INSERT INTO users(username,password,role,name,age,skill,address)VALUES"
                    + "('" + u.getUserName() + "','" + u.getPassWord()
                    + "','" + u.getRole() + "','" + u.getName() + "','" + u.getAge() + "','" + u.getSkill() + "','" + u.getAdress() + "')";
            Connection connection = newConnection();
            ps = connection.prepareStatement(sql);
            ps.execute();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;

    }

    public boolean update(User u) {
        boolean flag = false;

        try {
            String sql = "UPDATE users SET username='" + u.getUserName() + "', "
                    + "password='" + u.getPassWord() + "', role='" + u.getRole() + "', name = '" + u.getName() + "', "
                    + "age = '" + u.getAge() + "', skill = '" + u.getSkill() + "', address = '" + u.getAdress() + "' WHERE id =" + u.getId();

            Connection connection = newConnection();
            ps = connection.prepareStatement(sql);
            ps.execute();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;

    }

    public boolean delete(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM users where id='" + id + "'";
            Connection connection = newConnection();
            ps = connection.prepareStatement(sql);
            ps.execute();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
