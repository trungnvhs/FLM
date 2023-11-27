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
import model.Curriculum;
import model.User;

/**
 *
 * @author Asus
 */
public class CurriculumDAO {
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

    public static boolean checkCurriculumCode(String code) {
        try {
            connect();
            String strSelect = "select * from curriculum where code =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkAccount:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static void addCurriculum(String code, String name, String ownerid, String description, String creator) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`curriculum` (`code`, `name`, `description`, `owner_id`, `creator_id`,`is_active`)"
                    + " VALUES (?, ?, ?, ?, ?,b'0');";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, code);
            pstm.setString(2, name);
            pstm.setString(3, description);
            pstm.setString(4, ownerid);
            pstm.setString(5, creator);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("AddCurriculum:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static ArrayList<Curriculum> getListCurriculum(String id) {
        ArrayList<Curriculum> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "select * from curriculum where owner_id =? or creator_id =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.setString(2, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String curriculum_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                String decision_id = String.valueOf(rs.getInt(5));
                String total_credit = String.valueOf(rs.getInt(6));
                String owner_id = String.valueOf(rs.getInt(7));
                String creator_id = String.valueOf(rs.getInt(8));
                String is_active = rs.getString(9);
                data.add(new Curriculum(curriculum_id, code, name, description, decision_id, total_credit, owner_id, creator_id, is_active));
            }
        } catch (Exception e) {
            System.out.println("getListCurriculum" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static ArrayList<Curriculum> getListCurriculum1() {
        ArrayList<Curriculum> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "select curriculum_id, code from curriculum  ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String curriculum_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                data.add(new Curriculum(curriculum_id, code));
            }
        } catch (Exception e) {
            System.out.println("getListCurriculum1" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static String getDecisionNoDate(String decisionid) {

        try {
            connect();
            String strSelect = "select * from decision where decision_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, decisionid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String decisionNo = rs.getString(2);
                String decisionDate = rs.getString(4);
                return decisionNo + " dated " + decisionDate;
            }
        } catch (Exception e) {
            System.out.println("getDecisionNo" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static ArrayList<Curriculum> getListCurriculumSearch(String textsearch, String id) {
        ArrayList<Curriculum> data = new ArrayList<Curriculum>();
        try {
            connect();
            String strSelect = "SELECT * FROM curriculum where code like ? or name like ? and owner_id = ? or creator_id = ?  ;  ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + textsearch + "%");
            pstm.setString(2, "%" + textsearch + "%");
            pstm.setString(3, id);
            pstm.setString(4, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String curriculum_id = rs.getString(1);
                String code = rs.getString(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                String decision_id = rs.getString(5);
                String total_credit = rs.getString(6);
                String owner_id = rs.getString(7);
                String creator_id = rs.getString(8);
                String is_active = rs.getString(9);
                data.add(new Curriculum(curriculum_id, code, name, description, decision_id, total_credit, owner_id, creator_id, is_active));
            }
        } catch (SQLException e) {
            System.out.println("getCurriculumListBySearch: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static Curriculum getCurriculumByCurriculumID(String id) {
        Curriculum cu = new Curriculum();
        try {
            connect();
            String strSelect
                    = "SELECT * FROM curriculum where curriculum_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                cu.setCurriculum_id(rs.getString(1));
                cu.setCode(rs.getString(2));
                cu.setName(rs.getString(3));
                cu.setDescription(rs.getString(4));
                cu.setDecision_id(rs.getString(5));
                cu.setTotal_credit(rs.getString(6));
                cu.setOwner_id(rs.getString(7));
                cu.setCreator_id(rs.getString(8));
                cu.setIs_active(rs.getString(9));
            }
        } catch (SQLException e) {
            System.out.println("getCurriculumByCurriculumID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return cu;
    }

    public static Curriculum getCurriculumByCurriculumID1(String id) {
        Curriculum cu = new Curriculum();
        try {
            connect();
            String strSelect
                    = "SELECT * FROM curriculum where curriculum_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                cu.setCurriculum_id(rs.getString(1));
                cu.setCode(rs.getString(2));
                cu.setName(rs.getString(3));
                cu.setDescription(rs.getString(4));
                cu.setDecision_id(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("getCurriculumByCurriculumID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return cu;
    }

    public static String getTotalCredit(String curriculumid) {

        try {
            connect();
            String strSelect = "SELECT SUM(no_credit) FROM curriculum_subject where curriculum_id =?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String totalcredit = rs.getString(1);

                return totalcredit;
            }
        } catch (Exception e) {
            System.out.println("getTotalCredit" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static ArrayList<Curriculum> getCurriculumListPaging(String page, String creator) {
        ArrayList<Curriculum> data = new ArrayList<Curriculum>();
        try {
            connect();
            String strSelect = "SELECT * FROM curriculum where creator_id = ? or owner_id = ? order by curriculum_id limit ?, 10 ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, creator);
            pstm.setString(2, creator);

            pstm.setInt(3, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String curriculum_id = rs.getString(1);
                String code = rs.getString(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                String decision_id = rs.getString(5);
                String total_credit = rs.getString(6);
                String owner_id = rs.getString(7);
                String creator_id = rs.getString(8);
                String is_active = rs.getString(9);
                data.add(new Curriculum(curriculum_id, code, name, description, decision_id, total_credit, owner_id, creator_id, is_active));
            }
        } catch (SQLException e) {
            System.out.println("getCurriculumListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static int getTotalCurriculum() {
        try {
            connect();
            String strSelect = "select count(*) from curriculum";
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

    public static void editCurriculum(String id, String code, String name, String description, String decisionid, String totalcredit, String ownerid, String isactive) {
        try {
            connect();
            String strAdd = "UPDATE `swp391_g3_flm`.`curriculum`"
                    + " SET code = ? ,name = ? , description = ?  , total_credit = ? , owner_id = ?, is_active= b? "
                    + "Where curriculum_id = ?";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, code);
            pstm.setString(2, name);
            pstm.setString(3, description);
            pstm.setString(4, totalcredit);
            pstm.setString(5, ownerid);

            pstm.setString(6, isactive);
            pstm.setString(7, id);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("editCurriculum:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static ArrayList<Curriculum> getCurriculumNotHaveDecision() {
        ArrayList<Curriculum> data = new ArrayList<Curriculum>();
        try {
            connect();
            String strSelect
                    = "SELECT * FROM curriculum where decision_id is null;";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String curriculum_id = rs.getString(1);
                String code = rs.getString(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                String decision_id = rs.getString(5);
                String total_credit = rs.getString(6);
                String owner_id = rs.getString(7);
                String creator_id = rs.getString(8);
                String is_active = rs.getString(9);
                data.add(new Curriculum(curriculum_id, code, name, description, decision_id, total_credit, owner_id, creator_id, is_active));
            }
        } catch (SQLException e) {
            System.out.println("getCurriculumNotHaveDecision: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static void addDecisionForCurriculum(String id, String curriculumdecion) {
        try {
            connect();
            String strAdd = "UPDATE `swp391_g3_flm`.`curriculum` SET decision_id = ? Where curriculum_id = ?";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, id);
            pstm.setString(2, curriculumdecion);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("addDecisionForCurriculum:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static ArrayList<User> getStaff() {
        ArrayList<User> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "select * from user join user_role on user.user_id = user_role.user_id where role_id = 7  ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String password = rs.getString(6);
                String avatar = rs.getString(7);
                String job_title = rs.getString(8);
                String company = rs.getString(9);
                String status = rs.getString(10);
                data.add(new User(user_id, full_name, email, mobile, user_name, password, avatar, job_title, company, status));
            }
        } catch (Exception e) {
            System.out.println("getStaff" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static String getRoleId(String id) {

        try {
            connect();
            String strSelect = "select * from user_role  where user_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String role_id = rs.getString(1);
                return role_id;
            }
        } catch (Exception e) {
            System.out.println("getRoleId" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static ArrayList<Curriculum> getListCurriculumDefault() {
        ArrayList<Curriculum> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "select * from curriculum where is_active =1 and decision_id is not null  ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String curriculum_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                String decision_id = String.valueOf(rs.getInt(5));
                String total_credit = String.valueOf(rs.getInt(6));
                String owner_id = String.valueOf(rs.getInt(7));
                String creator_id = String.valueOf(rs.getInt(8));
                String is_active = rs.getString(9);
                data.add(new Curriculum(curriculum_id, code, name, description, decision_id, total_credit, owner_id, creator_id, is_active));
            }
        } catch (Exception e) {
            System.out.println("getListCurriculumDefault" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static String getAssigneeName(String assigneeid) {

        try {
            connect();
            String strSelect = "select * from user where user_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, assigneeid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String fullnameassignee = rs.getString(2);

                return fullnameassignee;
            }
        } catch (Exception e) {
            System.out.println("getAssigneeName" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static String getCreatorName(String creatorid) {

        try {
            connect();
            String strSelect = "select * from user where user_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, creatorid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String fullnamecreator = rs.getString(2);

                return fullnamecreator;
            }
        } catch (Exception e) {
            System.out.println("getCreatorName" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static ArrayList<Curriculum> getListCurriculumStatus(String filterStatus, String user_id) {
        ArrayList<Curriculum> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "select * from curriculum where is_active =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, filterStatus);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String curriculum_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                String decision_id = String.valueOf(rs.getInt(5));
                String total_credit = String.valueOf(rs.getInt(6));
                String owner_id = String.valueOf(rs.getInt(7));
                String creator_id = String.valueOf(rs.getInt(8));
                String is_active = rs.getString(9);
                data.add(new Curriculum(curriculum_id, code, name, description, decision_id, total_credit, owner_id, creator_id, is_active));
            }
        } catch (Exception e) {
            System.out.println("getListCurriculumByStatus" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

}
