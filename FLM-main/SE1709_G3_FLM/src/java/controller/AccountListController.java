/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.User;
import dao.UserDAO;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ThinkPad P50
 */
@WebServlet(name = "AccountListController", urlPatterns = {"/view/userAccess/accountlist"})
public class AccountListController extends HttpServlet {

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
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String txt = req.getParameter("txt");

        
        if (req.getParameter("Search") != null) {
            ArrayList<User> data = userd.getSearchName(txt);
            req.setAttribute("data", data);
        }
       int count = userd.getTotalAccount();
        int endPage = count / 10;
        if (count % 10 != 0) {
            endPage++;
        }
        String page = req.getParameter("page");
        if (page == null) {
            page = "1";
        }
        
        req.setAttribute("page", page);
        req.setAttribute("endPage", endPage);
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
        UserDAO userd = new UserDAO();
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("1")) {
            String id = req.getParameter("id");
            userd.unlock(id);
            req.setAttribute("mess", "Unlock User ID " + id + " successfull!");
        }
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("2")) {
            String id = req.getParameter("id");
            userd.lock(id);
            req.setAttribute("mess", "Lock User ID " + id + " successfull!");
        }
        int count = userd.getTotalAccount();
        int endPage = count / 10;
        if (count % 10 != 0) {
            endPage++;
        }
        String page = req.getParameter("page");
        if (page == null) {
            page = "1";
        }
        
        req.setAttribute("page", page);
        req.setAttribute("endPage", endPage);
        ArrayList<User> data = userd.getAccountList(Integer.parseInt(page));
        req.setAttribute("data", data);
        req.getRequestDispatcher("/view/userAccess/accountList.jsp").forward(req, resp);
    }

}
