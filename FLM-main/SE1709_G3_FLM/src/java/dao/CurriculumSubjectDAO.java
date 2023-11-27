/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.CurriculumDAO.closeConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.CurriculumSubject;
import model.Pre_requisite;

/**
 *
 * @author quan
 */
public class CurriculumSubjectDAO {

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

    public static ArrayList<CurriculumSubject> getListCurriculumSubject(String curriculumid, String page) {

        ArrayList<CurriculumSubject> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "select curriculum_subject.subject_id,code,name,semester,no_credit "
                    + "from curriculum_subject join subject on curriculum_subject.subject_id = subject.subject_id "
                    + "where curriculum_id = ?"
                    + "order by curriculum_subject.subject_id\n"
                    + "limit ?,10; ";

            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String semester = String.valueOf(rs.getInt(4));
                String no_credit = String.valueOf(rs.getInt(5));
                data.add(new CurriculumSubject(subject_id, code, name, semester, no_credit));
            }
        } catch (SQLException e) {
            System.out.println("getListCurriculum" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static int getTotalCurSUB(String curriculumid) {
        try {
            connect();
            String strSelect = "select count(*) "
                    + "from curriculum_subject join subject on curriculum_subject.subject_id = subject.subject_id "
                    + "where curriculum_id = ? and type <> 'subcombo'";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
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

    public static String getPrerequisite(String subjectid, String curriculumid) {

        try {
            connect();
            String strSelect = "SELECT * FROM swp391_g3_flm.prerequisite "
                    + "where curriculum_id=? and subject_id=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(2, subjectid);
            pstm.setString(1, curriculumid);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String description = rs.getString(4);

                return description;
            }
        } catch (Exception e) {
            System.out.println("getAssigneeName" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    
    
    
     public static ArrayList<CurriculumSubject> getListCurriculumSubject1(String curriculumid) {

        ArrayList<CurriculumSubject> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "select curriculum_subject.subject_id,code,name,semester,no_credit "
                    + "from curriculum_subject join subject on curriculum_subject.subject_id = subject.subject_id "
                    + "where curriculum_id = ? and type = 'Elective'"
                    + "order by curriculum_subject.subject_id\n";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String semester = String.valueOf(rs.getInt(4));
                String no_credit = String.valueOf(rs.getInt(5));
                data.add(new CurriculumSubject(subject_id, code, name, semester, no_credit));
            }
        } catch (SQLException e) {
            System.out.println("getListCurriculum" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static ArrayList<CurriculumSubject> getListCurriculumSubject3(String curriculumid) {

        ArrayList<CurriculumSubject> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "select curriculum_subject.subject_id,code,name,semester,no_credit "
                    + "from curriculum_subject join subject on curriculum_subject.subject_id = subject.subject_id "
                    + "where curriculum_id = ? and type = 'Combo'"
                    + "order by curriculum_subject.subject_id\n";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String semester = String.valueOf(rs.getInt(4));
                String no_credit = String.valueOf(rs.getInt(5));
                data.add(new CurriculumSubject(subject_id, code, name, semester, no_credit));
            }
        } catch (SQLException e) {
            System.out.println("getListCurriculum" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static ArrayList<CurriculumSubject> getListCurriculumSubject2(String curriculumid) {

        ArrayList<CurriculumSubject> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "select curriculum_subject.subject_id,code,name,semester,no_credit "
                    + "from curriculum_subject join subject on curriculum_subject.subject_id = subject.subject_id "
                    + "where curriculum_id = ? and type = 'Default' "
                    + "order by curriculum_subject.subject_id\n";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String semester = String.valueOf(rs.getInt(4));
                String no_credit = String.valueOf(rs.getInt(5));
                data.add(new CurriculumSubject(subject_id, code, name, semester, no_credit));
            }
        } catch (SQLException e) {
            System.out.println("getListCurriculum" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static boolean checkSubjectIdInCurriculum(String curriculumid, String subjectid) {
        try {
            connect();

            String strSelect = "select * from curriculum_subject where subject_id =? and curriculum_id= ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            pstm.setString(2, subjectid);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkSubjectID:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static void addCurriculum(String curriculumid, String subjectid, String syllabusid, String groupid, String semester, String nocredit, String isactive) {

        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`,`syllabus_id`, `group_id`, `semester`, `no_credit`, `is_active`)"
                    + " VALUES (?, ?, ?, b?, ?, ?,?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, curriculumid);
            pstm.setString(2, subjectid);
            pstm.setString(3, syllabusid);
            pstm.setString(4, groupid);
            pstm.setString(5, semester);
            pstm.setString(6, nocredit);
            pstm.setString(7, isactive);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("addCurriculumSubject:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }

    }

    public static void addGroup(String curID, String subID, String groupID) {
        try {
            connect();
            String strAdd = "UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = ? "
                    + "WHERE (`curriculum_id` = ?) and (`subject_id` = ?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, groupID);
            pstm.setString(2, curID);
            pstm.setString(3, subID);
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("addGroup:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void addSubInGroup(String curID, String subDelID) {
        try {
            connect();
            String strAdd = "UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = null "
                    + "WHERE (`curriculum_id` = ?) and (`subject_id` = ?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, curID);
            pstm.setString(2, subDelID);
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("addSubInGroup:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
    public static void UpdateTypeID(String curriculum_id, String subject_id, String group_id) {

        try {
            connect();
            String strAdd = "UPDATE `swp391_g3_flm`.`curriculum_subject` SET `type_id` = ? WHERE (`curriculum_id` = ? ) and (`subject_id` = ? );";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, group_id);
            pstm.setString(2, curriculum_id);
            pstm.setString(3, subject_id);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("updatetype:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }

    }
}
