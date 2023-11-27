/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.CurriculumSubjectDAO;
import dao.GroupDAO;
import dao.SubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Curriculum;
import model.CurriculumSubject;
import model.Group;
import model.Subject;

/**
 *
 * @author quan
 */
public class ComboController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //chuyển sang trang add Combo 
        if (req.getParameter("add") != null) {
            HttpSession session = req.getSession();
            String curriculumid = (String) session.getAttribute("curID");
            Curriculum dataCu = CurriculumDAO.getCurriculumByCurriculumID(curriculumid);
            ArrayList<CurriculumSubject> dataCur = CurriculumSubjectDAO.getListCurriculumSubject3(curriculumid);
            req.setAttribute("dataCur", dataCur);
            req.setAttribute("dataCu", dataCu);
            req.getRequestDispatcher("/view/comboo/newcombo.jsp").forward(req, resp);

        }

        //add Combo controller
        if (req.getParameter("btn_oke") != null) {

            String curriculum_id = req.getParameter("curriculum_id");
            String isActive = "1";
            String subject_id = req.getParameter("subjectid");
            Subject datasub = SubjectDAO.getSubByID(subject_id);
            String name = datasub.getName();

            GroupDAO.addElective(name, curriculum_id, isActive);

            Group datagr = GroupDAO.getGroupByName(name);
            String group_id = datagr.getGroup_id();

            CurriculumSubjectDAO.UpdateTypeID(curriculum_id, subject_id, group_id);

            CurriculumDAO cu = new CurriculumDAO();
            Curriculum dataCu = cu.getCurriculumByCurriculumID(curriculum_id);
            req.setAttribute("dataCu", dataCu);
            ArrayList<Group> datae = GroupDAO.getListCombo(curriculum_id);
            req.setAttribute("dataelec", datae);
            System.out.println(curriculum_id);
            req.setAttribute("mess", "add new elective successfull!");

            req.getRequestDispatcher("/view/comboo/comboList.jsp").forward(req, resp);
        }

        

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //chuyen sang trang delete combo
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("delete")) {
            String groupID = req.getParameter("gr_id");
            String curriculumid = req.getParameter("cu_id");
            GroupDAO.deleteElective(groupID);
            CurriculumDAO cu = new CurriculumDAO();
            Curriculum dataCu = cu.getCurriculumByCurriculumID(curriculumid);
            req.setAttribute("dataCu", dataCu);
            ArrayList<Group> datae = GroupDAO.getListCombo(curriculumid);
            req.setAttribute("dataelec", datae);
            req.setAttribute("mess", "Delete successful!");
            req.setAttribute("groupid", groupID);

            System.out.println(curriculumid);
            req.getRequestDispatcher("/view/comboo/comboList.jsp").forward(req, resp);
            return;
        }
       

        String groupid = req.getParameter("gr_id");
        String curriculumid = req.getParameter("cu_id");
        CurriculumDAO cu = new CurriculumDAO();
        Curriculum dataCu = cu.getCurriculumByCurriculumID(curriculumid);
        req.setAttribute("dataCu", dataCu);
        ArrayList<Group> datae = GroupDAO.getListCombo(curriculumid);
        req.setAttribute("dataelec", datae);
        req.setAttribute("groupid", groupid);
        Group dataea = GroupDAO.getComboByCurriculumID(curriculumid, groupid);
        req.setAttribute("elec", dataea);
        System.out.println(curriculumid);
        req.getRequestDispatcher("/view/comboo/comboList.jsp").forward(req, resp);

    }

}
