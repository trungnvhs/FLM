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
 * @author hp
 */
public class Pre_requisite {

    private int subjectId;
    private int preId;
    private String code;
    private String description;
    private int curriculum_id;

    public Pre_requisite(int subjectId, int preId, String description, int curriculum_id) {
        this.subjectId = subjectId;
        this.preId = preId;
        this.description = description;
        this.curriculum_id = curriculum_id;
        connect();
    }

    //Khai báo các thành phần xử lý DB
    Connection cnn;//kết nối DB
    Statement stm;//thực thi câu lệnh sql
    PreparedStatement pstm;
    ResultSet rs; //Lưu trữ và xử lý dữ liệu

    public Pre_requisite(String description) {
        this.description = description;
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

    public Pre_requisite() {
    }

    public Pre_requisite(int subjectId, int preId, String code) {
        this.subjectId = subjectId;
        this.preId = preId;
        this.code = code;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getPreId() {
        return preId;
    }

    public void setPreId(int preId) {
        this.preId = preId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Pre_requisite{" + "subjectId=" + subjectId + ", preId=" + preId + ", code=" + code + '}';
    }

}
