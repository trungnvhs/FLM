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
public class CurriculumSubject {
    private String curriculum_id, subject_id, syllabus_id, group_id, semester, no_credit,is_active;

    public CurriculumSubject() {
        connect();
    }

    public CurriculumSubject(String curriculum_id, String subject_id, String syllabus_id, String group_id, String semester, String no_credit, String is_active) {
        this.curriculum_id = curriculum_id;
        this.subject_id = subject_id;
        this.syllabus_id = syllabus_id;
        this.group_id = group_id;
        this.semester = semester;
        this.no_credit = no_credit;
        this.is_active = is_active;
        connect();
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public CurriculumSubject(String curriculum_id, String subject_id, String syllabus_id, String group_id, String semester, String no_credit) {
        this.curriculum_id = curriculum_id;
        this.subject_id = subject_id;
        this.syllabus_id = syllabus_id;
        this.group_id = group_id;
        this.semester = semester;
        this.no_credit = no_credit;
        connect();
    }
    public CurriculumSubject(String curriculum_id, String subject_id) {
        this.curriculum_id = curriculum_id;
        this.subject_id = subject_id;
        connect();
    }
    
    public CurriculumSubject(String curriculum_id, String subject_id, String group_id, String semester, String no_credit) {
        this.curriculum_id = curriculum_id;
        this.subject_id = subject_id;
        this.group_id = group_id;
        this.semester = semester;
        this.no_credit = no_credit;
        connect();
    }

    public CurriculumSubject(String curriculum_id) {
        this.curriculum_id = curriculum_id;
        connect();
    }

    public String getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(String curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSyllabus_id() {
        return syllabus_id;
    }

    public void setSyllabus_id(String syllabus_id) {
        this.syllabus_id = syllabus_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getNo_credit() {
        return no_credit;
    }

    public void setNo_credit(String no_credit) {
        this.no_credit = no_credit;
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
