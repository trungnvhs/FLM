/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ThinkPad P50
 */
public class CLO {
    private String clo_id, clo_name, clo_description, syllabus_id, is_active;

    public CLO() {
    }

    public CLO(String clo_id, String clo_name, String clo_description, String syllabus_id, String is_active) {
        this.clo_id = clo_id;
        this.clo_name = clo_name;
        this.clo_description = clo_description;
        this.syllabus_id = syllabus_id;
        this.is_active = is_active;
    }

    public CLO(String clo_id, String clo_name) {
        this.clo_id = clo_id;
        this.clo_name = clo_name;
    }
    

//    public CLO(String clo_id, String clo_name, String clo_description, String is_active) {
//        this.clo_id = clo_id;
//        this.clo_name = clo_name;
//        this.clo_description = clo_description;
//        this.is_active = is_active;
//    }

    public CLO(String clo_name, String clo_description, String syllabus_id, String is_active) {
        this.clo_name = clo_name;
        this.clo_description = clo_description;
        this.syllabus_id = syllabus_id;
        this.is_active = is_active;
    }
    
    

    public String getClo_id() {
        return clo_id;
    }

    public void setClo_id(String clo_id) {
        this.clo_id = clo_id;
    }

    public String getClo_name() {
        return clo_name;
    }

    public void setClo_name(String clo_name) {
        this.clo_name = clo_name;
    }

    public String getClo_description() {
        return clo_description;
    }

    public void setClo_description(String clo_description) {
        this.clo_description = clo_description;
    }

    public String getSyllabus_id() {
        return syllabus_id;
    }

    public void setSyllabus_id(String syllabus_id) {
        this.syllabus_id = syllabus_id;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "CLO{" + "clo_id=" + clo_id + ", clo_name=" + clo_name + ", clo_description=" + clo_description + ", syllabus_id=" + syllabus_id + ", is_active=" + is_active + '}';
    }
    
    
}
