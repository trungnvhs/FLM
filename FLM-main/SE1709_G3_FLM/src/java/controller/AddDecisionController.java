/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DecisionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import model.Decision;
import model.User;

/**
 *
 * @author Asus
 */
public class AddDecisionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String decisionNo = req.getParameter("decisionNo");
        String decisionName = req.getParameter("decisionName");
        String decisionDate = req.getParameter("decisionDate");
        String createdid = req.getParameter("createdid");
        String note = req.getParameter("note");
        Decision d = new Decision();
        boolean check = DecisionDAO.checkDecisionNo(decisionNo);

        //nếu code decision trùng nhau trả về trang add
        if (check) {
            req.setAttribute("note", note);
            req.setAttribute("decisionNo", decisionNo);
            req.setAttribute("decisionName", decisionName);
            req.setAttribute("currentDate", decisionDate);
            req.setAttribute("createdid", createdid);
            req.setAttribute("error1", "Decision has already!");
            req.getRequestDispatcher("/view/decision/newDecision.jsp").forward(req, resp);
            return;
            //ko trùng thì add decision
        } else {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
            try {
                Date date = inputFormat.parse(decisionDate);
                String formattedDate = outputFormat.format(date);
                DecisionDAO.addDecision(decisionNo, decisionName, note, formattedDate, createdid);

                int count = DecisionDAO.getTotalDecision();
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                String page = String.valueOf(endPage);
                HttpSession session = req.getSession();
                User u = (User) session.getAttribute("account");
                ArrayList<Decision> dataDe = DecisionDAO.getDecisionListPaging(page);
                req.setAttribute("dataDe", dataDe);
                req.setAttribute("page", endPage);
                req.setAttribute("mess", "Add decision successfull!!");
                req.setAttribute("endPage", endPage);
                req.getRequestDispatcher("/view/decision/manageDecision.jsp").forward(req, resp);
                return;
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            DecisionDAO.addDecision(decisionNo, decisionName, note, formattedDate, createdid);
//
//            int count = DecisionDAO.getTotalDecision();
//            int endPage = count / 10;
//            if (count % 10 != 0) {
//                endPage++;
//            }
//            String page = String.valueOf(endPage);
//            HttpSession session = req.getSession();
//            User u = (User) session.getAttribute("account");
//            ArrayList<Decision> dataDe = DecisionDAO.getDecisionListPaging(page, u.getUser_id());
//            req.setAttribute("dataDe", dataDe);
//            req.setAttribute("page", endPage);
//            req.setAttribute("mess", "Add decision successfull!!");
//            req.setAttribute("endPage", endPage);
//            req.getRequestDispatcher("/view/decision/manageDecision.jsp").forward(req, resp);
//            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        if (session.getAttribute("account") != null) {
            ArrayList<Decision> dataDe = DecisionDAO.getDecisionListPaging("1");
            req.setAttribute("dataDe", dataDe);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", 1);
            req.setAttribute("dataDe", dataDe);
            req.getRequestDispatcher("/view/decision/manageDecision.jsp").forward(req, resp);
            return;
        } else {
            req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);

        }

    }

}
