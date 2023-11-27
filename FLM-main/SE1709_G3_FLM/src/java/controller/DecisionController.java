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
import java.util.ArrayList;
import model.Decision;
import dao.DecisionDAO;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.User;

/**
 *
 * @author Asus
 */
public class DecisionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Decision> dataDe = new ArrayList<Decision>();
        if (req.getParameter("btn_search") != null) {
            String textsearch = req.getParameter("textsearch");
            dataDe.clear();
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("account");
            dataDe = DecisionDAO.getListDecisionSearchNoName(textsearch, u.getUser_id());
            req.setAttribute("isSearch", "true");
            req.setAttribute("dataDe", dataDe);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", 1);
            req.getRequestDispatcher("/view/decision/manageDecision.jsp").forward(req, resp);
            return;
        } else if (req.getParameter("btn_sort") != null) {
            String sort = req.getParameter("sort");
            dataDe.clear();
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("account");
            dataDe = DecisionDAO.getListDecisionSort(sort, u.getUser_id());
            req.setAttribute("isSearch", "true");
            req.setAttribute("dataDe", dataDe);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", 1);
            req.getRequestDispatcher("/view/decision/manageDecision.jsp").forward(req, resp);
            return;
        } else {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = currentDate.format(formatter);

            req.setAttribute("currentDate", formattedDate);
            req.getRequestDispatcher("/view/decision/newDecision.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("1")) {
            if (session.getAttribute("role") != null && (session.getAttribute("role").equals("Head") || session.getAttribute("role").equals("Staff"))) {
                String page = req.getParameter("page");
                ArrayList<Decision> dataSubject = DecisionDAO.getDecisionListPaging(page);
                req.setAttribute("dataDe", dataSubject);
                int count = DecisionDAO.getTotalDecision();
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
                req.getRequestDispatcher("/view/decision/manageDecision.jsp").forward(req, resp);
                return;
            } else {
                req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);
            }
        } else if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("2")) {
            if (session.getAttribute("role") != null) {
                String id = req.getParameter("id");
                Decision dataDe = DecisionDAO.getDecisionByDecisionID(id);
                req.setAttribute("dataDe", dataDe);
                req.getRequestDispatcher("/view/decision/viewDecision.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);

            }

        } else {
            req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);

        }

    }
}
