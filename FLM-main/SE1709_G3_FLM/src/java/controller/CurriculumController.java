/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Curriculum;
import model.User;

/**
 *
 * @author Asus
 */
public class CurriculumController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Curriculum> dataCur = new ArrayList<Curriculum>();
        if (req.getParameter("btn_search") != null) {
            String textsearch = req.getParameter("textsearch");
            dataCur.clear();
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("account");
            dataCur = CurriculumDAO.getListCurriculumSearch(textsearch, u.getUser_id());
            req.setAttribute("isSearch", "true");
            req.setAttribute("dataCu", dataCur);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", 1);
            req.getRequestDispatcher("/view/curriculum/manageCurricula.jsp").forward(req, resp);
            return;
        } else if (req.getParameter("btn_filter") != null) {
            String filterStatus = req.getParameter("filterStatus");
            dataCur.clear();
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("account");
            dataCur = CurriculumDAO.getListCurriculumStatus(filterStatus, u.getUser_id());
            req.setAttribute("isSearch", "true");
            req.setAttribute("dataCu", dataCur);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", 1);
            req.getRequestDispatcher("/view/curriculum/manageCurricula.jsp").forward(req, resp);
            return;
        } else {
            ArrayList<User> dataStaff = CurriculumDAO.getStaff();
            req.setAttribute("dataStaff", dataStaff);
            req.getRequestDispatcher("/view/curriculum/newCurriculum.jsp").forward(req, resp);
            return;
        }
//        dataCur = CurriculumDAO.getListCurriculum();
//        req.setAttribute("dataCur", dataCur);
//        req.getRequestDispatcher("/view/curriculum/manageCurricula.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("1")) {
            String roleid = null;
            if (session.getAttribute("account") != null) {
                roleid = CurriculumDAO.getRoleId(u.getUser_id());
            }
            if (roleid == null || roleid.equals("1") || roleid.equals("2") || roleid.equals("3")) {
                String page = req.getParameter("page");
                ArrayList<Curriculum> dataSubject = CurriculumDAO.getListCurriculumDefault();
                req.setAttribute("dataCu", dataSubject);
                int count = CurriculumDAO.getTotalCurriculum();
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
                req.getRequestDispatcher("/view/curriculum/manageCurricula.jsp").forward(req, resp);
                return;
            } else {
                String page = req.getParameter("page");
                ArrayList<Curriculum> dataSubject = CurriculumDAO.getCurriculumListPaging(page, u.getUser_id());
                req.setAttribute("dataCu", dataSubject);
                int count = CurriculumDAO.getTotalCurriculum();
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }

                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
                req.getRequestDispatcher("/view/curriculum/manageCurricula.jsp").forward(req, resp);
                return;
            }
        } else if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("2")) {
            String id = req.getParameter("id");
            CurriculumDAO cu = new CurriculumDAO();
            Curriculum dataCu = cu.getCurriculumByCurriculumID(id);
            String decisionnodate = CurriculumDAO.getDecisionNoDate(dataCu.getDecision_id());
            session.setAttribute("curID", req.getParameter("id"));
            req.setAttribute("decisionnodate", decisionnodate);
            req.setAttribute("dataCu", dataCu);
            req.getRequestDispatcher("/view/curriculum/viewCurriculum.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);

        }

    }

}
