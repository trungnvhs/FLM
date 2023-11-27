/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.BaseDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Trung Quan
 */
public class User {

    private String user_id, full_name, email, mobile, user_name, password, avatar, job_title, company, status, setting_name, setting_id;

    public User() {
        connect();
    }
    
    public User(String full_name, String email, String mobile, String user_name, String avatar, String job_title, String company) {
        this.full_name = full_name;
        this.email = email;
        this.mobile = mobile;
        this.user_name = user_name;
        this.avatar = avatar;
        this.job_title = job_title;
        this.company = company;
        connect();
    }

    public User(String user_id, String full_name, String email, String mobile, String user_name, String job_title, String status, String setting_name) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.mobile = mobile;
        this.user_name = user_name;
        this.job_title = job_title;
        this.status = status;
        this.setting_name = setting_name;
        connect();
    }


    
  public User(String user_id, String full_name, String email, String mobile, String user_name, String password, String avatar, String job_title, String company, String status, String setting_name) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.mobile = mobile;
        this.user_name = user_name;
        this.password = password;
        this.avatar = avatar;
        this.job_title = job_title;
        this.company = company;
        this.status = status;
        this.setting_name = setting_name;
        connect();
    }

    public String getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(String setting_id) {
        this.setting_id = setting_id;
    }
  
   public User(String user_id, String full_name, String email, String mobile, String user_name, String password, String avatar, String job_title, String company, String status, String setting_name,String setting_id) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.mobile = mobile;
        this.user_name = user_name;
        this.password = password;
        this.avatar = avatar;
        this.job_title = job_title;
        this.company = company;
        this.status = status;
        this.setting_name = setting_name;
        this.setting_id = setting_id;
        connect();
    }
  
    public User(String user_id, String full_name, String email, String mobile, String user_name, String password, String avatar, String job_title, String company, String status) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.mobile = mobile;
        this.user_name = user_name;
        this.password = password;
        this.avatar = avatar;
        this.job_title = job_title;
        this.company = company;
        this.status = status;
        connect();
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        connect();

    }

    public User(String email) {
        this.email = email;
        connect();

    }


    
    public User(String user_id, String full_name, String email, String mobile, String job_title, String status) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.mobile = mobile;
        this.job_title = job_title;
        this.status = status;
        connect();
    }

    public String getSetting_name() {
        return setting_name;
    }

    public void setSetting_name(String setting_name) {
        this.setting_name = setting_name;
    }
    

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    //Khai báo các thành phần xử lý DB
    Connection cnn;//kết nối DB
    Statement stm;//thực thi câu lệnh sql
    PreparedStatement pstm;
    ResultSet rs; //Lưu trữ và xử lý dữ liệu

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

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", full_name=" + full_name + ", email=" + email + ", mobile=" + mobile + ", user_name=" + user_name + ", password=" + password + ", avatar=" + avatar + ", job_title=" + job_title + ", company=" + company + ", status=" + status + '}';
    }

    
}
