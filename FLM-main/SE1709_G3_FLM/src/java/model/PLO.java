/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI
 */
public class PLO {

    private String plo_id, name, description, curriculum_id, is_Active;

    public PLO() {
    }

    public PLO(String name, String description, String curriculum_id) {
        this.name = name;
        this.description = description;
        this.curriculum_id = curriculum_id;
    }

    public PLO(String plo_id, String name, String description, String curriculum_id) {
        this.plo_id = plo_id;
        this.name = name;
        this.description = description;
        this.curriculum_id = curriculum_id;
    }

    public PLO(String plo_id, String name, String description, String curriculum_id, String is_Active) {
        this.plo_id = plo_id;
        this.name = name;
        this.description = description;
        this.curriculum_id = curriculum_id;
        this.is_Active = is_Active;
    }

    public String getPlo_id() {
        return plo_id;
    }

    public void setPlo_id(String plo_id) {
        this.plo_id = plo_id;
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

    public String getIs_Active() {
        return is_Active;
    }

    public void setIs_Active(String is_Active) {
        this.is_Active = is_Active;
    }

}
