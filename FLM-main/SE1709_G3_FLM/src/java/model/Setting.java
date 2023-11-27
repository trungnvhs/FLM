/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.BaseDAO;
import java.sql.Connection;

/**
 *
 * @author MSI
 */
public class Setting {

    private String setting_id;
    private String setting_name;
    private String setting_type;
    private String setting_value;
    private String setting_order;

    Connection cnn;

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
    public Setting() {
         connect();
    }

    public Setting(String setting_id, String setting_name) {
        this.setting_id = setting_id;
        this.setting_name = setting_name;
        connect();
    }
    public Setting(String setting_id, String setting_name, String setting_type, String setting_value, String setting_order) {
        this.setting_id = setting_id;
        this.setting_name = setting_name;
        this.setting_type = setting_type;
        this.setting_value = setting_value;
        this.setting_order = setting_order;
        connect();
    }

    public String getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(String setting_id) {
        this.setting_id = setting_id;
    }

    public String getSetting_name() {
        return setting_name;
    }

    public void setSetting_name(String setting_name) {
        this.setting_name = setting_name;
    }

    public String getSetting_type() {
        return setting_type;
    }

    public void setSetting_type(String setting_type) {
        this.setting_type = setting_type;
    }

    public String getSetting_value() {
        return setting_value;
    }

    public void setSetting_value(String setting_value) {
        this.setting_value = setting_value;
    }

    public String getSetting_order() {
        return setting_order;
    }

    public void setSetting_order(String setting_order) {
        this.setting_order = setting_order;
    }

    @Override
    public String toString() {
        return "Setting{" + "setting_id=" + setting_id + ", setting_name=" + setting_name + ", setting_type=" + setting_type + ", setting_value=" + setting_value + ", setting_order=" + setting_order + '}';
    }
     
    

    
}
