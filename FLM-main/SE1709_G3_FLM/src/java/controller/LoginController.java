package controller;

import dao.RoleDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Role;
import model.User;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Asus
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        User u = new User(account, password);
        if (account.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {

            boolean check = UserDAO.checkEmailPassWord(account, password);
            if (check) {

                //Get tất cả các thuộc tính của Account
                u = UserDAO.getInfoByEmail(account);
                //Lưu thông tin vào session
                HttpSession session = req.getSession();
                session.setAttribute("account", u);
                session.setAttribute("role", RoleDAO.getRole(u.getUser_id()));
                  req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Username or password is incorrect*");
                req.getRequestDispatcher("/view/userAccess/login.jsp").forward(req, resp);
            }
        } else if (account.matches("^[+]?[0-9]{8,15}$")) {

            boolean check = UserDAO.checkMobilePassword(account, password);
            if (check) {
                u = UserDAO.getInfoByMobile(account);
                //Lưu thông tin vào session
                HttpSession session = req.getSession();
                session.setAttribute("account", u);
                session.setAttribute("role", RoleDAO.getRole(u.getUser_id()));
                req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Username or password is incorrect*");
                req.getRequestDispatcher("/view/userAccess/login.jsp").forward(req, resp);
            }
        } else {
            boolean check = UserDAO.checkUserName(account, password);
            if (check) {
                u = UserDAO.getInfoByUserName(account);
                //Lưu thông tin vào session
                HttpSession session = req.getSession();
                session.setAttribute("account", u);
                session.setAttribute("role", RoleDAO.getRole(u.getUser_id()));
                req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);
            } else {
                //khi nhập sai tk và mkhau sẽ hiển thị thông báo
                req.setAttribute("error", "Username or password is incorrect*");
                req.getRequestDispatcher("/view/userAccess/login.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/userAccess/login.jsp").forward(req, resp);
        //resp.sendRedirect("login/Login.jsp");
    }

}
