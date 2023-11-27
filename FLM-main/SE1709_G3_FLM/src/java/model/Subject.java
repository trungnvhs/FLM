/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Trung Quan
 */
public class Subject {
    private String subject_id, code, name, type, is_active, description, parent_id, group_id, code_ps;

    public Subject() {
    }

    public Subject(String subject_id, String code_ps) {
        this.subject_id = subject_id;
        this.code_ps = code_ps;
    }

      public Subject(String subject_id, String code, String name) {
        this.subject_id = subject_id;
        this.code = code;
        this.name = name;
    }
    
    public Subject(String subject_id, String code, String name, String type, String is_active, String description, String parent_id, String group_id) {
        this.subject_id = subject_id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.is_active = is_active;
        this.description = description;
        this.parent_id = parent_id;
        this.group_id = group_id;
    }
    
    public Subject(int a, String code, String name, String type, String is_active, String description, String parent_id, String group_id) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.is_active = is_active;
        this.description = description;
        this.parent_id = parent_id;
        this.group_id = group_id;
    }

     public Subject(String subject_id, String code, String name, String type, String is_active, String description, String parent_id) {
        this.subject_id = subject_id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.is_active = is_active;
        this.description = description;
        this.parent_id = parent_id;
    }
//    public Subject(String code, String name, String type, String is_active, String description, String parent_id) {
//        this.code = code;
//        this.name = name;
//        this.type = type;
//        this.is_active = is_active;
//        this.description = description;
//        this.parent_id = parent_id;
//    }    

    public Subject(String subject_id,String code, String name, String is_active, String description, String code_ps) {
        this.subject_id = subject_id;
        this.code = code;
        this.name = name;
        this.is_active = is_active;
        this.description = description;
        this.code_ps = code_ps;
    }
    
    
    
    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
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

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getCode_ps() {
        return code_ps;
    }

    public void setCode_ps(String code_ps) {
        this.code_ps = code_ps;
    }


    

}
