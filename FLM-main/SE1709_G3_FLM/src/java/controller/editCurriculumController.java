/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.GroupDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Curriculum;

/**
 *
 * @author quan
 */
public class editCurriculumController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String decisionid = req.getParameter("decisionid");
        String ownerid = req.getParameter("ownerid");
        String totalcredit = req.getParameter("totalcredit");
        String isActive = req.getParameter("isActive");

        if (isActive == null) {
            isActive = "0";
        } else {
            isActive = "1";
        }

        CurriculumDAO.editCurriculum(id, code, name, description, decisionid, totalcredit, ownerid, isActive);
        CurriculumDAO cu = new CurriculumDAO();
        Curriculum dataCu = cu.getCurriculumByCurriculumID(id);
        String decisionnodate = CurriculumDAO.getDecisionNoDate(dataCu.getDecision_id());
        String assignee = CurriculumDAO.getAssigneeName(dataCu.getOwner_id());
        String creator = CurriculumDAO.getCreatorName(dataCu.getCreator_id());
        req.setAttribute("dataCu", dataCu);
        req.setAttribute("assignee", assignee);
        req.setAttribute("creator", creator);
        req.setAttribute("decisionnodate", decisionnodate);
        req.setAttribute("mess", "Edit Curriculum succesfull!");
        req.getRequestDispatcher("/view/curriculum/editCurriculum.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CurriculumDAO cu = new CurriculumDAO();
        Curriculum dataCu = cu.getCurriculumByCurriculumID(id);
        String decisionnodate = CurriculumDAO.getDecisionNoDate(dataCu.getDecision_id());
        String assignee = CurriculumDAO.getAssigneeName(dataCu.getOwner_id());
        String creator = CurriculumDAO.getCreatorName(dataCu.getCreator_id());
        req.setAttribute("assignee", assignee);
        req.setAttribute("creator", creator);
        req.setAttribute("decisionnodate", decisionnodate);
        req.setAttribute("dataCu", dataCu);
        req.getRequestDispatcher("/view/curriculum/editCurriculum.jsp").forward(req, resp);
    }

}
