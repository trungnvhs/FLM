/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SyllabusDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Syllabus;
import model.User;

/**
 *
 * @author MSI
 */
public class SyllabusController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("1")) {
            String page = req.getParameter("page");
            String text = req.getParameter("search");
            if (text == null) {
                text = "";
            }
            int count = SyllabusDAO.getTotalSyllabus(text);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<Syllabus> dataSyllabus = SyllabusDAO.getSyllabusBySearch(text, page);
            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
            req.setAttribute("search", req.getParameter("search"));
            req.setAttribute("dataSyllabus", dataSyllabus);
            req.getRequestDispatcher("/view/syllabus/syllabusManage.jsp").forward(req, resp);
            return;
        }

        if (req.getParameter("mod") != null && req.getParameter("mod").equals("view")) {
            String sysID = req.getParameter("id");
            Syllabus syl = SyllabusDAO.getSyllabusbyID(sysID);
            req.setAttribute("syl", syl);
            session.setAttribute("sysID", sysID);
            req.getRequestDispatcher("/view/syllabus/viewSyllabus.jsp").forward(req, resp);
        }
    }

}
