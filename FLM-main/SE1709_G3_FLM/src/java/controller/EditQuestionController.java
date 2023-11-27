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
import model.Question;
import model.User;

/**
 *
 * @author Asus
 */
public class EditQuestionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String sessionno = req.getParameter("sessionno");
        String questioname = req.getParameter("questioname");
        String details = req.getParameter("details");
        QuestionDAO que = new QuestionDAO();
        que.updateQuestion(id, sessionno, questioname, details);
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        String sy_id = (String) session.getAttribute("sysID");
        ArrayList<Question> dataQue = QuestionDAO.getQuestionListPaging("1", sy_id);
        req.setAttribute("dataQue", dataQue);
        req.setAttribute("page", 1);
        req.setAttribute("endPage", 1);
        req.setAttribute("mess", "Edit question successfull!!");
        req.getRequestDispatcher("/view/question/listQuestion.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String creatorid = req.getParameter("creatorid");
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        if (session.getAttribute("account") != null) {
            if (req.getParameter("mod") != null
                    && req.getParameter("mod").equals("1") && creatorid.equals(u.getUser_id())) {
                String question_id = req.getParameter("id");
                String page = req.getParameter("page");
                String sy_id = req.getParameter("sy_id");
                QuestionDAO qe = new QuestionDAO();
                qe.removeQuestion(question_id);
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
            } else if (req.getParameter("mod") != null
                    && req.getParameter("mod").equals("2")
                    && creatorid.equals(u.getUser_id())) {
                String question_id = req.getParameter("id");
                Question dataQue = QuestionDAO.getQuestionByQuestionID(question_id);
                req.setAttribute("dataQue", dataQue);
                req.getRequestDispatcher("/view/question/editQuestion.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);

            }
        } else {
            req.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(req, resp);

        }
    }
}
