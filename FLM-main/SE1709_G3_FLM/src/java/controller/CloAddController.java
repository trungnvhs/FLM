/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CLODAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.CLO;

/**
 *
 * @author ThinkPad P50
 */
public class CloAddController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("update")) {
            boolean check = false;
            String cloid = req.getParameter("cloid");
            String cloname = req.getParameter("name");
            String clonamec = req.getParameter("namec");
            String clodescription = req.getParameter("description");
            String sysID = req.getParameter("sysID");
            String isActive = req.getParameter("isActive");
            String cucode = req.getParameter("cucode");

            isActive = isActive == null ? "0" : "1";
            CLO clo = new CLO(cloid, cloname, clodescription, sysID, isActive);

            if (!cloname.equals(clonamec) && CLODAO.checkCloName(cloname, sysID)) {
                check = true;
                req.setAttribute("error1", "CLO Name has existed!");
            }
            if (cloname.equals("")) {
                check = true;
                req.setAttribute("error2", "Name cannot empty!");
            }
            if (check) {
                req.setAttribute("clo", clo);
                req.getRequestDispatcher("/view/clo/cloDetail.jsp").forward(req, resp);
                return;
            } else {
                if (clodescription.equals("")) {
                    clodescription = null;
                }
                CLO c = new CLO(cloid, cloname, clodescription, sysID, isActive);
                CLODAO.updateClo(c);

                int count = CLODAO.getTotalClo(sysID);
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                String page = req.getParameter("page");
                if (page == null) {
                    page = "1";
                }
                ArrayList<CLO> dataClo = CLODAO.getCloListPaging(page, sysID);
                for (CLO clo1 : dataClo) {
                    clo1.toString();
                }
                req.setAttribute("dataClo", dataClo);
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
                req.setAttribute("mess", "Update CLO ID " + cloid + " successfull!");
                req.getRequestDispatcher("/view/clo/cloList.jsp").forward(req, resp);
                return;
            }
        }
         boolean check = false;
        String cloname = req.getParameter("name");
        String clodescription = req.getParameter("description");
        String isActive = req.getParameter("isactive");
        String sysID = req.getParameter("sysID");
        if (CLODAO.checkCloName(cloname, sysID)) {
            check = true;
            req.setAttribute("error1", "CLO Name has existed!");
        }
        if (cloname.equals("")) {
            check = true;
            req.setAttribute("error2", "Name cannot empty!");
        }
        if (check) {
            req.setAttribute("name", req.getParameter("name"));
            req.setAttribute("description", req.getParameter("description"));
            req.setAttribute("sysID", req.getParameter("sysID"));
            req.getRequestDispatcher("/view/clo/newClo.jsp").forward(req, resp);
            return;
        } else {
            if (clodescription.equals("")) {
                clodescription = null;
            }
            CLO clo = new CLO( cloname, clodescription, sysID, isActive);
            CLODAO.addClo(clo);

            int count = CLODAO.getTotalClo(sysID);
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                String page = req.getParameter("page");
                if (page == null) {
                    page = "1";
                }
                ArrayList<CLO> dataClo = CLODAO.getCloListPaging(page, sysID);
                for (CLO clo1 : dataClo) {
                    clo1.toString();
                }
                req.setAttribute("dataClo", dataClo);
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
                req.setAttribute("mess", "Add new CLO Successfull!");
                req.getRequestDispatcher("/view/clo/cloList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("sysID") != null) {
            String sysID = req.getParameter("sysID");
            req.setAttribute("sysID", sysID);
            req.getRequestDispatcher("/view/clo/newClo.jsp").forward(req, resp);
        }
    }

}
