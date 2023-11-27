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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Curriculum;
import model.User;

/**
 *
 * @author Asus
 */
public class AddCurriculumController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String totalcredit = req.getParameter("totalcredit");
        String ownerid = req.getParameter("ownerid");
        String creator = req.getParameter("creator");
        Curriculum c = new Curriculum();

        boolean check = CurriculumDAO.checkCurriculumCode(code);
        //nếu code curriculum trùng nhau trả về trang add
        if (check) {
            req.setAttribute("code", code);
            req.setAttribute("name", name);
            req.setAttribute("description", description);
            req.setAttribute("totalcredit", totalcredit);
            req.setAttribute("creator", creator);
            req.setAttribute("error1", "Curriculum has already!");
            req.getRequestDispatcher("/view/curriculum/newCurriculum.jsp").forward(req, resp);
            return;
            //ko trùng thì add curriculum
        } else {
            CurriculumDAO.addCurriculum(code, name, ownerid, description, creator);

            int count = CurriculumDAO.getTotalCurriculum();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = String.valueOf(endPage);
            ArrayList<Curriculum> dataCur = CurriculumDAO.getCurriculumListPaging(page, creator);
            req.setAttribute("dataCu", dataCur);
            req.setAttribute("page", endPage);
            req.setAttribute("endPage", endPage);
            req.setAttribute("mess", "Add curriculum successfull!!");
            req.getRequestDispatcher("/view/curriculum/manageCurricula.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        ArrayList<Curriculum> dataCur = CurriculumDAO.getListCurriculum(u.getUser_id());
        req.setAttribute("dataCu", dataCur);
        req.getRequestDispatcher("/view/curriculum/manageCurricula.jsp").forward(req, resp);
        return;
    }

}
