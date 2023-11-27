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
public class Group {
    private String group_id,name,combo_subject,curriculum_id, display_order, is_active;

    public Group() {
    }

    public Group(String group_id) {
        this.group_id = group_id;
    }
    public Group(String group_id, String name, String combo_subject, String curriculum_id) {
        this.group_id = group_id;
        this.name = name;
        this.combo_subject = combo_subject;
        this.curriculum_id = curriculum_id;
    }
    
    public Group(String name, String combo_subject, String curriculum_id, String display_order, String is_active) {
        this.name = name;
        this.combo_subject = combo_subject;
        this.curriculum_id = curriculum_id;
        this.display_order = display_order;
        this.is_active = is_active;
    }

    public Group(String group_id, String name, String combo_subject, String curriculum_id, String display_order, String is_active) {
        this.group_id = group_id;
        this.name = name;
        this.combo_subject = combo_subject;
        this.curriculum_id = curriculum_id;
        this.display_order = display_order;
        this.is_active = is_active;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCombo_subject() {
        return combo_subject;
    }

    public void setCombo_subject(String combo_subject) {
        this.combo_subject = combo_subject;
    }

    public String getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(String curriculum_id) {
        this.curriculum_id = curriculum_id;
    }
    
    public String getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(String display_order) {
        this.display_order = display_order;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
