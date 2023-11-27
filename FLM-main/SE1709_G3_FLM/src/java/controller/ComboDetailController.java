/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumSubjectDAO;
import dao.GroupDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.CurriculumSubject;
import model.Group;

/**
 *
 * @author quan
 */
public class ComboDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //chuyển sang trang add detail elective
        if (req.getParameter("add1") != null) {
            String name = req.getParameter("elective_name");
            HttpSession session = req.getSession();
            String curriculumid = (String) session.getAttribute("curID");
            ArrayList<CurriculumSubject> dataCur = CurriculumSubjectDAO.getListCurriculumSubject2(curriculumid);
            req.setAttribute("dataCur", dataCur);
            req.setAttribute("name", name);
            req.getRequestDispatcher("/view/comboo/newComboDetail.jsp").forward(req, resp);

        }
        // add detail elective controller
        if (req.getParameter("btn_oke1") != null) {
            HttpSession session = req.getSession();
            String curriculumid = (String) session.getAttribute("curID");

            String subject_id = req.getParameter("subjectid");
            System.out.println(subject_id);
            String name = req.getParameter("name");
            System.out.println(name);
            //get group_id by elective name
            Group datagr = GroupDAO.getGroupByName(name);
            String group_id = datagr.getGroup_id();
            //update
            CurriculumSubjectDAO.UpdateTypeID(curriculumid, subject_id, group_id);

            ArrayList<Group> dataes = GroupDAO.getListComboSubject(curriculumid, group_id);
            Group datae = GroupDAO.getComboByCurriculumID(curriculumid, group_id);
            req.setAttribute("elec", datae);
            req.setAttribute("dataelec", dataes);
            req.setAttribute("groupid", group_id);

            req.setAttribute("mess", "add new elective subject successfull!");

            req.getRequestDispatcher("/view/comboo/comboDetail.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //xóa elective subject detail
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("delete")) {

            String subject_id = req.getParameter("subject_id");
            HttpSession session = req.getSession();
            String curriculumid = (String) session.getAttribute("curID");
            String groupid = req.getParameter("gr_id");

            System.out.println(curriculumid + " " + subject_id + " " + groupid + 1);
            GroupDAO.deleteElectiveDetail(curriculumid, subject_id);

            ArrayList<Group> dataes = GroupDAO.getListComboSubject(curriculumid, groupid);
            Group datae = GroupDAO.getComboByCurriculumID(curriculumid, groupid);
            req.setAttribute("elec", datae);
            req.setAttribute("dataelec", dataes);
            req.setAttribute("groupid", groupid);

            req.setAttribute("mess", "delete succesfull!");
            req.getRequestDispatcher("/view/comboo/comboDetail.jsp").forward(req, resp);
            return;
        }

        String curriculumid = req.getParameter("cu_id");
        String groupid = req.getParameter("gr_id");
        System.out.println(groupid);
        ArrayList<Group> dataes = GroupDAO.getListComboSubject(curriculumid, groupid);
        Group datae = GroupDAO.getComboByCurriculumID(curriculumid, groupid);
        req.setAttribute("elec", datae);
        req.setAttribute("dataelec", dataes);
        req.setAttribute("groupid", groupid);
        req.getRequestDispatcher("/view/comboo/comboDetail.jsp").forward(req, resp);

    }
}
