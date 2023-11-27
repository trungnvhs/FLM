/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumSubjectDAO;
import dao.GroupDAO;
import dao.SubjectDAO;
import dao.SyllabusDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.CurriculumSubject;

/**
 *
 * @author quan
 */
public class curriculumSubjectController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String curriculumid = (String) session.getAttribute("curID");
        String subjectid = req.getParameter("subjectid");
        String syllabusid = req.getParameter("syllabusid");
        String groupid = req.getParameter("groupid");
        String semester = req.getParameter("semester");
        String nocredit = req.getParameter("nocredit");
        String isactive = req.getParameter("isActive");

            
        
        
            
        
        if (isactive == null) {
                isactive = "0";
            }
            boolean check = false;
            //check subjectid trong curriculum
            boolean checksub1 = CurriculumSubjectDAO.checkSubjectIdInCurriculum(curriculumid, subjectid);
            //check subjectid trong subject
            boolean checksub2 = SubjectDAO.checkSubjectIdInSubject(subjectid);
            //check syllabus id có trong sylabus hay không
            boolean checksyid = SyllabusDAO.checkSyllabusid(syllabusid);
            //check group id có trong group hay không 
            boolean checkgroupid = GroupDAO.checkgroupid(groupid);

            if (checksub1) {
                check = true;
                req.setAttribute("error1", "Subject existed in Curriculum!!");
            }
            if (!checksub2) {
                check = true;
                req.setAttribute("error2", "Subject not existed in Subject table!!");
            }
            if (!checksyid) {
                check = true;
                req.setAttribute("error3", "Syllabus not existed in Syllabus table!!");
            }
            System.out.println(groupid);
            if (!checkgroupid) {
                check = true;
                req.setAttribute("error4", "Group not existed in Group table!!");
            }

            if (check) {
                req.setAttribute("curriculumid", curriculumid);
                req.setAttribute("subjectid", subjectid);
                req.setAttribute("groupid", groupid);
                req.setAttribute("syllabusid", syllabusid);
                req.setAttribute("semester", semester);
                req.setAttribute("nocredit", nocredit);
                req.setAttribute("isactive", isactive);
                req.getRequestDispatcher("/view/curriculumSubject/addCurriculumSubject.jsp").forward(req, resp);
                return;
            } else {
                CurriculumSubjectDAO.addCurriculum(curriculumid, subjectid, syllabusid, groupid, semester, nocredit, isactive);
                req.setAttribute("error5", "add success full !");
                req.setAttribute("curriculumid", curriculumid);
                req.setAttribute("subjectid", subjectid);
                req.setAttribute("groupid", groupid);
                req.setAttribute("syllabusid", syllabusid);
                req.setAttribute("semester", semester);
                req.setAttribute("nocredit", nocredit);
                req.setAttribute("isactive", isactive);
                req.getRequestDispatcher("/view/curriculumSubject/addCurriculumSubject.jsp").forward(req, resp);

            }

        }
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("1")) {
            HttpSession session = req.getSession();
            String curriculumid = (String) session.getAttribute("curID");
            String page = req.getParameter("page");
            ArrayList<CurriculumSubject> dataCur = CurriculumSubjectDAO.getListCurriculumSubject(curriculumid, page);
            int count = CurriculumSubjectDAO.getTotalCurSUB(curriculumid);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            System.out.println(endPage);
            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
            req.setAttribute("dataCur", dataCur);
            req.getRequestDispatcher("/view/curriculumSubject/curriculumSubjectList.jsp").forward(req, resp);

        }
    }

}
