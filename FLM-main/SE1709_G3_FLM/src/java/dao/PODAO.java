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
import model.PO;

/**
 *
 * @author ThinkPad P50
 */
public class PODAO {

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

    public static ArrayList<PO> getPoListPaging(String page, String curriculum_id) {
        ArrayList<PO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * FROM po where curriculum_id = ?\n"
                    + "order by po_id\n"
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(curriculum_id)));
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String po_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String description = rs.getString(3);
                String is_active = String.valueOf(rs.getInt(5));
                list.add(new PO(po_id, name, description, curriculum_id, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getPoListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
    
       public static ArrayList<PO> getPoListAc(String page, String curriculum_id) {
        ArrayList<PO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * FROM po where  curriculum_id = ? and is_active = 1 \n"
                    + "order by po_id\n"
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(curriculum_id)));
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String po_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String description = rs.getString(3);
                String is_active = String.valueOf(rs.getInt(5));
                list.add(new PO(po_id, name, description, curriculum_id, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getPoListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

        public static ArrayList<PO> getPoListD(String page, String curriculum_id) {
        ArrayList<PO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * FROM po where  curriculum_id = ? and is_active = 0 \n"
                    + "order by po_id\n"
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(curriculum_id)));
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String po_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String description = rs.getString(3);
                String is_active = String.valueOf(rs.getInt(5));
                list.add(new PO(po_id, name, description, curriculum_id, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getPoListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static int getTotalPo(String curriculum_id) {
        try {
            connect();
            String strSelect = "select count(*) from po\n"
                    + "where curriculum_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(curriculum_id)));
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getPoByID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }
    
      public static int getTotalPoF(String curriculum_id, String isactive) {
        try {
            connect();
            String strSelect = "select count(*) from po\n"
                    + "where curriculum_id = ? and is_active = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(curriculum_id)));
            pstm.setString(2, isactive);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getPoByID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }

    public static ArrayList<PO> getPoSearch(String txt, String curriculum_id) {
        ArrayList<PO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * \n"
                    + "FROM po"
                    + " where name like '" + "%" + txt + "%" + "'"
                    + " OR po_id like '" + "%" + txt + "%" + "'"
                    + "and curriculum_id = ?";

            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(curriculum_id)));
            rs = pstm.executeQuery();
            while (rs.next()) {
                String po_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String description = rs.getString(3);
                String is_active = String.valueOf(rs.getInt(5));
                list.add(new PO(po_id, name, description, curriculum_id, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getPoSearch: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
    // Unlock  Po

    public static void unlock(String po_id) {

        try {
            connect();
            String strSelect = "UPDATE po "
                    + "SET is_active = 1 "
                    + "WHERE (po_id = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, po_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Unlock:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    // Lock  PO
    public static void lock(String po_id) {
        try {
            connect();
            String strSelect = "UPDATE po "
                    + "SET is_active = 0 "
                    + "WHERE (po_id = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, po_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("lock:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
    
    // Delete  PO
    public static void delete(String po_id) {
        try {
            connect();
            String strDelete = "delete from po where po_id=?";
            pstm = cnn.prepareStatement(strDelete);           
            pstm.setString(1, po_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("delete:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
    
    

    public static PO getPoByID(String poID) {
        PO list = new PO();
        try {
            connect();
            String strSelect = "SELECT po_id, p.name, p.description, p.curriculum_id, p.is_active, c.code\n"
                    + "FROM po p left join curriculum c on p.curriculum_id = c.curriculum_id \n"
                    + "where po_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, poID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String po_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String description = rs.getString(3);
                String curriculum_id = String.valueOf(rs.getInt(4));
                String is_active = String.valueOf(rs.getInt(5));
                String code = rs.getString(6);
                list = new PO(po_id, name, description, curriculum_id, is_active, code);
            }
        } catch (SQLException e) {
            System.err.println("getPoByID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static boolean checkPoName(String name, String curriculum_id) {
        try {
            connect();

            String strSelect = "select * from po where name = ? and curriculum_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, name);
            pstm.setString(2, curriculum_id);
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

    public static void addPo(PO po) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`po` (`name`, `description`, `curriculum_id`,`is_active`)"
                    + " VALUES (?, ?, ?, b?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, po.getName());
            pstm.setString(2, po.getDescription());
            pstm.setString(3, po.getCurriculum_id());
            pstm.setString(4, po.getIs_active());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addPo:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void updatePo(PO po) {
        try {
            connect();
            String strUpdate = "update po "
                    + "SET `name` =?,"
                    + "`description` = ?, `curriculum_id`=?,\n"
                    + "`is_active` = b?\n"
                    + "where po_id=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, po.getName());
            pstm.setString(2, po.getDescription());
            pstm.setString(3, po.getCurriculum_id());
            pstm.setString(4, po.getIs_active());
            pstm.setString(5, po.getPo_id());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updatePo:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static ArrayList<PO> getListPoByCode(String cu_id) {
        ArrayList<PO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select p.po_id, p.name\n"
                    + "from po as p join curriculum as c \n"
                    + "on p.curriculum_id = c.curriculum_id \n"
                    + "where c.curriculum_id = ? and p.is_active = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cu_id);
            pstm.setString(2, "1");
            rs = pstm.executeQuery();
            while (rs.next()) {
                String po_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
               
                list.add(new PO(po_id, name));
            }
        } catch (SQLException e) {
            System.err.println("getListPoByCode: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

}
