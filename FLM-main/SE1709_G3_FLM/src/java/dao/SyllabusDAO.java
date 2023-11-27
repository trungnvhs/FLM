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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Decision;
import model.Pre_requisite;
import model.Subject;
import model.Syllabus;
import model.User;

/**
 *
 * @author quan
 */
public class SyllabusDAO {

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

    public static boolean checkSyllabusid(String syllabus_id) {
        try {
            connect();

            String strSelect = "select * from syllabus where syllabus_id =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, syllabus_id);

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

    public static ArrayList<Syllabus> getSyllabusNotHaveDecision() {
        ArrayList<Syllabus> data = new ArrayList<Syllabus>();
        try {
            connect();
            String strSelect
                    = "SELECT * FROM syllabus where decision_id is null;";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                String syllabus_id = rs.getString(1);

                data.add(new Syllabus(syllabus_id, name));
            }
        } catch (SQLException e) {
            System.out.println("getSyllabusNotHaveDecision: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static void addDecisionForSyllabus(String id, String syllabusdecision) {
        try {
            connect();
            String strAdd = "UPDATE `swp391_g3_flm`.`syllabus` SET decision_id = ? Where syllabus_id = ?";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, id);
            pstm.setString(2, syllabusdecision);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("addDecisionForSyllabus:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static String getRoleId(String user_id) {
        try {
            connect();
            String strSelect = "select * from user_role  where user_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, user_id);
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

    public static ArrayList<Syllabus> getListSyllabusDefault() {
        ArrayList<Syllabus> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "select * from syllabus ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String syllabus_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String no_of_credit = String.valueOf(rs.getInt(3));
                String isActive = rs.getString(4);
                String isApproved = rs.getString(5);
                String degree_level = rs.getString(6);
                String decision_id = String.valueOf(rs.getInt(7));
                String designer_id = String.valueOf(rs.getInt(8));
                String subject_id = String.valueOf(rs.getInt(9));
                data.add(new Syllabus(syllabus_id, name, no_of_credit, isActive, isApproved, degree_level, decision_id, designer_id, subject_id));
            }
            System.out.println(data.get(2).toString());
        } catch (Exception e) {
            System.out.println("getListSyllabusDefault" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static int getTotalSyllabus() {
        try {
            connect();
            String strSelect = "select count(*) from syllabus";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getTotalSyllabus: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }

    public static ArrayList<Syllabus> getSyllabusListPaging(String page, String designer) {
        ArrayList<Syllabus> data = new ArrayList<Syllabus>();
        try {
            connect();
            String strSelect = "SELECT * FROM syllabus where designer_id = ? order by syllabus_id limit ?, 10 ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, designer);
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String syllabus_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String no_of_credit = String.valueOf(rs.getInt(3));
                String isActive = rs.getString(4);
                String isApproved = rs.getString(5);
                String degree_level = rs.getString(6);
                String decision_id = String.valueOf(rs.getInt(7));
                String designer_id = String.valueOf(rs.getInt(8));
                String subject_id = String.valueOf(rs.getInt(9));
                data.add(new Syllabus(syllabus_id, name, no_of_credit, isActive, isApproved, degree_level, decision_id, designer_id, subject_id));
                System.out.println(name);
            }

        } catch (SQLException e) {
            System.out.println("getCurriculumListPaging: " + e.getMessage());
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

    public static String getSubjectCode(String subject_id) {
        try {
            connect();
            String strSelect = "select * from subject where subject_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, subject_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String SubjectCode = rs.getString(2);
                return SubjectCode;
            }

        } catch (Exception e) {
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }
    
    public static String getSubjectCodeBySYID(String syl_id) {
        try {
            connect();
            String strSelect = "SELECT subject.code FROM swp391_g3_flm.syllabus join swp391_g3_flm.subject \n" +
                    "on syllabus.subject_id = subject.subject_id\n" +
                    "where syllabus_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, syl_id);
            rs = pstm.executeQuery();System.out.println("check");

            while (rs.next()) {
                String SubjectCode = rs.getString(1);
                return SubjectCode;
                
            }
        } catch (SQLException e) {
            System.out.println("getSubjectCodeBySYID" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static String getSubjectName(String subject_id) {
        try {
            connect();
            String strSelect = "select * from subject where subject_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, subject_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String SubjectName = rs.getString(3);
                return SubjectName + " SubN " + SubjectName;
            }

        } catch (Exception e) {
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static String getSubjectDecription(String subject_id) {
        try {
            connect();
            String strSelect = "select * from subject where subject_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, subject_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Decription = rs.getString(6);
                return Decription;
            }

        } catch (Exception e) {
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static String getDesigner(String designer_id) {
        try {
            connect();
            String strSelect = "select * from user where user_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, designer_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Designer = rs.getString(2);
                return Designer + " De " + Designer;
            }

        } catch (Exception e) {
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public Syllabus getSyllabusBySyllabusID(String id) {
        Syllabus sy = new Syllabus();
        try {
            connect();
            String strSelect
                    = "SELECT * FROM syllabus where syllabus_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                sy.setSyllabus_id(rs.getString(1));
                sy.setName(rs.getString(2));
                sy.setNo_of_credit(rs.getString(3));
                sy.setIs_active(rs.getString(4));
                sy.setIs_approved(rs.getString(5));
                sy.setDegree_level(rs.getString(6));
                sy.setDecision_id(rs.getString(7));
                sy.setDesigner_id(rs.getString(8));
                sy.setSubject_id(rs.getString(9));

            }
        } catch (SQLException e) {
            System.out.println("getSyllabusBySyllabusID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return sy;
    }

    public static ArrayList<Pre_requisite> getPre(String key) {
        ArrayList<Pre_requisite> pre = new ArrayList<>();
        try {
            connect();
            String str1 = "SELECT s.subject_id, p.prerequisite_id, \n"
                    + "(SELECT s2.code FROM subject s2 WHERE s2.subject_id = p.prerequisite_id) AS code\n"
                    + "FROM subject s\n"
                    + "JOIN prerequisite p ON p.subject_id = s.subject_id\n"
                    + "WHERE 1=1\n and s.subject_id = ? or s.code like ?";

            if (str1 != null) {
                pstm = cnn.prepareStatement(str1);
                pstm.setString(1, key);
                pstm.setString(2, key);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    pre.add(new Pre_requisite(rs.getInt("subject_id"), rs.getInt("prerequisite_id"), rs.getString("code")));
                }
                return pre;
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query 123: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SyllabusDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static ArrayList<Pre_requisite> getSuc(String key) {
        ArrayList<Pre_requisite> pre = new ArrayList<>();
        try {
            connect();
            String str1 = "SELECT s.subject_id, p.prerequisite_id, \n"
                    + "(SELECT s2.code FROM subject s2 WHERE s2.subject_id = p.subject_id) AS code\n"
                    + " FROM \n"
                    + " subject s\n"
                    + "JOIN prerequisite p ON p.subject_id = s.subject_id\n"
                    + "WHERE 1=1 and p.prerequisite_id = ? OR  p.prerequisite_id=(SELECT subject_id FROM subject WHERE code like ?);";

            if (str1 != null) {
                pstm = cnn.prepareStatement(str1);
                pstm.setString(1, key);
                pstm.setString(2, key);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    pre.add(new Pre_requisite(rs.getInt("subject_id"), rs.getInt("prerequisite_id"), rs.getString("code")));
                }
                return pre;
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query 123: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SyllabusDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static Syllabus getSyllabus(String subject) {
        try {
            connect();
            String strSelect = "select s.*, su.code, su.name\n"
                    + " from syllabus s join subject su on s.subject_id = su.subject_id \n"
                    + " where  su.code = ? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, subject);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Syllabus_id = rs.getString(1);
                String sub_code = rs.getString(17);
                String sub_id = rs.getString(9);
                String sub_name = rs.getString(11);
                String Syllabus_name = rs.getString(2);
                String Decision_id = rs.getString(7);
                return new Syllabus(Syllabus_id, Syllabus_name, Decision_id, new Subject(sub_id, sub_code, sub_name));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }
    public static int getTotalSyllabus(String text) {
        try {
            connect();
            String strSelect = "SELECT count(*) FROM swp391_g3_flm.syllabus where syllabus_id like ? or name like ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%"+text+'%');
            pstm.setString(2, "%"+text+'%');
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getTotalSyllabus: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }

    public static ArrayList<Syllabus> getSyllabusBySearch(String text, String page) {
         ArrayList<Syllabus> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT syllabus.*, User.user_name\n" +
                        "FROM swp391_g3_flm.syllabus\n" +
                        "join swp391_g3_flm.User on syllabus.designer_id = User.user_id\n" +
                        "where syllabus_id like ? or syllabus.name like ?\n" +
                        "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%"+text+'%');
            pstm.setString(2, "%"+text+'%');
            pstm.setInt(3, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String syllabus_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String no_of_credit = String.valueOf(rs.getInt(3));
                String isActive = rs.getString(4);
                String isApproved = rs.getString(5);
                String degree_level = rs.getString(6);
                String decision_id = String.valueOf(rs.getInt(7));
                String designer_id = String.valueOf(rs.getInt(8));
                String subject_id = String.valueOf(rs.getInt(9));
                String designer_name = rs.getString(10);
                data.add(new Syllabus(syllabus_id, name, no_of_credit, isActive, isApproved, degree_level, decision_id, subject_id, new User(designer_id, null, null, designer_name, null, null,null, null)));
            }
        } catch (SQLException e) {
            System.out.println("getListSyllabusDefault" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }
        public static Syllabus getSyllabusbyID(String sysID) {
        try {
            connect();
            String strSelect = "SELECT * FROM swp391_g3_flm.syllabus where syllabus_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sysID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Syllabus sys = new Syllabus();
                sys.setSyllabus_id(String.valueOf(rs.getInt(1)));
                sys.setName(rs.getString(2));
                sys.setNo_of_credit(String.valueOf(rs.getInt(3)));
                sys.setIs_active(rs.getString(4));
                sys.setIs_approved(rs.getString(5));
                sys.setDegree_level(rs.getString(6));
                sys.setDecision_id(String.valueOf(rs.getInt(7)));
                sys.setDesigner_id(String.valueOf(rs.getInt(8)));
                sys.setSubject_id(String.valueOf(rs.getInt(9)));
                sys.setTime_allocation(rs.getString(10));
                sys.setStu_task(rs.getString(11));
                sys.setTool(rs.getString(12));
                sys.setScoringScale(String.valueOf(rs.getInt(13)));
                sys.setNote(rs.getString(14));
                sys.setMinAvgMarkToPass(String.valueOf(rs.getInt(15)));
                sys.setCreateBy(String.valueOf(rs.getInt(16)));
                return sys;
            }

        } catch (SQLException e) {
            System.out.println("getCurriculumListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

}
