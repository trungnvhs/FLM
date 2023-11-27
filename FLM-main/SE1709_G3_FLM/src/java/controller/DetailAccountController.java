/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.UserDAO;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author ThinkPad P50
 */
public class DetailAccountController extends HttpServlet {

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
        UserDAO userd = new UserDAO();
        int count = userd.getTotalAccount();
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        req.setAttribute("endP", endPage);
        req.setAttribute("tag", index);
        ArrayList<User> data = userd.getAccountList(index);
        req.setAttribute("data", data);
        req.getRequestDispatcher("/view/userAccess/accountList.jsp").forward(req, resp);
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
        req.getRequestDispatcher("/view/userAccess/accountDetail.jsp").forward(req, resp);

    }

}
