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
import model.Subject;

/**
 *
 * @author Trung Quan
 */
public class SubjectDAO {

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

    public static ArrayList<Subject> getSubjectList() {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select * from subject ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String type = rs.getString(4);
                String is_active = String.valueOf(rs.getInt(5));
                String description = rs.getString(6);
                String parent_id = String.valueOf(rs.getInt(7));
                String group_id = String.valueOf(rs.getInt(8));

                list.add(new Subject(subject_id, code, name, type, is_active, description, parent_id, group_id));
            }
        } catch (SQLException e) {
            System.err.println("getSubjectList: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static ArrayList<Subject> getSubjectListPaging(String page) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * FROM swp391_g3_flm.subject\n"
                    + "order by subject_id\n"
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String type = rs.getString(4);
                String is_active = String.valueOf(rs.getInt(5));
                String description = rs.getString(6);
                String parent_id = String.valueOf(rs.getInt(7));
                String group_id = String.valueOf(rs.getInt(8));

                list.add(new Subject(subject_id, code, name, type, is_active, description, parent_id, group_id));
            }
        } catch (SQLException e) {
            System.err.println("getSubjectListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static boolean checkSubjectCode(String code) {
        try {
            connect();

            String strSelect = "select * from subject where code = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, code);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkSubjectCode:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static void addSubject(Subject s) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`subject` (`code`, `name`,`type`, `is_active`, `description`, `parent_id`, `group_id`)"
                    + " VALUES (?, ?, ?, b?, ?, ?, ?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, s.getCode());
            pstm.setString(2, s.getName());
            pstm.setString(3, s.getType());
            pstm.setString(4, s.getIs_active());
            pstm.setString(5, s.getDescription());
            pstm.setString(6, s.getParent_id());
            pstm.setString(7, s.getGroup_id());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addSubject:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static String getParentID(String parentCode) {
        try {
            connect();

            String strSelect = "select * from subject where code = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, parentCode);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return String.valueOf(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("checkSubjectCode:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static String getParentCode(String parentID) {
        try {
            connect();

            String strSelect = "select code from subject where subject_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, parentID);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("checkSubjectCode:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static ArrayList<Subject> getSubListBySearch(String text, String page) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select * from subject where code like ? or name like ?\n"
                    + "order by subject_id\n"
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + text + "%");
            pstm.setString(2, "%" + text + "%");
            pstm.setInt(3, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String type = rs.getString(4);
                String is_active = String.valueOf(rs.getInt(5));
                String description = rs.getString(6);
                String parent_id = String.valueOf(rs.getInt(7));
                String group_id = String.valueOf(rs.getInt(8));

                list.add(new Subject(subject_id, code, name, type, is_active, description, parent_id, group_id));
            }
        } catch (SQLException e) {
            System.err.println("getSubListByCode: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static ArrayList<Subject> getSubListByName(String text) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select * from subject where name like ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + text + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String type = rs.getString(4);
                String is_active = String.valueOf(rs.getInt(5));
                String description = rs.getString(6);
                String parent_id = String.valueOf(rs.getInt(7));
                String group_id = String.valueOf(rs.getInt(8));

                list.add(new Subject(subject_id, code, name, type, is_active, description, parent_id, group_id));
            }
        } catch (SQLException e) {
            System.err.println("getSubListByName: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static Subject getSubByID(String subjectID) {
        Subject list = new Subject();
        try {
            connect();
            String strSelect = "select * from subject where subject_id like ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, subjectID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String type = rs.getString(4);
                String is_active = String.valueOf(rs.getInt(5));
                String description = rs.getString(6);
                String parent_id = String.valueOf(rs.getInt(7));
                String group_id = String.valueOf(rs.getInt(8));

                list = new Subject(subject_id, code, name, type, is_active, description, parent_id, group_id);
            }
        } catch (SQLException e) {
            System.err.println("getSubByID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static int getTotalSubject() {
        try {
            connect();
            String strSelect = "select count(*) from subject";
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

    public static int getTotalSubject(String text) {
        try {
            connect();
            String strSelect = "select count(*) from subject where code like ? or name like ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + text + "%");
            pstm.setString(2, "%" + text + "%");
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

    public static ArrayList<Subject> getSubPreCode(String pcode) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select su.subject_id, su.code\n"
                    + "from  subject s join prerequisite p\n"
                    + "on s.subject_id = p.prerequisite_id\n"
                    + "join subject su on p.subject_id = su.subject_id\n"
                    + "where s.code = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pcode);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code_ps = rs.getString(2);
                list.add(new Subject(subject_id, code_ps));
            }
        } catch (SQLException e) {
            System.err.println("getSubPreCode: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static ArrayList<Subject> getSubSucCode(String pcode) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select   s.subject_id ,su.code\n"
                    + "  from  subject s join prerequisite p\n"
                    + "  on s.subject_id = p.subject_id\n"
                    + "  join subject su on p.prerequisite_id = su.subject_id\n"
                    + "  where s.code = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pcode);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code_ps = rs.getString(2);
                list.add(new Subject(subject_id, code_ps));
            }
        } catch (SQLException e) {
            System.err.println("getSubSucCode: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static Subject getSubPreCode1(String pcode) {
        Subject list = new Subject();
        try {
            connect();
            String strSelect = "select * from subject where code = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pcode);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String type = rs.getString(4);
                String is_active = String.valueOf(rs.getInt(5));
                String description = rs.getString(6);
                String parent_id = String.valueOf(rs.getInt(7));
                String group_id = String.valueOf(rs.getInt(8));

                list = new Subject(subject_id, code, name, type, is_active, description, parent_id, group_id);
            }
        } catch (SQLException e) {
            System.err.println("getSubPreCode1: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static boolean checkSubjectCodePre(String pcode) {
        try {
            connect();

            String strSelect = "select s.code\n"
                    + "from  subject s join prerequisite p\n"
                    + "on s.subject_id = p.prerequisite_id\n"
                    + "join subject su on p.subject_id = su.subject_id\n"
                    + "where s.code = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pcode);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkSubjectCodePre:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static boolean checkSubjectCodeSuc(String pcode) {
        try {
            connect();

            String strSelect = "select su.code\n"
                    + "from  subject s join prerequisite p\n"
                    + "on s.subject_id = p.prerequisite_id\n"
                    + "join subject su on p.subject_id = su.subject_id\n"
                    + "where s.code = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pcode);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkSubjectCodePre:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static boolean checkSubjectIdInSubject(String subjectid) {
        try {
            connect();

            String strSelect = "select * from subject where subject_id =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, subjectid);

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

    public static boolean checkGroupCode(String groupCode) {
        try {
            connect();

            String strSelect = "select * from `group` where group_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, groupCode);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkSubjectCode:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static ArrayList<Subject> getListSubContent(String groupID, String curID) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select s.*\n"
                    + "from `swp391_g3_flm`.`curriculum_subject` cs join `swp391_g3_flm`.`subject` s\n"
                    + "on cs.subject_id = s.subject_id\n"
                    + "and cs.curriculum_id = ? and cs.group_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curID);
            pstm.setString(2, groupID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String type = rs.getString(4);
                String is_active = String.valueOf(rs.getInt(5));
                String description = rs.getString(6);
                String parent_id = String.valueOf(rs.getInt(7));
                String group_id = String.valueOf(rs.getInt(8));

                list.add(new Subject(subject_id, code, name, type, is_active, description, parent_id, group_id));
            }
        } catch (SQLException e) {
            System.err.println("getSubListByName: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static ArrayList<Subject> getListSubContent(String curID) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select s.*\n"
                    + "from `swp391_g3_flm`.`curriculum_subject` cs join `swp391_g3_flm`.`subject` s\n"
                    + "on cs.subject_id = s.subject_id\n"
                    + "and cs.curriculum_id = ? and cs.group_id is null";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String subject_id = String.valueOf(rs.getInt(1));
                String code = rs.getString(2);
                String name = rs.getString(3);
                String type = rs.getString(4);
                String is_active = String.valueOf(rs.getInt(5));
                String description = rs.getString(6);
                String parent_id = String.valueOf(rs.getInt(7));
                String group_id = String.valueOf(rs.getInt(8));

                list.add(new Subject(subject_id, code, name, type, is_active, description, parent_id, group_id));
            }
        } catch (SQLException e) {
            System.err.println("getSubListByName: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static boolean check(String curID, String subject_id, String plo_id) {
        try {
            connect();
            String sql = "SELECT * FROM swp391_g3_flm.plo_subject\n"
                    + "where curriculum_id = ? and subject_id = ? and plo_id = ?;";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, curID);
            pstm.setString(2, subject_id);
            pstm.setString(3, plo_id);
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

    public static void addMappingPlo_Sub(String curID, String subject_id, String plo_id) {
        try {
            connect();
            String sqlUpdate = ""
                    + "INSERT INTO swp391_g3_flm.plo_subject (`curriculum_id`, `subject_id`, `plo_id`) VALUES (?, ?, ?);";
            pstm = cnn.prepareStatement(sqlUpdate);
            pstm.setString(1, curID);
            pstm.setString(2, subject_id);
            pstm.setString(3, plo_id);
            pstm.execute();

        } catch (Exception ex) {
            System.out.println("addMappingPlo_Sub :" + ex.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void deleteMappingPlo_Sub(String curID, String subject_id, String plo_id) {
        try {
            connect();
            String sqlDetele = ""
                    + "DELETE FROM swp391_g3_flm.plo_subject\n"
                    + "where curriculum_id = ? and subject_id = ? and plo_id = ?;";
            pstm = cnn.prepareStatement(sqlDetele);
            pstm.setString(1, curID);
            pstm.setString(2, subject_id);
            pstm.setString(3, plo_id);
            pstm.execute();

        } catch (Exception ex) {
            System.out.println("deleteMappingPlo_Sub :" + ex.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public boolean checkSubject(String pcode) {
        try {
            connect();
            String strSelect = " select * from subject where code like ? or subject_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, pcode);
            pstm.setString(2, pcode);
            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkSubjectCodePre:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }
}
