/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.PODAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Curriculum;

import model.PO;

/**
 *
 * @author ThinkPad P50
 */
public class PoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        String isactive = req.getParameter("isactive");
        String cu_id = req.getParameter("cu_id");
       

        if (text.equals("")) {
            int count = PODAO.getTotalPo(cu_id);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = req.getParameter("page");
            if (page == null) {
                page = "1";
            }
            ArrayList<PO> dataPo = PODAO.getPoListPaging(page, cu_id);
            for (PO po : dataPo) {
                po.toString();
            }
            req.setAttribute("dataPo", dataPo);
            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
        }
        else if (!isactive.equals("")) {
            
            int count = PODAO.getTotalPoF(cu_id, isactive);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = req.getParameter("page");
            if (page == null) {
                page = "1";
            }
            if (isactive.equals("1")) {
                ArrayList<PO> dataPo = PODAO.getPoListAc(page, cu_id);
                for (PO po : dataPo) {
                    po.toString();
                }
                req.setAttribute("dataPo", dataPo);
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
            }
            if (isactive.equals("0")) {
                ArrayList<PO> dataPo = PODAO.getPoListD(page, cu_id);
                for (PO po : dataPo) {
                    po.toString();
                }
                req.setAttribute("dataPo", dataPo);
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
            }
        } else {
            ArrayList<PO> dataPo = new ArrayList<PO>();
            dataPo = PODAO.getPoSearch(text, cu_id);
            req.setAttribute("search", req.getParameter("text"));
            req.setAttribute("dataPo", dataPo);
        }

        req.getRequestDispatcher("/view/po/poList.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("detail") != null && req.getParameter("detail").equals("1")) {
            String po_id = req.getParameter("id");
            PO po = PODAO.getPoByID(po_id);
            req.setAttribute("po", po);
            req.getRequestDispatcher("/view/po/poDetail.jsp").forward(req, resp);
        } else if (req.getParameter("cu_id") != null) {
            String cu_id = req.getParameter("cu_id");
            int count = PODAO.getTotalPo(cu_id);
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
                cu_id = req.getParameter("cu_id");
                PODAO.unlock(id);
                req.setAttribute("mess", "Active PO ID " + id + " successfull!");
            }
            if (req.getParameter("mod") != null && req.getParameter("mod").equals("3")) {
                String id = req.getParameter("id");
                cu_id = req.getParameter("cu_id");
                PODAO.lock(id);
                req.setAttribute("mess", "DeActive PO ID " + id + " successfull!");
            }
            if (req.getParameter("delete") != null && req.getParameter("delete").equals("1")) {
                String id = req.getParameter("id");
                cu_id = req.getParameter("cu_id");
                PODAO.delete(id);
                req.setAttribute("mess", "Delete PO ID " + id + " successfull!");
            }

            ArrayList<PO> dataPo = PODAO.getPoListPaging(page, cu_id);
            for (PO po : dataPo) {
                po.toString();
            }
            req.setAttribute("dataPo", dataPo);
            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/po/poList.jsp").forward(req, resp);
        }

    }

}
