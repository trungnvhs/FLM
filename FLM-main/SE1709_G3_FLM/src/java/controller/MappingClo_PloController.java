/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CLODAO;
import dao.Clo_PloDAO;
import dao.PLO_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.CLO;
import model.PLO;

/**
 *
 * @author ThinkPad P50
 */
public class MappingClo_PloController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Clo_PloDAO plocloDAO = new Clo_PloDAO();
        String sysID = req.getParameter("sysID");
        Map<String, Boolean> mappingStatus = new HashMap<>();
        Map<String, Boolean> mappingStatusUpdate = new HashMap<>();

        ArrayList<CLO> ListCLO = CLODAO.getListCloByCode(sysID);
        ArrayList<PLO> ListPLO = PLO_DAO.getListPloByCode1("7");

        String para = "";
        boolean check = false;

        if (req.getParameter("save") != null) {
            for (CLO clo : ListCLO) {
                for (PLO plo : ListPLO) {
                    para = "mapping" + clo.getClo_id() + "_" + plo.getPlo_id() + "";
                    check = plocloDAO.check(clo.getClo_id(), plo.getPlo_id());
                    mappingStatus.put(para, check);
                    if (req.getParameter(para) != null && check == false) {
                        plocloDAO.addMappingPLO_CLO(clo.getClo_id(), plo.getPlo_id());
                    }
                    if (req.getParameter(para) == null && check == true) {
                        plocloDAO.deleteMappingCLO_PLO(clo.getClo_id(), plo.getPlo_id());
                    }
                }
            }
        }
        for (CLO clo : ListCLO) {
            for (PLO plo : ListPLO) {
                para = "mapping" + clo.getClo_id() + "_" + plo.getPlo_id() + "";
                check = plocloDAO.check(clo.getClo_id(), plo.getPlo_id());
                mappingStatusUpdate.put(para, check);
            }
        }

        req.setAttribute("para", para);
        req.setAttribute("ListClo", ListCLO);
        req.setAttribute("ListPlo", ListPLO);
        req.setAttribute("mappingStatus", mappingStatusUpdate);
        req.setAttribute("mess", "Update successfull!");
        req.getRequestDispatcher("mappingClo_Plo.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Clo_PloDAO plocloDAO = new Clo_PloDAO();
        String sysID = req.getParameter("sysID");
        Map<String, Boolean> mappingStatus = new HashMap<>();

        ArrayList<CLO> ListCLO = CLODAO.getListCloByCode(sysID);
        ArrayList<PLO> ListPLO = PLO_DAO.getListPloByCode1("7");

        String para = "";
        boolean check = false;
        for (CLO clo : ListCLO) {
            for (PLO plo : ListPLO) {
                para = "mapping" + clo.getClo_id() + "_" + plo.getPlo_id() + "";
                check = plocloDAO.check(clo.getClo_id(), plo.getPlo_id());
                mappingStatus.put(para, check);
            }
        }

        req.setAttribute("para", para);
        req.setAttribute("ListClo", ListCLO);
        req.setAttribute("ListPlo", ListPLO);
        req.setAttribute("mappingStatus", mappingStatus);
        req.getRequestDispatcher("mappingClo_Plo.jsp").forward(req, resp);
    }

}
