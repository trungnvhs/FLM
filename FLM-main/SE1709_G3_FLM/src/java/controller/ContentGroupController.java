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
import model.Group;
import model.Subject;

/**
 *
 * @author Trung Quan
 */
public class ContentGroupController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String curID = (String) session.getAttribute("curID");
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("add")) {

            String curCode = req.getParameter("curID");
            String name = req.getParameter("name");
            String disOrder = req.getParameter("disOrder");
            Group g = new Group(name, "contentGroup", curID, disOrder, "1");

            if (GroupDAO.checkGroupNameOfCur(name, curID)) {
                req.setAttribute("mess", "Content Group has existed in curriculum " + curCode);
                req.setAttribute("name", name);
                req.setAttribute("dataCu", CurriculumDAO.getCurriculumByCurriculumID(curID));
                req.setAttribute("disOrder", disOrder);
                req.getRequestDispatcher("/view/contentGroups/newContentGroup.jsp").forward(req, resp);
                return;
            }
            GroupDAO.insertGroup(g);

            int count = GroupDAO.getTotalGroup(curID);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = String.valueOf(endPage);
            ArrayList<Group> dataContentGroup = GroupDAO.getGroupListPaging(page, curID, "contentGroup");
            req.setAttribute("dataContentGroup", dataContentGroup);
            req.setAttribute("mess", "Add new content group successfull!");
            req.setAttribute("page", endPage);
            req.setAttribute("dataCu", CurriculumDAO.getCurriculumByCurriculumID(curID));
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/contentGroups/contentGroupsList.jsp").forward(req, resp);
            return;
        }
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("subGroup")) {
            String groupID = req.getParameter("id");

            if (req.getParameter("btn-add-sub") != null) {
                String subID = req.getParameter("subject");
                CurriculumSubjectDAO.addGroup(curID, subID, groupID);
            }
            if (req.getParameter("btn-del-sub") != null) {
                String subDelID = req.getParameter("btn-del-sub");
                CurriculumSubjectDAO.addSubInGroup(curID, subDelID);
            }
            if (req.getParameter("btn-save") != null) {
                String name = req.getParameter("name");
                String disOrder = req.getParameter("disOrder");
                GroupDAO.updateGroup(groupID, name, disOrder);
            }
            Group g = GroupDAO.getGroupByID(groupID);
            ArrayList<Subject> dataSubjectContentGroup = SubjectDAO.getListSubContent(groupID, curID);
            ArrayList<Subject> dataSubNoGroup = SubjectDAO.getListSubContent(curID);
            req.setAttribute("group", g);
            req.setAttribute("dataSubjectContentGroup", dataSubjectContentGroup);
            req.setAttribute("dataSubNoGroup", dataSubNoGroup);
            req.getRequestDispatcher("/view/contentGroups/contentGroupDetail.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String curID = (String) session.getAttribute("curID");
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("paging")) {
            String page = req.getParameter("page");
            ArrayList<Group> dataContentGroup = GroupDAO.getGroupListPaging(page, curID, "contentGroup");
            req.setAttribute("dataContentGroup", dataContentGroup);
            int count = GroupDAO.getTotalGroup(curID);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("dataCu", CurriculumDAO.getCurriculumByCurriculumID(curID));
            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/contentGroups/contentGroupsList.jsp").forward(req, resp);
            return;
        }
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("delete")) {
            String groupID = req.getParameter("id");
            GroupDAO.deleteGroup(groupID);
            ArrayList<Group> dataContentGroup = GroupDAO.getGroupListPaging("1", curID, "contentGroup");
            req.setAttribute("dataContentGroup", dataContentGroup);
            int count = GroupDAO.getTotalGroup(curID);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("dataCu", CurriculumDAO.getCurriculumByCurriculumID(curID));
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/contentGroups/contentGroupsList.jsp").forward(req, resp);
            return;
        }
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("delete")) {
            req.setAttribute("mod", "edit");
        }
        String groupID = req.getParameter("id");
        Group g = GroupDAO.getGroupByID(groupID);
        ArrayList<Subject> dataSubjectContentGroup = SubjectDAO.getListSubContent(groupID, curID);
        ArrayList<Subject> dataSubNoGroup = SubjectDAO.getListSubContent(curID);
        req.setAttribute("group", g);
        req.setAttribute("dataSubjectContentGroup", dataSubjectContentGroup);
        req.setAttribute("dataSubNoGroup", dataSubNoGroup);
        req.getRequestDispatcher("/view/contentGroups/contentGroupDetail.jsp").forward(req, resp);
    }

}
