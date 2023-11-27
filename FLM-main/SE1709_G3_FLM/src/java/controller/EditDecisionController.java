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
import java.io.IOException;
import model.Decision;
import dao.CurriculumDAO;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.User;
import model.Curriculum;
import model.Syllabus;
import dao.SyllabusDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class EditDecisionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String decisionNo = req.getParameter("decisionNo");
        String decisionName = req.getParameter("decisionName");
        String approveddate = req.getParameter("approveddate");
        String note = req.getParameter("note");
        String decisionDate = req.getParameter("decisionDate");
        String curriculumdecision = req.getParameter("curriculumdecision");
        String syllabusdecision = req.getParameter("syllabusdecision");
        String createdid = req.getParameter("createdid");
        DecisionDAO d = new DecisionDAO();
        CurriculumDAO c = new CurriculumDAO();
        SyllabusDAO s = new SyllabusDAO();

        d.updateDecision(id, decisionNo, decisionName, approveddate, note, decisionDate, createdid);
        c.addDecisionForCurriculum(id, curriculumdecision);
        s.addDecisionForSyllabus(id, syllabusdecision);
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        ArrayList<Decision> dataDe = DecisionDAO.getDecisionListPaging("1");
        req.setAttribute("dataDe", dataDe);
        req.setAttribute("page", 1);
        req.setAttribute("endPage", 1);
        req.setAttribute("mess", "Edit decision successfull!!");
        req.getRequestDispatcher("/view/decision/manageDecision.jsp").forward(req, resp);
        return;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String creatorid = req.getParameter("creatorid");

        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        if (session.getAttribute("account") != null) {
            if (req.getParameter("mod") != null
                    && req.getParameter("mod").equals("1") && creatorid.equals(u.getUser_id())) {

                String decisionid = req.getParameter("id");
                String page = req.getParameter("page");
                DecisionDAO de = new DecisionDAO();
//                de.updateDecision(decisionid);
                de.removeDecision(decisionid);
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
            } else if (req.getParameter("mod") != null
                    && req.getParameter("mod").equals("2")
                    && creatorid.equals(u.getUser_id())) {
                String id = req.getParameter("id");
                Decision dataDe = DecisionDAO.getDecisionByDecisionID(id);
                ArrayList<Curriculum> dataCu = CurriculumDAO.getCurriculumNotHaveDecision();
                ArrayList<Syllabus> dataSy = SyllabusDAO.getSyllabusNotHaveDecision();
                System.out.println(dataSy.size());
                req.setAttribute("dataSy", dataSy);
                req.setAttribute("dataDe", dataDe);
                req.setAttribute("dataCu", dataCu);
                req.getRequestDispatcher("/view/decision/editDecision.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);

            }
        } else {
            req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);

        }
    }
}
