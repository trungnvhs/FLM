/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Asus
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoleDAO {
    //Khai báo các thành phần xử lý DB

    static Connection cnn;//kết nối DB
    static Statement stm;//thực thi câu lệnh sql
    static PreparedStatement pstm;
    static ResultSet rs; //Lưu trữ và xử lý dữ liệu

    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs, Statement stm) {
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
    
    public static String getRoleID(String userid) {

        try {
            connect();

            String strSelect = "select * from user_role where user_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, userid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String roleid = String.valueOf(rs.getInt(1));

                return roleid;
            }
        } catch (Exception e) {
            System.out.println("getListPost" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static String getRoleName(String roleid) {

        try {
            connect();

            String strSelect = "select * from setting where setting_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, roleid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String rolename = rs.getString(3);
                return rolename;
            }
        } catch (Exception e) {
            System.out.println("getRoleName" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
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
    public static void addRole(String user_id) {
        try {
            String strUpdate = "insert user_role(role_id,user_id) values ('1',?)";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, user_id);
            //pstm.setInt(3, Integer.parseInt(UnitsInStock));
            
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Update:" + e.getMessage());
        }finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

        // Update role account
       public static void updateRole(String user_id, String role_id) {
        try {
            connect();
            String strSelect = "UPDATE user_role "
                    + "SET role_id = ? "
                    + "WHERE (user_id = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, role_id);
            pstm.setString(2, user_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateRole:" + e.getMessage());
        }finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
       
    public static String getRole(String accId) {

        try {
            connect();

            String strSelect = "SELECT setting_name\n" +
                            "FROM user_role join setting \n" +
                            "on role_id = setting_id\n" +
                            "where user_id = ? ;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, accId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String rolename = rs.getString(1);
                return rolename;
            }
        } catch (Exception e) {
            System.out.println("getRole " + e.getMessage());
        }finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }
}
