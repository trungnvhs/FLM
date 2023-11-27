/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SettingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Setting;

/**
 *
 * @author MSI
 */
public class SettingDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        SettingDAO st = new SettingDAO();
        String name = req.getParameter("name");
        String value = req.getParameter("value");
        String order = req.getParameter("order");
        String id = req.getParameter("id");
        st.UpdateSetting(name,value, order,id);
        resp.sendRedirect("settinglist");
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        SettingDAO s = new SettingDAO();
        Setting data = s.getSettingBySettingID(id);
        req.setAttribute("data", data);
        req.getRequestDispatcher("/view/setting/settingdetail.jsp").forward(req, resp);
    }

}
