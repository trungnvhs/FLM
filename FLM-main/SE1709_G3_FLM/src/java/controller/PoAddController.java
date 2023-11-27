/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.PO;
import dao.PODAO;
import java.util.ArrayList;

/**
 *
 * @author ThinkPad P50
 */
public class PoAddController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("mod") != null && req.getParameter("mod").equals("update")) {
            boolean check = false;
            String poid = req.getParameter("poid");
            String poname = req.getParameter("name");
            String ponamec = req.getParameter("namec");
            String podescription = req.getParameter("description");
            String cu_id = req.getParameter("cu_id");
            String isActive = req.getParameter("isActive");
            String cucode = req.getParameter("cucode");

            isActive = isActive == null ? "0" : "1";
            PO po = new PO(poid, poname, podescription, cu_id, isActive, cucode);

            if (!poname.equals(ponamec) && PODAO.checkPoName(poname, cu_id)) {
                check = true;
                req.setAttribute("error1", "PO Name has existed!");
            }
            if (poname.equals("")) {
                check = true;
                req.setAttribute("error2", "Name cannot empty!");
            }
            if (check) {
                req.setAttribute("po", po);
                req.getRequestDispatcher("/view/po/poDetail.jsp").forward(req, resp);
                return;
            } else {
                if (podescription.equals("")) {
                    podescription = null;
                }
                PO p = new PO(poid, poname, podescription, cu_id, isActive, cucode);
                PODAO.updatePo(p);

                int count = PODAO.getTotalPo(cu_id);
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                String page = "1";
                ArrayList<PO> dataPo = PODAO.getPoListPaging(page, cu_id);
                for (PO po1 : dataPo) {
                    po1.toString();
                }
                req.setAttribute("dataPo", dataPo);
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
                req.setAttribute("mess", "Update PO ID " + poid + " successfull!");
                req.getRequestDispatcher("/view/po/poList.jsp").forward(req, resp);
                return;
            }
        }
        
        boolean check = false;
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String isactive = req.getParameter("isactive");
        String cu_id = req.getParameter("cu_id");
        if (PODAO.checkPoName(name, cu_id)) {
            check = true;
            req.setAttribute("error1", "PO Name has existed!");
        }
        if (name.equals("")) {
            check = true;
            req.setAttribute("error2", "Name cannot empty!");
        }
        if (check) {
            req.setAttribute("name", req.getParameter("name"));
            req.setAttribute("description", req.getParameter("description"));
            req.setAttribute("cu_id", req.getParameter("cu_id"));
            req.getRequestDispatcher("/view/po/newPo.jsp").forward(req, resp);
            return;
        } else {
            if (description.equals("")) {
                description = null;
            }
            PO p = new PO(name, description, cu_id, isactive);
            PODAO.addPo(p);

            int count = PODAO.getTotalPo(cu_id);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = String.valueOf(endPage);
            ArrayList<PO> dataPo = PODAO.getPoListPaging(page, cu_id);
            for (PO po : dataPo) {
                po.toString();
            }
            req.setAttribute("dataPo", dataPo);
            req.setAttribute("page", endPage);
            req.setAttribute("mess", "Add new PO successfull!");
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/po/poList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("cu_id") != null) {
            String cu_id = req.getParameter("cu_id");
          req.setAttribute("cu_id", cu_id);
            req.getRequestDispatcher("/view/po/newPo.jsp").forward(req, resp);
        }
    }

}
