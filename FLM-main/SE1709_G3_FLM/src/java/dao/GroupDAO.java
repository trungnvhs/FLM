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
import model.Group;

/**
 *
 * @author quan
 */
public class GroupDAO {

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

    public static boolean checkgroupid(String groupid) {
        try {
            connect();

            String strSelect = "select * from `group` where group_id =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, groupid);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkAccount:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static boolean checkgroupName(String name) {
        try {
            connect();

            String strSelect = "select * from `group` where name =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, name);
            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkAccount:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static boolean checkGroupNameOfCur(String name, String curID) {
        try {
            connect();

            String strSelect = "select * from `group` where name =? and curriculum_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, name);
            pstm.setString(2, curID);
            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkAccount:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static void insertGroup(Group g) {
        try {
            connect();

            String strSelect = "INSERT INTO `swp391_g3_flm`.`group` (`name`, `combo_subject`, `curriculum_id`, `display_order`, `is_active`)"
                    + " VALUES (?, ?, ?, ?, b?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, g.getName());
            pstm.setString(2, g.getCombo_subject());
            pstm.setString(3, g.getCurriculum_id());
            pstm.setString(4, g.getDisplay_order());
            pstm.setString(5, g.getIs_active());
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("checkAccount:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static ArrayList<Group> getGroupListPaging(String page, String curID, String combo_sub) {
        ArrayList<Group> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * FROM swp391_g3_flm.`group`\n"
                    + "where curriculum_id = ? and combo_subject = ?\n"
                    + "order by display_order\n"
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curID);
            pstm.setString(2, combo_sub);
            pstm.setInt(3, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String group_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String combo_subject = rs.getString(3);
                String curriculum_id = String.valueOf(rs.getInt(4));
                String is_active = String.valueOf(rs.getInt(6));
                String display_order = String.valueOf(rs.getInt(5));

                list.add(new Group(group_id, name, combo_subject, curriculum_id, display_order, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getGroupListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static int getTotalGroup(String curID) {
        try {
            connect();
            String strSelect = "select count(*) from `group`"
                    + "where curriculum_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getTotalGroup: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }

    public static Group getGroupByID(String groupID) {
        try {
            connect();

            String strSelect = "select * from `group` where group_id =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, groupID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String group_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String combo_subject = rs.getString(3);
                String curriculum_id = String.valueOf(rs.getInt(4));
                String is_active = String.valueOf(rs.getInt(6));
                String display_order = String.valueOf(rs.getInt(5));

                return new Group(group_id, name, combo_subject, curriculum_id, display_order, is_active);
            }
        } catch (SQLException e) {
            System.out.println("checkAccount:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static Group getGroupByName(String s) {
        try {
            connect();

            String strSelect = "select * from `group` where name =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, s);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String group_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String combo_subject = rs.getString(3);
                String curriculum_id = String.valueOf(rs.getInt(4));
                String is_active = String.valueOf(rs.getInt(6));
                String display_order = String.valueOf(rs.getInt(5));

                return new Group(group_id, name, combo_subject, curriculum_id, display_order, is_active);
            }
        } catch (SQLException e) {
            System.out.println("checkAccount:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static void updateCur_Sub(String groupID) {
        try {
            connect();
            String strDelete = "UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = null \n"
                    + "WHERE (`group_id` = ?);";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setString(1, groupID);
            pstm.execute();
        } catch (SQLException e) {
            System.err.println("updateCur_Sub: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void deleteGroup(String groupID) {
        try {
            updateCur_Sub(groupID);
            connect();
            String strDelete = "DELETE FROM `swp391_g3_flm`.`group` WHERE (`group_id` = ?);";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setString(1, groupID);
            pstm.execute();
        } catch (SQLException e) {
            System.err.println("deleteGroup: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static ArrayList<Group> getListElective(String curriculumid) {
        ArrayList<Group> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT `group`.group_id,`group`.name,subject.code,subject.name "
                    + "FROM swp391_g3_flm.curriculum_subject join subject "
                    + "on curriculum_subject.subject_id = subject.subject_id "
                    + "join `group` on `group`.group_id = curriculum_subject.type_id "
                    + "where curriculum_subject.curriculum_id = ? and type = 'Elective' and `group`.is_active = 1";

            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String group_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String combo_subject = rs.getString(3);
                String curriculum_id = rs.getString(4);
                data.add(new Group(group_id, name, combo_subject, curriculum_id));

            }
        } catch (SQLException e) {
            System.out.println("getListElective" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static Group getElectiveByCurriculumID(String curriculumid, String groupid) {
        Group gr = new Group();
        try {
            connect();
            String strSelect
                    = "SELECT `group`.group_id,`group`.name,subject.code,subject.name "
                    + "FROM swp391_g3_flm.curriculum_subject join subject "
                    + "on curriculum_subject.subject_id = subject.subject_id "
                    + "join `group` on `group`.group_id = curriculum_subject.type_id "
                    + "where curriculum_subject.curriculum_id = ? and type = 'Elective' and `group`.group_id = ? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            pstm.setString(2, groupid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                gr.setGroup_id(rs.getString(1));
                gr.setName(rs.getString(2));
                gr.setCombo_subject(rs.getString(3));
                gr.setCurriculum_id(rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("getElectiveByCurriculumID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return gr;
    }

    public static ArrayList<Group> getListElectiveSubject(String curriculumid, String groupid) {
        ArrayList<Group> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT subject.subject_id,`group`.name,subject.code,subject.name "
                    + "FROM swp391_g3_flm.curriculum_subject join subject "
                    + "on curriculum_subject.subject_id = subject.subject_id "
                    + "join `group` on `group`.group_id = curriculum_subject.type_id "
                    + "where curriculum_subject.curriculum_id = ? and type = 'Default' and `group`.group_id = ? ";

            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            pstm.setString(2, groupid);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String group_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String combo_subject = rs.getString(3);
                String curriculum_id = rs.getString(4);
                data.add(new Group(group_id, name, combo_subject, curriculum_id));

            }
        } catch (SQLException e) {
            System.out.println("getListElectiveSubject" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static ArrayList<Group> getListCombo(String curriculumid) {
        ArrayList<Group> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT `group`.group_id,`group`.name,subject.code,subject.name,`group`.display_order,`group`.is_active "
                    + "FROM swp391_g3_flm.curriculum_subject join subject "
                    + "on curriculum_subject.subject_id = subject.subject_id "
                    + "join `group` on `group`.group_id = curriculum_subject.type_id "
                    + "where curriculum_subject.curriculum_id= ? and type='Combo' ";

            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String group_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String combo_subject = rs.getString(3);
                String curriculum_id = rs.getString(4);
                String display_order = rs.getString(5);
                String is_active = rs.getString(6);

                data.add(new Group(group_id, name, combo_subject, curriculum_id, display_order, is_active));

            }
        } catch (SQLException e) {
            System.out.println("getListCombo" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static ArrayList<Group> getListComboSubject(String curriculumid, String groupid) {
        ArrayList<Group> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT subject.subject_id,subject.name,subject.code,curriculum_subject.semester "
                    + "FROM swp391_g3_flm.curriculum_subject join subject "
                    + "on curriculum_subject.subject_id = subject.subject_id "
                    + "join `group` on `group`.group_id = curriculum_subject.type_id "
                    + "where curriculum_subject.curriculum_id = ? and type = 'Default' and `group`.group_id = ? ";

            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            pstm.setString(2, groupid);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String group_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String combo_subject = rs.getString(3);
                String curriculum_id = rs.getString(4);
                data.add(new Group(group_id, name, combo_subject, curriculum_id));

            }
        } catch (SQLException e) {
            System.out.println("getListComboSubject" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static Group getComboByCurriculumID(String curriculumid, String groupid) {
        Group gr = new Group();
        try {
            connect();
            String strSelect
                    = "SELECT `group`.group_id,`group`.name,subject.code,subject.name "
                    + "FROM swp391_g3_flm.curriculum_subject join subject "
                    + "on curriculum_subject.subject_id = subject.subject_id "
                    + "join `group` on `group`.group_id = curriculum_subject.type_id "
                    + "where curriculum_subject.curriculum_id = ? and type = 'Combo' and `group`.group_id = ? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            pstm.setString(2, groupid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                gr.setGroup_id(rs.getString(1));
                gr.setName(rs.getString(2));
                gr.setCombo_subject(rs.getString(3));
                gr.setCurriculum_id(rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("getElectiveByCurriculumID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return gr;
    }

    public static void deleteElective(String groupID) {
        try {
            connect();

            String strSelect = "DELETE FROM `swp391_g3_flm`.`group` WHERE (`group_id` = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, groupID);
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("deleteElective:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void addElective(String name, String curriculum_id, String active) {
        try {
            connect();

            String strSelect = "INSERT INTO `swp391_g3_flm`.`group` (`name`, `combo_subject`, `curriculum_id`, `is_active`)"
                    + " VALUES (?, ?, ?, b?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, name);
            pstm.setString(2, "elective");
            pstm.setString(3, curriculum_id);
            pstm.setString(4, active);
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("addElective:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }

    }

    public static void deleteElectiveDetail(String curriculumid, String subject_id) {

        try {
            connect();

            String strSelect = "UPDATE `swp391_g3_flm`.`curriculum_subject` SET type_id = '0' where curriculum_id = ? and subject_id= ? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            pstm.setString(2, subject_id);
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("deleteElectiveDetail:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void updateCombo(String groupid, String active) {
        try {
            connect();

            String strSelect = "UPDATE `swp391_g3_flm`.`group` SET is_active = b? where group_id=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, active);
            pstm.setString(2, groupid);
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("deleteElectiveDetail:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }

    }
    public static ArrayList<Group> getGroupList(String curID, String combo_sub) {
        ArrayList<Group> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * FROM swp391_g3_flm.`group`\n"
                    + "where curriculum_id = ? and combo_subject = ?\n"
                    + "order by display_order;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curID);
            pstm.setString(2, combo_sub);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String group_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String combo_subject = rs.getString(3);
                String curriculum_id = String.valueOf(rs.getInt(4));
                String is_active = String.valueOf(rs.getInt(6));
                String display_order = String.valueOf(rs.getInt(5));

                list.add(new Group(group_id, name, combo_subject, curriculum_id, display_order, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getGroupList: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static void updateGroup(String groupID, String name, String disOrder) {
        try {
            connect();

            String strSelect = "UPDATE `swp391_g3_flm`.`group` "
                    + "SET `name` = ?, `display_order` = ? "
                    + "WHERE (`group_id` = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, name);
            pstm.setString(2, disOrder);
            pstm.setString(3, groupID);
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("deleteElective:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
    
    
}
