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
 * @author Asus
 */
public class Curriculum {
    String  curriculum_id, code, name, description, decision_id, total_credit,owner_id,creator_id,is_active;

    public Curriculum() {
        connect();
    }

    public Curriculum(String curriculum_id, String code, String name, String description, String decision_id, String total_credit, String owner_id, String creator_id, String is_active) {
        this.curriculum_id = curriculum_id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.decision_id = decision_id;
        this.total_credit = total_credit;
        this.owner_id = owner_id;
        this.creator_id = creator_id;
        this.is_active = is_active;
         connect();
    }

    public Curriculum(String code, String name, String description, String owner_id, String creator_id) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.owner_id = owner_id;
        this.creator_id = creator_id;
         connect();
    }

    public Curriculum(String curriculum_id, String code) {
        this.curriculum_id = curriculum_id;
        this.code = code;
    }

    

    public String getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(String curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDecision_id() {
        return decision_id;
    }

    public void setDecision_id(String decision_id) {
        this.decision_id = decision_id;
    }

    public String getTotal_credit() {
        return total_credit;
    }

    public void setTotal_credit(String total_credit) {
        this.total_credit = total_credit;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getIs_active() {
        return is_active;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
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
}
