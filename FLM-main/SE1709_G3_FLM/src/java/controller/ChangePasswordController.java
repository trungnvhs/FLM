/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;

/**
 *
 * @author Trung Quan
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/view/userAccess/ChangePassword"})
public class ChangePasswordController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
//        if (session.getAttribute("account") == null) {
//            request.setAttribute("error", "<script>alert('Login to continue!!!');</script>");
//            request.getRequestDispatcher("/view/userAccess/.jsp").forward(request, response);
//            /**
//             * Thêm tên file
//             */
//        }
        request.getRequestDispatcher("/view/userAccess/changePass.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("account");
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String preNewPass = request.getParameter("preNewPass");
        if(request.getParameter("cancel")!=null){
            request.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(request, response);
            return;
        }
        // Check password đã nhập đúng hay chưa
        if (!u.getPassword().equals(oldPass)) {
            request.setAttribute("oldPass", request.getParameter("oldPass"));
            request.setAttribute("newPass", request.getParameter("newPass"));
            request.setAttribute("preNewPass", request.getParameter("preNewPass"));
            request.setAttribute("err1", "Current password incorrect.");
        } else {
            if (oldPass.equals(newPass)) {
                request.setAttribute("oldPass", request.getParameter("oldPass"));
                request.setAttribute("newPass", request.getParameter("newPass"));
                request.setAttribute("preNewPass", request.getParameter("preNewPass"));
                request.setAttribute("err2", "New password can't match with current password .");
            } else if (!newPass.equals(preNewPass)) {
                request.setAttribute("oldPass", request.getParameter("oldPass"));
                request.setAttribute("newPass", request.getParameter("newPass"));
                request.setAttribute("preNewPass", request.getParameter("preNewPass"));
                request.setAttribute("err3", "Confirm password incorrect");
            } else {
                // Update New Password into DB
                boolean check = UserDAO.UpdatePass(u.getUser_id(), newPass);
                if (check) {
                    request.setAttribute("success", "<script>alert('Change password successfull.');</script>");
                } else {
                    request.setAttribute("success", "<script>alert('Change password failed.');</script>");
                }
            }
        }
        request.getRequestDispatcher("/view/userAccess/changePass.jsp").forward(request, response);
    }

}
