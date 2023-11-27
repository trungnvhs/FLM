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
public class CloController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        String sysID = req.getParameter("sysID");
        String isactive = req.getParameter("isactive");
        System.out.println(isactive);
        if (text.equals("")) {
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
            for (CLO clo : dataClo) {
                clo.toString();
            }
            req.setAttribute("dataClo", dataClo);
            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
        } else if (!isactive.equals("")) {
            int count = CLODAO.getTotalCloF(sysID, isactive);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = req.getParameter("page");
            if (page == null) {
                page = "1";
            }
            if (isactive.equals("1")) {
                ArrayList<CLO> dataClo = CLODAO.getCloListA(page, sysID);
                for (CLO clo : dataClo) {
                    clo.toString();
                }
                System.out.println(dataClo);
                req.setAttribute("dataPo", dataClo);
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
            }
            if (isactive.equals("0")) {
                ArrayList<CLO> dataClo = CLODAO.getCloListD(page, sysID);
                for (CLO clo : dataClo) {
                    clo.toString();
                }
                System.out.println(dataClo);
                req.setAttribute("dataClo", dataClo);
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
            }
        } else {
            ArrayList<CLO> dataClo = new ArrayList<CLO>();
            dataClo = CLODAO.getCloSearch(text, sysID);
            req.setAttribute("search", req.getParameter("text"));
            req.setAttribute("dataClo", dataClo);
        }

        req.getRequestDispatcher("/view/clo/cloList.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("detail") != null && req.getParameter("detail").equals("1")) {
            String clo_id = req.getParameter("id");
            CLO clo = CLODAO.getCloByID(clo_id);
            req.setAttribute("clo", clo);
            req.getRequestDispatcher("/view/clo/cloDetail.jsp").forward(req, resp);
        } else if (req.getParameter("sysID") != null) {
            String sysID = req.getParameter("sysID");
            int count = CLODAO.getTotalClo(sysID);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = req.getParameter("page");
            if (page == null) {
                page = "1";
            }
            if (req.getParameter("mod") != null && req.getParameter("mod").equals("2")) {
                String id = req.getParameter("id");
                sysID = req.getParameter("sysID");
                CLODAO.unlock(id);
                req.setAttribute("mess", "Active CLO ID " + id + " successfull!");
            }
            if (req.getParameter("mod") != null && req.getParameter("mod").equals("3")) {
                String id = req.getParameter("id");
                sysID = req.getParameter("sysID");
                CLODAO.lock(id);
                req.setAttribute("mess", "DeActive CLO ID " + id + " successfull!");
            }
            ArrayList<CLO> dataClo = CLODAO.getCloListPaging(page, sysID);
            for (CLO clo : dataClo) {
                clo.toString();
            }
            req.setAttribute("dataClo", dataClo);
            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/clo/cloList.jsp").forward(req, resp);
        }
    }

}
