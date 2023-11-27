/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.QuestionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.User;
import model.Question;

/**
 *
 * @author Asus
 */
public class QuestionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        ArrayList<Question> dataQue = new ArrayList<Question>();
        String sy_id = (String) session.getAttribute("sysID");;

        String textsearch = req.getParameter("textsearch");
        dataQue.clear();
        dataQue = QuestionDAO.getListQuestionSearchNameDetails(textsearch, sy_id);
        req.setAttribute("isSearch", "true");
        req.setAttribute("dataQue", dataQue);
        req.setAttribute("page", 1);
        req.setAttribute("endPage", 1);
        req.getRequestDispatcher("/view/question/listQuestion.jsp").forward(req, resp);
        return;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        String sy_id = (String)session.getAttribute("sysID");

        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("1")) {
            if (session.getAttribute("role") != null && !session.getAttribute("role").equals("Guest")) {
                String page = req.getParameter("page");
                ArrayList<Question> dataSubject = QuestionDAO.getQuestionListPaging(page, sy_id);
                req.setAttribute("dataQue", dataSubject);
                int count = QuestionDAO.getTotalQuestion(sy_id);
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                req.setAttribute("page", page);
                req.setAttribute("endPage", endPage);
                req.getRequestDispatcher("/view/question/listQuestion.jsp").forward(req, resp);
                return;
            } else {
                req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);

            }
        }
    }
}
