/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ThinkPad P50
 */
public class PO {
    private String po_id, name, description, curriculum_id, is_active, code;

    public PO() {
    }

    public PO(String po_id, String name, String description, String curriculum_id, String is_active) {
        this.po_id = po_id;
        this.name = name;
        this.description = description;
        this.curriculum_id = curriculum_id;
        this.is_active = is_active;
    }

    public PO(String po_id, String name, String description, String curriculum_id, String is_active, String code) {
        this.po_id = po_id;
        this.name = name;
        this.description = description;
        this.curriculum_id = curriculum_id;
        this.is_active = is_active;
        this.code = code;
    }
    

    public PO(String name, String description, String is_active) {
        this.name = name;
        this.description = description;
        this.is_active = is_active;
    }

    public PO(String name, String description, String curriculum_id, String is_active) {
        this.name = name;
        this.description = description;
        this.curriculum_id = curriculum_id;
        this.is_active = is_active;
    }

    public PO(String po_id, String name) {
        this.po_id = po_id;
        this.name = name;
    }
    

    

    public String getPo_id() {
        return po_id;
    }

    public void setPo_id(String po_id) {
        this.po_id = po_id;
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

    public String getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(String curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    @Override
    public String toString() {
        return "PO{" + "po_id=" + po_id + ", name=" + name + ", description=" + description + ", curriculum_id=" + curriculum_id + ", is_active=" + is_active + '}';
    }
    
    
}
