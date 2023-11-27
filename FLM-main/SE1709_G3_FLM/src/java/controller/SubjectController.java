/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Subject;

/**
 *
 * @author Trung Quan
 */
public class SubjectController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("add")) {
            boolean check = false;
            String code = req.getParameter("code");
            String name = req.getParameter("name");
            String type = req.getParameter("type");
            String description = req.getParameter("description");
            String isActive = req.getParameter("isActive");
            if (SubjectDAO.checkSubjectCode(code)) {
                check = true;
                req.setAttribute("error1", "Subject code has existed!");
            }
            if (check) {
                req.setAttribute("code", req.getParameter("code"));
                req.setAttribute("name", req.getParameter("name"));
                req.setAttribute("type", req.getParameter("type"));
                req.setAttribute("description", req.getParameter("description"));
                req.setAttribute("isActive", req.getParameter("isActive"));
                req.setAttribute("parentCode", req.getParameter("parentCode"));
                req.setAttribute("groupCode", req.getParameter("groupCode"));
                req.getRequestDispatcher("/view/subject/newSubject.jsp").forward(req, resp);
                return;
            } else {
                Subject s = new Subject(1, code, name, type, isActive, description, null, null);
                SubjectDAO.addSubject(s);
                
                int count = SubjectDAO.getTotalSubject();
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                String page = String.valueOf(endPage);
                ArrayList<Subject> dataSubject = SubjectDAO.getSubjectListPaging(page);
                req.setAttribute("dataSubject", dataSubject);
                req.setAttribute("page", endPage);
                req.setAttribute("mess", "Add new subject successfull!");
                req.setAttribute("endPage", endPage);
                req.getRequestDispatcher("/view/subject/subjectList.jsp").forward(req, resp);
                return;
            }
        }

        if (req.getParameter("mod") != null && req.getParameter("mod").equals("search")) {
            String text = req.getParameter("text");
            int count = SubjectDAO.getTotalSubject(text);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<Subject> dataSubject = SubjectDAO.getSubListBySearch(text, "1");
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.setAttribute("search", req.getParameter("text"));
            req.setAttribute("dataSubject", dataSubject);
            req.getRequestDispatcher("/view/subject/subjectList.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mod") != null 
                && req.getParameter("mod").equals("1")) {
            String page = req.getParameter("page");
            if (req.getParameter("search") != null) {
                String text = req.getParameter("search");
                int count = SubjectDAO.getTotalSubject(text);
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                ArrayList<Subject> dataSubject = SubjectDAO.getSubListBySearch(text, page);
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
                req.setAttribute("search", req.getParameter("search"));
                req.setAttribute("dataSubject", dataSubject);
                req.getRequestDispatcher("/view/subject/subjectList.jsp").forward(req, resp);
                return;
            }
            ArrayList<Subject> dataSubject = SubjectDAO.getSubjectListPaging(page);
            req.setAttribute("dataSubject", dataSubject);
            int count = SubjectDAO.getTotalSubject();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }

            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/subject/subjectList.jsp").forward(req, resp);
            return;
        }
        String subjectID = req.getParameter("id");
        Subject s = SubjectDAO.getSubByID(subjectID);
        req.setAttribute("subject", s);
        req.getRequestDispatcher("/view/subject/subjectDetail.jsp").forward(req, resp);
    }

}
