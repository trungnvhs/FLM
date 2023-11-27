/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Setting;

/**
 *
 * @author MSI
 */
public class SettingDAO extends BaseDAO {

    public void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs, Statement stm) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public ArrayList<Setting> getListSetting() {
        ArrayList<Setting> data = new ArrayList<Setting>();
        try {
            connect();
            String strSelect = "SELECT * FROM setting;";
             pstm = connection.prepareStatement(strSelect);
             rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(3);
                String type = rs.getString(2);
                String value = rs.getString(4);
                String displayOrder = rs.getString(5);
                data.add(new Setting(id, name, type, value, displayOrder));
            }
        } catch (Exception e) {
            System.out.println("getListSetting:" + e.getMessage());
        }finally{
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public ArrayList<Setting> getSettingBySettingName(String str) {
        ArrayList<Setting> data = new ArrayList<Setting>();
        Setting s = new Setting();
        try {
            connect();
            String strSelect
                    = "SELECT * FROM setting where setting_name like ?;";
             pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%"+ str + "%");
             rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(3);
                String type = rs.getString(2);
                String value = rs.getString(4);
                String displayOrder = rs.getString(5);
                data.add(new Setting(id, name, type, value, displayOrder));
            }
        } catch (SQLException e) {
            System.out.println("getSettingBySettingID: " + e.getMessage());
        }finally{
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }


    public ArrayList<String> getAllType() {
        ArrayList<String> settings = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT DISTINCT setting_type FROM setting;";
            pstm = connection.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String setting = rs.getString("setting_type");
                settings.add(setting);
            }
        } catch (SQLException e) {
            System.out.println("loi getAllType: " + e.getMessage());
        }finally{
            closeConnection(cnn, pstm, rs, stm);
        }
        return settings;
    }

    public Setting getSettingBySettingID(String id) {
        Setting s = new Setting();
        try {
            connect();
            String strSelect
                    = "SELECT * FROM setting where setting_id = ?;";
             pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                s.setSetting_id(rs.getString(1));
                s.setSetting_name(rs.getString(3));
                s.setSetting_type(rs.getString(2));
                s.setSetting_value(rs.getString(4));
                s.setSetting_order(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("getSettingBySettingID: " + e.getMessage());
        }finally{
            closeConnection(cnn, pstm, rs, stm);
        }
        return s;
    }

    public void UpdateSetting(String name, String value, String order,String id) {
        try {
            connect();
            String sql = "UPDATE swp391_g3_flm.setting "
                    + "SET setting_name = ?,\n"
                    + "setting_value = ?, \n"
                    + "display_order = ? \n"
                    + "WHERE setting_id = ?;";

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, value);
            pstm.setString(3, order);
            pstm.setString(4, id);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("UpdateProfile " + e.getMessage());
        }finally{
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public ArrayList<Setting> getSettingByType(String type) {
        ArrayList<Setting> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT *\n"
                    + "FROM setting\n"
                    + "WHERE setting_type = ?;";
             pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, type);
             rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(3);
                String value = rs.getString(4);
                String displayOrder = rs.getString(5);
                data.add(new Setting(id, name, type, value, displayOrder));
            }
        } catch (SQLException e) {
            System.out.println("getSettingByType: " + e.getMessage());
        }finally{
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }
    //Khai báo các thành phần xử lý DB

    static Connection cnn;//kết nối DB
    static Statement stm;//thực thi câu lệnh sql
    static PreparedStatement pstm;
    static ResultSet rs; //Lưu trữ và xử lý dữ liệu

    // Get list Setting
    public ArrayList<Setting> getSettingListName() {
        ArrayList<Setting> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select * from setting ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String setting_id = String.valueOf(rs.getInt(1));
                String setting_name = rs.getString(3);
                
                list.add(new Setting(setting_id, setting_name));
            }
        } catch (Exception e) {
            System.err.println("getSettingListName: " + e.getMessage());
        }finally{
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    private static void connect() {
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
