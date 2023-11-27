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
public class Plo_PoDAO {

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

    public boolean check(String plo, String po) {
        try {
            connect();
            String sql = "select * FROM po_plo pp\n"
                    + "where pp.po_id = ? and pp.plo_id = ?;";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, po);
            pstm.setString(2, plo);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Check_PO_PLO :" + ex.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public void deleteMappingPO_PLO(String plo, String po) {
        try {
            connect();
            String sqlDetele = "DELETE FROM `po_plo`\n"
                    + "WHERE po_id = ? AND plo_id = ?";
            pstm = cnn.prepareStatement(sqlDetele);
            pstm.setString(1, po);
            pstm.setString(2, plo);
            pstm.execute();

        } catch (Exception ex) {
            System.out.println("deleteMappingPO_PLO :" + ex.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public void addMappingPLO_PO(String plo, String po) {

        try {
            connect();
            String sqlUpdate = "INSERT INTO `po_plo` (`po_id`, `plo_id`) VALUES (?, ?);";
            pstm = cnn.prepareStatement(sqlUpdate);
            pstm.setString(1, po);
            pstm.setString(2, plo);
            pstm.execute();

        } catch (Exception ex) {
            System.out.println("addMappingPLO_PO :" + ex.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }

    }

}
