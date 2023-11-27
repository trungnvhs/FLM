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

/**
 *
 * @author ThinkPad P50
 */
public class Clo_PloDAO {
    //Khai báo các thành phần xử lý DB
    static Connection cnn;//kết nối DB
    static Statement stm;//thực thi câu lệnh sql
    static PreparedStatement pstm;
    static ResultSet rs; //Lưu trữ và xử lý dữ liệu

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
    
    public boolean check(String clo, String plo) {
        try {
            connect();
            String sql = "select * FROM clo_plo cp\n"
                    + "where cp.clo_id = ? and cp.plo_id = ?;";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, clo);
            pstm.setString(2, plo);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Check_CLO_PLO :" + ex.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }
public void deleteMappingCLO_PLO(String clo, String plo) {
        try {
            connect();
            String sqlDetele = "DELETE FROM `clo_plo`\n"
                    + "WHERE clo_id = ? AND plo_id = ?";
            pstm = cnn.prepareStatement(sqlDetele);
            pstm.setString(1, clo);
            pstm.setString(2, plo);
            pstm.execute();

        } catch (Exception ex) {
            System.out.println("deleteMappingCLO_PLO :" + ex.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public void addMappingPLO_CLO(String clo, String plo) {

        try {
            connect();
            String sqlUpdate = "INSERT INTO `clo_plo` (`clo_id`, `plo_id`) VALUES (?, ?);";
            pstm = cnn.prepareStatement(sqlUpdate);
            pstm.setString(1, clo);
            pstm.setString(2, plo);
            pstm.execute();

        } catch (Exception ex) {
            System.out.println("addMappingPLO_CLO :" + ex.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }

    }
}
