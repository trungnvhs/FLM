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
import model.Decision;

/**
 *
 * @author Asus
 */
public class DecisionDAO {

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
            System.out.println("not connect");
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

    public static boolean checkDecisionNo(String decisionno) {
        try {
            connect();
            String strSelect = "select * from decision where decision_no =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, decisionno);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkDecisionNo:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static void addDecision(String no, String name, String note, String createdate, String createdid) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`decision` (`decision_no`, `decision_name`, `note`,`decision_CreateDate`, `creator_id`)"
                    + " VALUES (?, ?, ?, ?,?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, no);
            pstm.setString(2, name);
            pstm.setString(3, note);
            pstm.setString(4, createdate);
            pstm.setString(5, createdid);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("AddDecision:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static int getTotalDecision() {
        try {
            connect();
            String strSelect = "select count(*) from decision";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getSubByID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }

    public static ArrayList<Decision> getDecisionListPaging(String page) {
        ArrayList<Decision> data = new ArrayList<Decision>();
        try {
            connect();
            String strSelect = "SELECT * FROM decision  "
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            
            pstm.setInt(1, (Integer.parseInt(page) - 1) * 10);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String decision_id = rs.getString(1);
                String decision_no = rs.getString(2);
                String decision_name = rs.getString(3);
                String decision_approveddate = rs.getString(4);
                String note = rs.getString(5);
                String decision_date = rs.getString(6);
                String created_id = rs.getString(7);

                data.add(new Decision(decision_id, decision_no, decision_name, decision_approveddate, note, decision_date, created_id));
            }
        } catch (SQLException e) {
            System.out.println("getDecisionListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static ArrayList<Decision> getListDecisionSearchNoName(String textsearch, String userid) {
        ArrayList<Decision> data = new ArrayList<Decision>();
        try {
            connect();
            String strSelect = "SELECT * FROM decision where decision_no like ? OR decision_name like ? and creator_id = ? ;  ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + textsearch + "%");
            pstm.setString(2, "%" + textsearch + "%");
            pstm.setString(3, userid);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String decision_id = rs.getString(1);
                String decision_no = rs.getString(2);
                String decision_name = rs.getString(3);
                String decision_approveddate = rs.getString(4);
                String note = rs.getString(5);
                String decision_createdate = rs.getString(6);
                String created_id = rs.getString(7);

                data.add(new Decision(decision_id, decision_no, decision_name, decision_approveddate, note, decision_createdate, created_id));
            }
        } catch (SQLException e) {
            System.out.println("getDecisionListByNoName: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static Decision getDecisionByDecisionID(String id) {
        Decision de = new Decision();
        try {
            connect();
            String strSelect
                    = "SELECT * FROM decision where decision_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                de.setDecision_id(rs.getString(1));
                de.setDecision_no(rs.getString(2));
                de.setDecision_name(rs.getString(3));
                de.setDecision_ApprovedDate(rs.getString(4));
                de.setNote(rs.getString(5));
                de.setDecision_CreateDate(rs.getString(6));
                de.setCreator_id(rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.println("getDecisionByDecisionID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return de;
    }

    public static void updateDecision(String id, String no, String name, String approveddate, String note, String createdate, String creatorid) {
        try {
            connect();
            String strAdd = "UPDATE `swp391_g3_flm`.`decision`"
                    + " SET decision_no = ? ,decision_name = ? , decision_ApprovedDate = ? , note = ? , decision_CreateDate = ? , creator_id = ?"
                    + "Where decision_id = ?";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, no);
            pstm.setString(2, name);
            pstm.setString(3, approveddate);
            pstm.setString(4, note);
            pstm.setString(5, createdate);
            pstm.setString(6, creatorid);
            pstm.setString(7, id);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("editDecision:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static String getCreatorName(String creatorid) {

        try {
            connect();
            String strSelect = "select * from user where user_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, creatorid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String fullnamecreator = rs.getString(5);

                return fullnamecreator;
            }
        } catch (Exception e) {
            System.out.println("getCreatorName" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static ArrayList<Decision> getListDecisionSort(String sort, String user_id) {
        ArrayList<Decision> data = new ArrayList<Decision>();
        try {
            connect();
            String strSelect = "select * from decision   where creator_id =? order by ? asc;  ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, user_id);
            pstm.setString(2, sort);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String decision_id = rs.getString(1);
                String decision_no = rs.getString(2);
                String decision_name = rs.getString(3);
                String decision_approveddate = rs.getString(4);
                String note = rs.getString(5);
                String decision_createdate = rs.getString(6);
                String created_id = rs.getString(7);

                data.add(new Decision(decision_id, decision_no, decision_name, decision_approveddate, note, decision_createdate, created_id));
            }
        } catch (SQLException e) {
            System.out.println("getDecisionListBySort: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public void removeDecision(String decisionid) {
        try {
            connect();
            String strAdd = " DELETE FROM `swp391_g3_flm`.`decision` WHERE (`decision_id` = ?); ";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, decisionid);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("removeDecision:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
    public void updateDecision(String decisionid) {
        try {
            connect();
            String strAdd = "UPDATE `swp391_g3_flm`.`curriculum` SET `decision_id` = NULL WHERE (`decision_id` = ?); "
                    + "UPDATE `swp391_g3_flm`.`syllabus` SET `decision_id` = NULL WHERE (`decision_id` = ?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, decisionid);
            pstm.setString(2, decisionid);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateDecision:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
}
