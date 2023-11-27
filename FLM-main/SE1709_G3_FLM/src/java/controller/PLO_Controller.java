/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PLO_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.PLO;

/**
 *
 * @author MSI
 */
public class PLO_Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("add")) {

            String plo_id = req.getParameter("plo_id");
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String cu_id = req.getParameter("cu_id");
            if (plo_id.isEmpty() || name.isEmpty()
                    || description.isEmpty() || cu_id.isEmpty()) {

                req.getRequestDispatcher("/view/plo/newPLO.jsp").forward(req, resp);
                return;
            } else {
                PLO p1 = new PLO(plo_id, name, description, cu_id);
                PLO_DAO.addPLO(p1);
                int count = PLO_DAO.getTotalPLO(cu_id);
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                String page = String.valueOf(endPage);
                ArrayList<PLO> dataPLO = PLO_DAO.getPLOListPaging(page, cu_id);

                req.setAttribute("dataPLO", dataPLO);
                req.setAttribute("page", page);
                req.setAttribute("mess", "Add new plo successfull!");
                req.setAttribute("endPage", endPage);
                req.getRequestDispatcher("/view/plo/plo_List.jsp").forward(req, resp);

                return;
            }
        }
        if (req.getParameter("update") != null ) {
            String plo_id = req.getParameter("plo_id");
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String cu_id = req.getParameter("cu_id");
            PLO p2 = new PLO(plo_id, name, description, cu_id);
            PLO_DAO.updatePLO(p2);
            int count = PLO_DAO.getTotalPLO(cu_id);
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                String page = String.valueOf(endPage);
                ArrayList<PLO> dataPLO = PLO_DAO.getPLOListPaging(page, cu_id);

                req.setAttribute("dataPLO", dataPLO);
                req.setAttribute("page", page);
                req.setAttribute("mess", "Add new plo successfull!");
                req.setAttribute("endPage", endPage);
                req.getRequestDispatcher("/view/plo/plo_List.jsp").forward(req, resp);

                return;
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("1")) {
            String cu_id = req.getParameter("cu_id");
            String page = req.getParameter("page");
            ArrayList<PLO> dataPLO = PLO_DAO.getPLOListPaging(page, cu_id);
            req.setAttribute("dataPLO", dataPLO);
            int count = PLO_DAO.getTotalPLO(cu_id);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;

            }
            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/plo/plo_List.jsp").forward(req, resp);
            return;
        }

        String plo_id = req.getParameter("plo_id");
        PLO p = PLO_DAO.getPLOByID(plo_id);
        req.setAttribute("plo", p);
        req.getRequestDispatcher("/view/plo/plo_Detail.jsp").forward(req, resp);
    }

}
