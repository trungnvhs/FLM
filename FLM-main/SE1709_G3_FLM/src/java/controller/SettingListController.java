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
import java.util.ArrayList;
import model.Setting;

/**
 *
 * @author MSI
 */
public class SettingListController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String name = req.getParameter("name");
        SettingDAO s = new SettingDAO();
        ArrayList<String> dataType = s.getAllType();
        req.setAttribute("dataType", dataType);
        //Xử lý search theo name
        if (req.getParameter("btn_search") != null) {

            // search có thể nhập ko hết như 'Stu' khi mà nó có nhiều thì sẽ cần in ra hết
            // với cả cái data bên jsp ô trả về nó là kiểu ArrayList<Setting> nên chỗ này ô set nó thành setting thôi là ko đc
            ArrayList<Setting> data = s.getSettingBySettingName(name);
            
            req.setAttribute("data", data);
            req.getRequestDispatcher("/view/setting/settinglist.jsp").forward(req, resp);
            return;
        }
        // Xử lý search theo type
        String type = req.getParameter("settingType");
        ArrayList<Setting> data = s.getSettingByType(type);
        req.setAttribute("type", req.getParameter("settingType"));
        req.setAttribute("data", data);
        req.getRequestDispatcher("/view/setting/settinglist.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SettingDAO st = new SettingDAO();
        ArrayList<Setting> data = st.getListSetting();
        ArrayList<String> dataType = st.getAllType();
        req.setAttribute("dataType", dataType);
        req.setAttribute("data", data);
        req.getRequestDispatcher("/view/setting/settinglist.jsp").forward(req, resp);
    }

}
