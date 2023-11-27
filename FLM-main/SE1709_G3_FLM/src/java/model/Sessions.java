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
 * @author quan
 */
public class Sessions {

    String session_id, syllabus_id, name, details, learning_type;
    //Khai báo các thành phần xử lý DB
    Connection cnn;//kết nối DB
    Statement stm;//thực thi câu lệnh sql
    PreparedStatement pstm;
    ResultSet rs; //Lưu trữ và xử lý dữ liệu

    public Sessions() {
        connect();
    }

    

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

    public Sessions(String session_id, String syllabus_id, String name, String details, String learning_type) {
        this.session_id = session_id;
        this.syllabus_id = syllabus_id;
        this.name = name;
        this.details = details;
        this.learning_type = learning_type;
        connect();
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getSession_id() {
        return session_id;
    }

    

    public String getSyllabus_id() {
        return syllabus_id;
    }

    public void setSyllabus_id(String syllabus_id) {
        this.syllabus_id = syllabus_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLearning_type() {
        return learning_type;
    }

    public void setLearning_type(String learning_type) {
        this.learning_type = learning_type;
    }

}
