/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import model.User;

public class RegisterPhoneController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String code = req.getParameter("code");
        String newPass = req.getParameter("newPass");
        String reNewPass = req.getParameter("reNewPass");
        String avatar = req.getParameter("avatar");
        
        boolean check = UserDAO.checkMobile(phone);
        if (!check) {
            
            UserDAO.addUserPhone(newPass, phone, avatar);
            User u = UserDAO.getInfoByMobile(phone);
            //get user id de lay role id
            String userid = u.getUser_id();
            UserDAO.addRoleUserIDPhone(userid);
            req.setAttribute("mess", "Register is successfully!");
            req.getRequestDispatcher("/view/userAccess/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error3", "Phone is exist*");
            req.setAttribute("phone", phone);
            req.setAttribute("code", code);
            req.setAttribute("newPass", newPass);
            req.setAttribute("reNewPass", reNewPass);
            req.getRequestDispatcher("/view/userAccess/registerPhone.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/userAccess/registerPhone.jsp").forward(req, resp);

    }

}
