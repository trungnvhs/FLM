/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SettingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.UserDAO;
import dao.RoleDAO;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Role;
import model.Setting;
import model.User;

/**
 *
 * @author ThinkPad P50
 */
public class AccountDetailEditController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Check đã login hay chưa
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        if (session.getAttribute("account") == null) {
            req.setAttribute("error", "<script>alert('Login to continue!!!');</script>");
            req.setAttribute("checkLogin", true);
            req.getRequestDispatcher("/view/userAccess/login.jsp").forward(req, resp);
        }
        
        String user_id = req.getParameter("user_id");
        String fullName = req.getParameter("fullName");
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String role_id = req.getParameter("role_id");
        String job = req.getParameter("job");
        String company = req.getParameter("company ");
//        , userName, email, mobile, job, company
        UserDAO.updateUser(user_id, fullName, mobile, job, company);
        RoleDAO.updateRole(user_id, role_id);
        
        UserDAO userd = new UserDAO();        
        ArrayList<User> data = userd.getInfoAccount(user_id);        
        req.setAttribute("data", data);        
        req.getRequestDispatcher("/view/userAccess/accountDetail.jsp").forward(req, resp);
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Check đã login hay chưa
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        if (session.getAttribute("account") == null) {
            req.setAttribute("error", "<script>alert('Login to continue!!!');</script>");
            req.setAttribute("checkLogin", true);
            req.getRequestDispatcher("/view/userAccess/login.jsp").forward(req, resp);
        }
        //Nhận thông tin từ JSP - tham số ACC
        String id = req.getParameter("id");
        //GỌi đến class USer để getUser tương ứng với acc
        UserDAO userd = new UserDAO();
        ArrayList<User> data = userd.getInfoAccount(id);
        req.setAttribute("data", data);
        SettingDAO settingd = new SettingDAO();
        ArrayList<Setting> datas = settingd.getSettingListName();
        req.setAttribute("datas", datas);
        req.getRequestDispatcher("/view/userAccess/accountDetailEdit.jsp").forward(req, resp);
    }
    
}
