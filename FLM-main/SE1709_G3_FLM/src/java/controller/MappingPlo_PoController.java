/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PLO_DAO;
import dao.PODAO;
import dao.Plo_PoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.PLO;
import model.PO;

/**
 *
 * @author ThinkPad P50
 */
public class MappingPlo_PoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Plo_PoDAO plopoDAO = new Plo_PoDAO();
        String cu_id = req.getParameter("cu_id");
        Map<String, Boolean> mappingStatus = new HashMap<>();
        Map<String, Boolean> mappingStatusUpdate = new HashMap<>();

        ArrayList<PO> ListPo = PODAO.getListPoByCode(cu_id);
        ArrayList<PLO> ListPLO = PLO_DAO.getListPloByCode1(cu_id);

        String para = "";
        boolean check = false;

        if (req.getParameter("save") != null) {
            for (PLO plo : ListPLO) {
                for (PO po : ListPo) {
                    para = "mapping" + plo.getPlo_id() + "_" + po.getPo_id() + "";
                    check = plopoDAO.check(plo.getPlo_id(), po.getPo_id());
                    mappingStatus.put(para, check);
                    if(req.getParameter(para) != null && check == false){
                        plopoDAO.addMappingPLO_PO(plo.getPlo_id(), po.getPo_id());
                    }
                    if(req.getParameter(para) == null && check == true){
                        plopoDAO.deleteMappingPO_PLO(plo.getPlo_id(), po.getPo_id());
                    }
                }
            }
        }
        for (PLO plo : ListPLO) {
            for (PO po : ListPo) {
                para = "mapping" + plo.getPlo_id() + "_" + po.getPo_id() + "";
                check = plopoDAO.check(plo.getPlo_id(), po.getPo_id());
                mappingStatusUpdate.put(para, check);
            }
        }

        req.setAttribute("para", para);
        req.setAttribute("ListPo", ListPo);
        req.setAttribute("ListPlo", ListPLO);
        req.setAttribute("mappingStatus", mappingStatusUpdate);
        req.setAttribute("mess", "Update successfull!");
        req.getRequestDispatcher("mappingplo_po.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Plo_PoDAO plopoDAO = new Plo_PoDAO();
        String cu_id = req.getParameter("cu_id");
        Map<String, Boolean> mappingStatus = new HashMap<>();

        ArrayList<PO> ListPo = PODAO.getListPoByCode(cu_id);
        ArrayList<PLO> ListPLO = PLO_DAO.getListPloByCode1(cu_id);

        String para = "";
        boolean check = false;
        for (PLO plo : ListPLO) {
            for (PO po : ListPo) {
                para = "mapping" + plo.getPlo_id() + "_" + po.getPo_id() + "";
                check = plopoDAO.check(plo.getPlo_id(), po.getPo_id());
                mappingStatus.put(para, check);
            }
        }

        req.setAttribute("para", para);
        req.setAttribute("ListPo", ListPo);
        req.setAttribute("ListPlo", ListPLO);
        req.setAttribute("mappingStatus", mappingStatus);
        req.getRequestDispatcher("mappingplo_po.jsp").forward(req, resp);
    }

}
