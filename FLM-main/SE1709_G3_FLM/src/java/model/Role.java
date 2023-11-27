package model;

import dao.BaseDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Asus
 */
public class Role {

    String role_id, user_id, is_active;

    public Role() {
        connect();

    }

    public Role(String role_id) {
        this.role_id = role_id;
        connect();

    }
     public Role(String role_id, String user_id) {
        this.role_id = role_id;
        this.user_id = user_id;
        connect();
    }

    public Role(String role_id, String user_id, String is_active) {
        this.role_id = role_id;
        this.user_id = user_id;
        this.is_active = is_active;
        connect();

    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
    Connection cnn;

    private void connect() {
        try {
            cnn = (new BaseDAO()).connection;
            if (cnn != null) {
                System.out.println("Connect success");
            } else {
                System.out.println("Connect fail");
            }
        } catch (Exception e) {

        }
    }

}
