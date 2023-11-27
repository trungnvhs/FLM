/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.QuestionDAO.closeConnection;
import static dao.QuestionDAO.cnn;
import static dao.QuestionDAO.pstm;
import static dao.QuestionDAO.rs;
import static dao.QuestionDAO.stm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Question;
import model.Sessions;

/**
 *
 * @author quan
 */
public class SessionDAO {
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

    public static ArrayList<Sessions> getSessionListPaging(String page, String syllabusid) {
        ArrayList<Sessions> data = new ArrayList<Sessions>();
        try {
            connect();
            String strSelect = "select session_id,syllabus_id,name,details,learning_type"
                    + " from session where syllabus_id = ?  "
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, syllabusid);
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);

            rs = pstm.executeQuery();
            while (rs.next()) {

                String session_id = rs.getString(1);
                String syllabus_id = rs.getString(2);
                String name = rs.getString(3);
                String details = rs.getString(4);
                String learning_type = rs.getString(5);

                data.add(new Sessions(session_id, syllabus_id, name, details, learning_type));
            }
        } catch (SQLException e) {
            System.out.println("getSessionListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static int getTotalSession(String sys_id) {
        try {
            connect();
            String strSelect = "select count(*) from session where syllabus_id = ?  ;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sys_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getTotalSession: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }

    public static ArrayList<Sessions> getListSessionSearchNameDetails(String textsearch, String sy_id) {
        ArrayList<Sessions> data = new ArrayList<Sessions>();
        try {
            connect();
            String strSelect = "select session_id,syllabus_id,name,details,learning_type"
                    + " from session where  (name like ? OR q.details like ?)and syllabus_id = ?;  ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + textsearch + "%");
            pstm.setString(2, "%" + textsearch + "%");
            pstm.setString(3, sy_id);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String session_id = rs.getString(1);
                String syllabus_id = rs.getString(2);
                String name = rs.getString(3);
                String details = rs.getString(4);
                String learning_type = rs.getString(5);

                data.add(new Sessions(session_id, syllabus_id, name, details, learning_type));
            }
        } catch (SQLException e) {
            System.out.println("getListSessionSearchNameDetails: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static void getDeleteSession(String syllabusid) {
        try {
            connect();

            String strSelect = "DELETE FROM session WHERE (`session_id` = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, syllabusid);
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("deleteElective:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public Sessions getSessionBySessionID(String sessionid) {

        Sessions se = new Sessions();
        try {
            connect();
            String strSelect
                    = "SELECT * FROM session where session_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sessionid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                se.setSession_id(rs.getString(1));
                se.setSyllabus_id(rs.getString(2));
                se.setName(rs.getString(3));
                se.setDetails(rs.getString(4));
                se.setLearning_type(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("getSessionBySessionID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return se;
    }

}

