/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author quan
 */
public class LogoutController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Xóa thông tin người dùng khỏi session
    HttpSession session = request.getSession();
    session.invalidate();

    // Chuyển hướng đến trang đăng nhập hoặc trang chủ
    request.getRequestDispatcher("/view/userAccess/login.jsp").forward(request, response);;
}
    
}
