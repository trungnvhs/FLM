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
import model.CLO;

/**
 *
 * @author ThinkPad P50
 */
public class CLODAO {
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
     public static ArrayList<CLO> getCloListPaging(String page, String syllabus_id) {
        ArrayList<CLO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * FROM clo where syllabus_id = ?\n"
                    + "order by clo_id\n"
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(syllabus_id)));
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String clo_id = String.valueOf(rs.getInt(1));
                String clo_name = rs.getString(2);
                String clo_description = rs.getString(3);
                String is_active = String.valueOf(rs.getInt(5));
                list.add(new CLO(clo_id, clo_name, clo_description, syllabus_id, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getCloListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
        public static ArrayList<CLO> getCloListA(String page, String syllabus_id) {
        ArrayList<CLO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * FROM clo where syllabus_id = ? and  is_active = 1 \n"
                    + "order by clo_id\n"
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(syllabus_id)));
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String clo_id = String.valueOf(rs.getInt(1));
                String clo_name = rs.getString(2);
                String clo_description = rs.getString(3);
                String is_active = String.valueOf(rs.getInt(5));
                list.add(new CLO(clo_id, clo_name, clo_description, syllabus_id, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getCloListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
        
         public static ArrayList<CLO> getCloListD(String page, String syllabus_id) {
        ArrayList<CLO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * FROM clo where syllabus_id = ? and  is_active = 0 \n"
                    + "order by clo_id\n"
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(syllabus_id)));
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String clo_id = String.valueOf(rs.getInt(1));
                String clo_name = rs.getString(2);
                String clo_description = rs.getString(3);
                String is_active = String.valueOf(rs.getInt(5));
                list.add(new CLO(clo_id, clo_name, clo_description, syllabus_id, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getCloListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
     
       public static CLO getCloByID(String cloID) {
        CLO list = new CLO();
        try {
            connect();
            String strSelect = "SELECT * FROM clo where clo_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cloID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                 String clo_id = String.valueOf(rs.getInt(1));
                String clo_name = rs.getString(2);
                String clo_description = rs.getString(3);
                String syllabus_id = rs.getString(4);
                String is_active = String.valueOf(rs.getInt(5));
                list = new CLO(clo_id, clo_name, clo_description, syllabus_id, is_active);
            }
        } catch (SQLException e) {
            System.err.println("getPoByID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
     
      public static int getTotalClo(String syllabus_id) {
        try {
            connect();
            String strSelect = "select count(*) from clo\n"
                    + "where syllabus_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(syllabus_id)));
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getTotalClo: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }
      
      public static int getTotalCloF(String syllabus_id, String isactive) {
        try {
            connect();
            String strSelect = "select count(*) from clo\n"
                    + "where syllabus_id = ? and is_active = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(syllabus_id)));
            pstm.setString(2, isactive);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getTotalClo: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }

    public static ArrayList<CLO> getCloSearch(String txt, String syllabus_id) {
        ArrayList<CLO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * \n"
                    + "FROM clo"
                    + " where clo_name like '" + "%" + txt + "%" + "'"
                    + " OR clo_id like '" + "%" + txt + "%" + "'"
                    + "and syllabus_id = ?";

            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(syllabus_id)));
            rs = pstm.executeQuery();
            while (rs.next()) {
                String clo_id = String.valueOf(rs.getInt(1));
                String clo_name = rs.getString(2);
                String clo_description = rs.getString(3);
                String is_active = String.valueOf(rs.getInt(5));
                list.add(new CLO(clo_id, clo_name, clo_description, syllabus_id, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getCloSearch: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
     // UnLock  CLO
     public static void unlock(String clo_id) {

        try {
            connect();
            String strSelect = "UPDATE clo "
                    + "SET is_active = 1 "
                    + "WHERE (clo_id = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, clo_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Unlock:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    // Lock  CLO
    public static void lock(String clo_id) {

        try {
            connect();
            String strSelect = "UPDATE clo "
                    + "SET is_active = 0 "
                    + "WHERE (clo_id = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, clo_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("lock:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
    
    public static boolean checkCloName(String name, String sysID) {
        try {
            connect();

            String strSelect = "select * from clo where clo_name = ? and syllabus_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, name);
            pstm.setString(2, sysID);
            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkCloName:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }
    
    public static void updateClo(CLO clo) {
        try {
            connect();
            String strUpdate = "update clo "
                    + "SET `clo_name` =?,"
                    + "`clo_description` = ?, `syllabus_id`=?,\n"
                    + "`is_active` = b?\n"
                    + "where clo_id=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, clo.getClo_name());
            pstm.setString(2, clo.getClo_description());
            pstm.setString(3, clo.getSyllabus_id());
            pstm.setString(4, clo.getIs_active());
            pstm.setString(5, clo.getClo_id());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateClo:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
    
    public static void addClo(CLO clo) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`clo` (`clo_name`, `clo_description`, `syllabus_id`, `is_active`)"
                    + " VALUES (?, ?, ?, b?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, clo.getClo_name());
            pstm.setString(2, clo.getClo_description());
            pstm.setString(3, clo.getSyllabus_id());
            pstm.setString(4, clo.getIs_active());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addClo:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
    
     public static ArrayList<CLO> getListCloByCode(String sysID) {
        ArrayList<CLO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "   select c.clo_id, c.clo_name\n" +
"                     from clo as c join syllabus as s \n" +
"                     on c.syllabus_id = s.syllabus_id \n" +
"                     where  s.syllabus_id = ? and c.is_active = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sysID);
            pstm.setString(2, "1");
            rs = pstm.executeQuery();
            while (rs.next()) {
                String clo_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
               
                list.add(new CLO(clo_id, name));
            }
        } catch (SQLException e) {
            System.err.println("getListPoByCode: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
}
