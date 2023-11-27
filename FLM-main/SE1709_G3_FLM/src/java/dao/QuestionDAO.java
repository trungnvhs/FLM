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
import model.Question;

/**
 *
 * @author Asus
 */
public class QuestionDAO {

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

    public static ArrayList<Question> getQuestionListPaging(String page, String sy_id) {
        ArrayList<Question> data = new ArrayList<Question>();
        try {
            connect();
            String strSelect = "select * from question q join session s on q.session_id = s.session_id where s.syllabus_id = ?  "
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sy_id);
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String q_id = rs.getString(1);
                String ses_id = rs.getString(2);
                String name = rs.getString(3);
                String detail = rs.getString(4);

                data.add(new Question(q_id, ses_id, name, detail));
            }
        } catch (SQLException e) {
            System.out.println("getQuestionListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static int getTotalQuestion(String sys_id) {
        try {
            connect();
            String strSelect = "select count(*) from question q join session s on q.session_id = s.session_id where s.syllabus_id =?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sys_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getTotalQuestion: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }

    public static String getCreatorName(String sy_id) {

        try {
            connect();
            String strSelect = "select u.user_name from syllabus s  join user u on s.designer_id = u.user_id where s.syllabus_id = ? ;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sy_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_name = rs.getString(1);

                return user_name;
            }
        } catch (Exception e) {
            System.out.println("getCreatorName" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static ArrayList<Question> getListQuestionSearchNameDetails(String textsearch, String sy_id) {
        ArrayList<Question> data = new ArrayList<Question>();
        try {
            connect();
            String strSelect = "select * from question q join session s on q.session_id = s.session_id where  (q.name like ? OR q.details like ?)and s.syllabus_id = ?;  ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + textsearch + "%");
            pstm.setString(2, "%" + textsearch + "%");
            pstm.setString(3, sy_id);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String q_id = rs.getString(1);
                String s_id = rs.getString(2);
                String name = rs.getString(3);
                String details = rs.getString(4);
                data.add(new Question(q_id, s_id, name, details));
            }
        } catch (SQLException e) {
            System.out.println("getListQuestionSearchNameDetails: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static ArrayList<Question> getListQuestionSort(String sort, String sy_id) {
        ArrayList<Question> data = new ArrayList<Question>();
        try {
            connect();
            System.out.println(sort);
            String strSelect = "select * from question q join session s on q.session_id = s.session_id where s.syllabus_id = ?  order by  ?  asc;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sy_id);
            pstm.setString(2, sort);

            rs = pstm.executeQuery();
            while (rs.next()) {
                String q_id = rs.getString(1);

                String s_id = rs.getString(2);
                String name = rs.getString(3);
                String details = rs.getString(4);
                System.out.println(details);
                data.add(new Question(q_id, s_id, name, details));
            }
        } catch (SQLException e) {
            System.out.println("getListQuestionSort: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return data;
    }

    public static boolean checkQuestion_name(String code) {
        try {
            connect();

            String strSelect = "select * from question where name = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, code);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkQuestion_name:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static void addQuestion(Question q) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`question` (`session_id`, `name`, `details`) VALUES (?, ?, ?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, q.getSession_id());
            pstm.setString(2, q.getName());
            pstm.setString(3, q.getDetails());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addQuestion:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static Question getQuestionByQuestionID(String question_id) {
        Question que = new Question();
        try {
            connect();
            String strSelect
                    = "SELECT * FROM question where question_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, question_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                que.setQuestion_id(rs.getString(1));
                que.setSession_id(rs.getString(2));
                que.setName(rs.getString(3));
                que.setDetails(rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("getQuestionByQuestionID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return que;
    }

    public static boolean checkSession_id(String session_id) {
 try {
            connect();

            String strSelect = "select * from session where session_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, session_id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkSession_id:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;    }

    public void removeQuestion(String question_id) {
        try {
            connect();
            String strAdd = " DELETE FROM `swp391_g3_flm`.`question` WHERE (`question_id` = ?); ";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, question_id);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("removeQuestion:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
     public static void updateQuestion(String id, String sessionno, String questioname, String details) {
        try {
            connect();
            String strAdd = "UPDATE `swp391_g3_flm`.`question`"
                    + " SET session_id = ? , name = ? , details = ? "
                    + "Where question_id = ?";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, sessionno);
            pstm.setString(2, questioname);
            pstm.setString(3, details);
            pstm.setString(4, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateQuestion:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
}
