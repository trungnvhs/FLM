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
import model.Subject;
import dao.SubjectDAO;
import dao.SyllabusDAO;
import jakarta.servlet.http.HttpSession;
import model.Pre_requisite;
import model.Syllabus;

/**
 *
 * @author ThinkPad P50
 */
public class SubjectSuccController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("search")) {
            SubjectDAO subjectDao = new SubjectDAO();
            String text = req.getParameter("text");
            boolean check = subjectDao.checkSubject(text);
            if (text.equals("")) {
                req.setAttribute("error", "Please enter subject code!");
            } else if (check == true) {
                Syllabus s = SyllabusDAO.getSyllabus(text);
                ArrayList<Pre_requisite> sList = SyllabusDAO.getSuc(text);
                req.setAttribute("s", s);
                req.setAttribute("sList", sList);

            } else {
                req.setAttribute("error1", "Subject code <b>" + req.getParameter("text") + "</b> does not exist or has no syllabus");
            }
            req.setAttribute("search", req.getParameter("text"));
            req.getRequestDispatcher("/view/subject/subjectSuccessors.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/subject/subjectSuccessors.jsp").forward(req, resp);
    }

}
