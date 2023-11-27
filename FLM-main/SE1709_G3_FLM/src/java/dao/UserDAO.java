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
import model.User;

/**
 *
 * @author Asus
 */
public class UserDAO {

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

    public static void addEmail(String email) {
        try {
            connect();

            String strAdd = "insert into user(email)values(?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, email);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("Add:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }
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

    public static boolean checkEmailPassWord(String email, String password) {
        try {
            connect();
            String strSelect = "select * from user where email =? and password =?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, email);
            pstm.setString(2, password);
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

    public static boolean checkEmail(String email) {
        try {
            connect();

            String strSelect = "select * from user where email =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, email);

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

    public static boolean checkMobilePassword(String mobile, String password) {
        try {
            connect();

            String strSelect = "select * from user where mobile =? and password =?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, mobile);
            pstm.setString(2, password);
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

    public static boolean checkMobile(String mobile) {
        try {
            connect();

            String strSelect = "select * from user where mobile =? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, mobile);

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

    public static boolean checkUserName(String user_name, String password) {
        try {
            connect();

            String strSelect = "select * from user where user_name =? and password =?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, user_name);
            pstm.setString(2, password);
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

    public static User getInfoByEmail(String emaill) {
        try {
            connect();

            String strSelect = "select * from user "
                    + "where email=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, emaill);
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

                return new User(user_id, full_name, email, mobile, user_name, password, avatar, job_title, company, status);
            }
        } catch (Exception e) {
            System.err.println("getAccount: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static User getInfoByMobile(String mobilee) {
        try {
            connect();

            String strSelect = "select * from user "
                    + "where mobile=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, mobilee);
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

                return new User(user_id, full_name, email, mobile, user_name, password, avatar, job_title, company, status);

            }
        } catch (Exception e) {
            System.err.println("getAccount: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static User getInfoByUserName(String userName) {
        try {
            connect();

            String strSelect = "select * from user "
                    + "where user_name=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, userName);
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

                return new User(user_id, full_name, email, mobile, user_name, password, avatar, job_title, company, status);
            }
        } catch (Exception e) {
            System.err.println("getAccount: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static User getRoleID(String userid) {
        try {
            connect();

            String strSelect = "select * from user "
                    + "where user_id=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, userid);
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

                return new User(user_id, full_name, email, mobile, user_name, password, avatar, job_title, company, status);

            }
        } catch (Exception e) {
            System.err.println("getAccount: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static void updatePassword(String password, String user_id) {
        try {
            connect();
            String strUpdate = "	update user"
                    + "     SET   password =? "
                    + "					  WHERE user_id = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, password);
            //pstm.setInt(3, Integer.parseInt(UnitsInStock));
            pstm.setString(2, user_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Update:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void updateUser(String user_id, String fullName, String mobile, String job, String company) {
        try {
            connect();
            String strUpdate = "UPDATE `swp391_g3_flm`.`user`"
                    + "SET `full_name` = ? "
                    + "`mobile` =? ,\n"
                    + "`job_title` =? ,\n"
                    + "`company` =? \n"
                    + "WHERE (`user_id` =  ?)";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, fullName);
            pstm.setString(2, mobile);
            pstm.setString(3, job);
            pstm.setString(4, company);
            pstm.setString(5, user_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Update:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void addDefaultAvatar(String user_id) {
        try {
            connect();
            String strAdd = "UPDATE `swp391_g3_flm`.`user` SET `avatar` = 'avatar.png' WHERE (`user_id` = ?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, user_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Add:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static boolean UpdatePass(String user_id, String newPass) {
        try {
            connect();

            String strSelect = "UPDATE user\n"
                    + "SET password = ?\n"
                    + "WHERE user_id=?;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, newPass);
            pstm.setString(2, user_id);
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.err.println("UpdatePass: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return false;
    }

    public static User getInfoByID(String fullname) {
        try {
            connect();

            String strSelect = "select * from user "
                    + "where user_id=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, fullname);
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

                return new User(user_id, full_name, email, mobile, user_name, password, avatar, job_title, company, status);
            }
        } catch (Exception e) {
            System.err.println("getAccount: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return null;
    }

    public static void UpdateInfor(User userNew, String id) {
        try {
            connect();

            String strSelect = "UPDATE user "
                    + "SET full_name = ?, email = ?, mobile = ?, user_name = ?"
                    + ", job_title = ?, company = ?"
                    + "WHERE (user_id = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, userNew.getFull_name());
            pstm.setString(2, userNew.getEmail());
            pstm.setString(3, userNew.getMobile());
            pstm.setString(4, userNew.getUser_name());
            pstm.setString(5, userNew.getJob_title());
            pstm.setString(6, userNew.getCompany());
            pstm.setString(7, id);
            pstm.execute();
        } catch (Exception e) {
            System.err.println("UpdateInfor: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void updateAvatar(String user_id, String fileName) {
        try {
            connect();

            String strSelect = "UPDATE user "
                    + "SET avatar = ? "
                    + "WHERE (user_id = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, fileName);
            pstm.setString(2, user_id);
            pstm.execute();
        } catch (Exception e) {
            System.err.println("updateAvatar: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void addNewUser(String newPass, String email, String avatar) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`user` ( `email`, `password`, `avatar`) VALUES (?, ?,?)";

            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, email);
            pstm.setString(2, newPass);
            pstm.setString(3, avatar);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("Add:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    public static void addUserPhone(String newPass, String phone, String avatar) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`user` ( `mobile`, `password`, `avatar`) VALUES (?, ?,?)";

            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, phone);
            pstm.setString(2, newPass);
            pstm.setString(3, avatar);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("Add:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    // Get List Account 
    public ArrayList<User> getAccountList(int page) {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, job_title, status,setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "order by user_id \n"
                    + "Limit ?,10";

            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (page - 1) * 10);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String job_title = rs.getString(6);
                String status = rs.getString(7);
                String setting_name = rs.getString(8);
                list.add(new User(user_id, full_name, email, mobile, user_name, job_title, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountList: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    // Get List Account ASC
    public ArrayList<User> getAccountListTop() {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "order by user_id asc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String status = rs.getString(9);
                String setting_name = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountListTop: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    // Get List Account DESC
    public ArrayList<User> getAccountListDown() {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,status, setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "order by user_id desc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String status = rs.getString(9);
                String setting_name = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountListTop: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    // Loc Account Verify
    public ArrayList<User> getAccountListVerify() {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,status, setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "where status = 1";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String status = rs.getString(9);
                String setting_name = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountListVerify: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    // Loc Account Not Verify
    public ArrayList<User> getAccountListNotVerify() {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,status, setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "where status = 0";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String status = rs.getString(9);
                String setting_name = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountListNotVerify: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    // Sort Name asc
    public ArrayList<User> getAccountListNameTop() {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,status, setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "order by full_name asc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String status = rs.getString(9);
                String setting_name = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountListNameTop: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

// Sort Name desc
    public ArrayList<User> getAccountListNameDown() {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,status, setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "order by full_name desc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String status = rs.getString(9);
                String setting_name = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountListNameDown: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    // sort mail asc
    public ArrayList<User> getAccountListMailTop() {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,status, setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "order by email asc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String status = rs.getString(9);
                String setting_name = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountListMailTop: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
// sort mail desc

    public ArrayList<User> getAccountListMailDown() {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,status, setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "order by email desc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String status = rs.getString(9);
                String setting_name = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountListMailDown: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
    // sort mobile asc

    public ArrayList<User> getAccountListMobileTop() {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,status, setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "order by mobile asc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String status = rs.getString(9);
                String setting_name = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountListMobileTop: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }
// sort mobile desc

    public ArrayList<User> getAccountListMobileDown() {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,status, setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + "order by mobile desc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String status = rs.getString(9);
                String setting_name = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, job_title, company, status, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getAccountListMailDown: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    // Search name, email, mobile, job
    public ArrayList<User> getSearchName(String txt) {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,setting_name\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + " where full_name like '" + "%" + txt + "%" + "'"
                    + " OR email like '" + "%" + txt + "%" + "'"
                    + " OR mobile like '" + "%" + txt + "%" + "'"
                    + " OR setting_name like '" + "%" + txt + "%" + "'"
                    + " OR job_title like '" + "%" + txt + "%" + "'";

            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String setting_name = rs.getString(9);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, avatar, job_title, company, avatar, setting_name));

            }
        } catch (Exception e) {
            System.err.println("getSearchName: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    // Get Info detail Account
    public ArrayList<User> getInfoAccount(String id) {
        ArrayList<User> list = new ArrayList<>();
        try {
            connect();
            String strSelect = "select  user.user_id , full_name, email, mobile, user_name, avatar, job_title, company,setting_name, setting_id\n"
                    + "from user join user_role\n"
                    + "on user_role.user_id = user.user_id \n"
                    + "join setting on user_role.role_id = setting.setting_id "
                    + " where user.user_id ='" + id + "' ";

            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String user_id = String.valueOf(rs.getInt(1));
                String full_name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String user_name = rs.getString(5);
                String avatar = rs.getString(6);
                String job_title = rs.getString(7);
                String company = rs.getString(8);
                String setting_name = rs.getString(9);
                String setting_id = rs.getString(10);
                list.add(new User(user_id, full_name, email, mobile, user_name, avatar, avatar, job_title, company, avatar, setting_name, setting_id));
            }
        } catch (Exception e) {
            System.err.println("getInfoAccount: " + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return list;
    }

    public static void addRoleUserID(String userid) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`user_role` (`role_id`, `user_id`) VALUES (1,?)";

            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, userid);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("Add:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }

    }

    public static void addRoleUserIDPhone(String userid) {
        try {
            connect();
            String strAdd = "INSERT INTO `swp391_g3_flm`.`user_role` (`role_id`, `user_id`) VALUES (1,?)";

            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, userid);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("Add:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    // Unlock  account
    public void unlock(String user_id) {

        try {
            connect();
            String strSelect = "UPDATE user "
                    + "SET status = 1 "
                    + "WHERE (user_id = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, user_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Unlock:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    // Lock  account
    public void lock(String user_id) {

        try {
            connect();
            String strSelect = "UPDATE user "
                    + "SET status = 0 "
                    + "WHERE (user_id = ?);";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, user_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("lock:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
    }

    //Total account
    public int getTotalAccount() {
        try {
            connect();
            String strSelect = "select count(*) from user";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getTotalAccount:" + e.getMessage());
        } finally {
            closeConnection(cnn, pstm, rs, stm);
        }
        return 0;
    }

}
