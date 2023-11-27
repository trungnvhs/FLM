/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.GroupDAO;
import dao.PLO_DAO;
import dao.SubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Group;
import model.PLO;
import model.PO;
import model.Subject;

/**
 *
 * @author Trung Quan
 */
public class SubPLO_MapController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String curID = (String) session.getAttribute("curID");

        Map<String, Boolean> mappingStatus = new HashMap<>();
        ArrayList<PLO> dataPLO = PLO_DAO.getPLOList(curID);
        ArrayList<Group> dataContentGroup = GroupDAO.getGroupList(curID, "contentGroup");

        String para = "";
        boolean check = false;
        // duyệt tất cả các phần tử trong list plo
            for (Group group : dataContentGroup) {
                for (Subject sub : SubjectDAO.getListSubContent(group.getGroup_id(), curID)) {
                    for (PLO plo : dataPLO) {
                        para = "mapping" + sub.getSubject_id() + "_" + plo.getPlo_id() + "";
                        check = SubjectDAO.check(curID, sub.getSubject_id(), plo.getPlo_id());
                        if (req.getParameter(para) != null && check == false) {
                            SubjectDAO.addMappingPlo_Sub(curID, sub.getSubject_id(), plo.getPlo_id());
                        }
                        if (req.getParameter(para) == null && check == true) {
                            SubjectDAO.deleteMappingPlo_Sub(curID, sub.getSubject_id(), plo.getPlo_id());
                        }
                    }
                }
            }
        for (Group group : dataContentGroup) {
            for (Subject sub : SubjectDAO.getListSubContent(group.getGroup_id(), curID)) {
                for (PLO plo : dataPLO) {
                    para = "mapping" + sub.getSubject_id() + "_" + plo.getPlo_id() + "";
                    check = SubjectDAO.check(curID, sub.getSubject_id(), plo.getPlo_id());
                    mappingStatus.put(para, check);
                }
            }
        }
        req.setAttribute("dataPLO", dataPLO);
        req.setAttribute("dataContentGroup", dataContentGroup);
        req.setAttribute("mappingStatus", mappingStatus);
        req.getRequestDispatcher("/view/subject/subjectPloMapping.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String curID = (String) session.getAttribute("curID");

        Map<String, Boolean> mappingStatus = new HashMap<>();
        ArrayList<PLO> dataPLO = PLO_DAO.getPLOList(curID);
        ArrayList<Group> dataContentGroup = GroupDAO.getGroupList(curID, "contentGroup");

        String para = "";
        boolean check = false;
        for (Group group : dataContentGroup) {
            for (Subject sub : SubjectDAO.getListSubContent(group.getGroup_id(), curID)) {
                for (PLO plo : dataPLO) {
                    para = "mapping" + sub.getSubject_id() + "_" + plo.getPlo_id() + "";
                    check = SubjectDAO.check(curID, sub.getSubject_id(), plo.getPlo_id());
                    mappingStatus.put(para, check);
                }
            }
        }

        req.setAttribute("dataPLO", dataPLO);
        req.setAttribute("dataContentGroup", dataContentGroup);
        req.setAttribute("mappingStatus", mappingStatus);
        req.getRequestDispatcher("/view/subject/subjectPloMapping.jsp").forward(req, resp);
    }

}
