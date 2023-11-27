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
import model.PLO;

/**
 *
 * @author MSI
 */
public class PLO_DAO {

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

    public static ArrayList<PLO> getPLOList(String curriculim_id) {
        ArrayList<PLO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select * from plo where curriculum_id = ?; ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, curriculim_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String plo_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String description = rs.getString(3);
                String curriculum_id = String.valueOf(rs.getInt(4));

                list.add(new PLO(plo_id, name, description, curriculum_id));
            }
        } catch (SQLException e) {
            System.err.println("getPLOList: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static ArrayList<PLO> getPLOListPaging(String page, String curriculum_id) {
        ArrayList<PLO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT * FROM swp391_g3_flm.plo where curriculum_id = ?\n"
                    + "order by plo_id\n"
                    + "limit ?, 10;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(curriculum_id)));
            pstm.setInt(2, (Integer.parseInt(page) - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String plo_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String description = rs.getString(3);
                list.add(new PLO(plo_id, name, description, curriculum_id));
            }
        } catch (SQLException e) {
            System.err.println("getPLOListPaging: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static int getTotalPLO(String curriculum_id) {
        try {
            connect();
            String strSelect = "select count(*) from plo where curriculum_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (Integer.parseInt(curriculum_id)));
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("getTotalPLO: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }

    public static PLO getPLOByID(String plo_id) {
        PLO p = new PLO();
        try {
            connect();
            System.out.println("vào được");
            String strSelect = "select * from plo where plo_id like ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, plo_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                String description = rs.getString(3);
                String curriculum_id = String.valueOf(rs.getInt(4));

                p = new PLO(plo_id, name, description, curriculum_id);
            }
        } catch (SQLException e) {
            System.err.println("getPLOByID: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return p;
    }

    public static boolean checkPLO_id(String code) {
        try {
            connect();

            String strSelect = "select * from plo where plo_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, code);

            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkPLOCode:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static boolean checkPLO_name(String name, String curID) {
        try {
            connect();

            String strSelect = "select * from plo where name = ? and curriculum_id = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, name);
            pstm.setString(2, curID);
            rs = pstm.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkPLOCode:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static void addPLO(PLO p) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `description`, `curriculum_id`) VALUES (?, ?, ?, ?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, p.getPlo_id());
            pstm.setString(2, p.getName());
            pstm.setString(3, p.getDescription());
            pstm.setString(4, p.getCurriculum_id());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addPLO:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void updatePLO(PLO p) {
        try {
            connect();
            String strUpdate = "UPDATE `swp391_g3_flm`.`plo` SET `name` = ?, `description` = ?, `curriculum_id` = ? WHERE `plo_id` = ?;";

            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, p.getName());
            pstm.setString(2, p.getDescription());
            pstm.setString(3, p.getCurriculum_id());
            pstm.setString(4, p.getPlo_id());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error in updatePLO: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static ArrayList<PLO> getListPloByCode(String code) {
        ArrayList<PLO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select p.plo_id, p.name, p.description, p.curriculum_id, p.is_active\n"
                    + "from plo as p join curriculum as c \n"
                    + "on p.curriculum_id = c.curriculum_id \n"
                    + "where c.code = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String plo_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String description = rs.getString(3);
                String curriculum_id = String.valueOf(rs.getInt(4));
                String is_active = String.valueOf(rs.getInt(5));

                list.add(new PLO(plo_id, name, description, curriculum_id, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getListPloByCode: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

     public static ArrayList<PLO> getListPloByCode1(String cu_id) {
        ArrayList<PLO> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select p.plo_id, p.name, p.description, p.curriculum_id, p.is_active\n"
                    + "from plo as p join curriculum as c \n"
                    + "on p.curriculum_id = c.curriculum_id \n"
                    + "where c.curriculum_id = ? and p.is_active = ?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cu_id);
            pstm.setString(2, "1");
            rs = pstm.executeQuery();
            while (rs.next()) {
                String plo_id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String description = rs.getString(3);
                String curriculum_id = String.valueOf(rs.getInt(4));
                String is_active = String.valueOf(rs.getInt(5));

                list.add(new PLO(plo_id, name, description, curriculum_id, is_active));
            }
        } catch (SQLException e) {
            System.err.println("getListPloByCode: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
    public static void addPLO1(PLO p) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`plo` (`name`, `description`, `curriculum_id`) VALUES (?, ?, ?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, p.getName());
            pstm.setString(2, p.getDescription());
            pstm.setString(3, p.getCurriculum_id());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addPLO:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
}
