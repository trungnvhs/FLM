package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.RoleDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import util.Constant;

/**
 *
 * @author Asus
 */
public class LoginGoogleController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        User user = getUserInfo(accessToken);

        String email = user.getEmail();
        User u = new User(email);
        boolean check = UserDAO.checkEmail(email);
        if (check) {
            //Get tất cả các thuộc tính của Account
            u = UserDAO.getInfoByEmail(email);
            System.out.println(u);
            //get user id de lay role id
            String userid = u.getUser_id();
            System.out.println(userid);
            //get role id
            String roleid = RoleDAO.getRoleID(userid);
            System.out.println(roleid);
            //get role name
            String rolename = RoleDAO.getRoleName(roleid);
            //Lưu thông tin vào session
            HttpSession session = request.getSession();
            session.setAttribute("account", u);

            request.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(request, response);
        } else {
            //khi chưa có email thì mặc định chạy vào home page guest
            //add email and avatar
            
            UserDAO.addEmail(email);
           
            //get info email
            u = UserDAO.getInfoByEmail(email);
            //get userid
            String userid = u.getUser_id();
            //add role cho tk google moi
            RoleDAO.addRole(userid);
            //add avatar cho tk google moi
            UserDAO.addDefaultAvatar(userid);
            
            
             HttpSession session = request.getSession();
            session.setAttribute("account", u);
            session.setAttribute("isLoggedIn",true);
            
            request.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(request, response);
        }

    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constant.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constant.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constant.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constant.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static User getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        User googlePojo = new Gson().fromJson(response, User.class);

        return googlePojo;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
