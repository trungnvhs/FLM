/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.QuestionDAO;
import dao.SessionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import javax.mail.Session;
import model.Question;
import model.Sessions;
import model.User;

/**
 *
 * @author quan
 */
public class SessionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("account");
        ArrayList<Sessions> dataSe = new ArrayList<Sessions>();
        String sy_id = (String) session.getAttribute("sysID");;

        String textsearch = req.getParameter("textsearch");
        dataSe.clear();
        dataSe = SessionDAO.getListSessionSearchNameDetails(textsearch, sy_id);
        req.setAttribute("isSearch", "true");
        req.setAttribute("dataSe", dataSe);
        req.setAttribute("page", 1);
        req.setAttribute("endPage", 1);
        req.getRequestDispatcher("/view/session/sessionList.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("delete")) {
            String syllabusid = req.getParameter("sy_id");

            String sessionid = req.getParameter("id");
            String page = req.getParameter("page");
            SessionDAO.getDeleteSession(sessionid);
            ArrayList<Sessions> es = SessionDAO.getSessionListPaging(page, syllabusid);

            int count = SessionDAO.getTotalSession(syllabusid);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("dataSe", es);
            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/session/sessionList.jsp").forward(req, resp);

        }
        
        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("edit")) {

            String sessionid = req.getParameter("id");
            SessionDAO se = new SessionDAO();
            Sessions SE = se.getSessionBySessionID(sessionid);
            req.setAttribute("dataSe", SE);
            
            req.getRequestDispatcher("/view/session/sessionDetails.jsp").forward(req, resp);

        }

        if (req.getParameter("mod") != null
                && req.getParameter("mod").equals("1")) {
            String syllabusid = req.getParameter("sy_id");
            String page = req.getParameter("page");

            ArrayList<Sessions> es = SessionDAO.getSessionListPaging(page, syllabusid);

            int count = SessionDAO.getTotalSession(syllabusid);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            req.setAttribute("dataSe", es);
            req.setAttribute("page", page);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/session/sessionList.jsp").forward(req, resp);
        }
    }
}
