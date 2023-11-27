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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class Decision {

    private String decision_id, decision_no, decision_name, decision_ApprovedDate, note, decision_CreateDate, creator_id;

    public Decision() {
        connect();
    }
     public Decision(String decision_no) {
        this.decision_no = decision_no;
    }

    public Decision(String decision_id, String decision_no, String decision_name, String decision_ApprovedDate, String note, String decision_CreateDate, String creator_id) {
        this.decision_id = decision_id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.decision_ApprovedDate = decision_ApprovedDate;
        this.decision_CreateDate = decision_CreateDate;
        this.note = note;
        this.creator_id = creator_id;
        connect();

    }

    public String getDecision_id() {
        return decision_id;
    }

    public void setDecision_id(String decision_id) {
        this.decision_id = decision_id;
    }

    public String getDecision_no() {
        return decision_no;
    }

    public void setDecision_no(String decision_no) {
        this.decision_no = decision_no;
    }

    public String getDecision_name() {
        return decision_name;
    }

    public void setDecision_name(String decision_name) {
        this.decision_name = decision_name;
    }

    public String getDecision_ApprovedDate() {
        return decision_ApprovedDate;
    }

    public void setDecision_ApprovedDate(String decision_ApprovedDate) {
        this.decision_ApprovedDate = decision_ApprovedDate;
    }

    public String getDecision_CreateDate() {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = inputFormat.parse(decision_CreateDate);
            String formattedDate = outputFormat.format(date);
             return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDecision_CreateDate(String decision_CreateDate) {
        this.decision_CreateDate = decision_CreateDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
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
