/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Random;
import model.User;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class ForgotPhoneController extends HttpServlet {

    public static String generateRandomString(int length) {
        // Define the character set from which to select random characters
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // Generate a random index to select a character from the character set
            int randomIndex = random.nextInt(characters.length());

            // Append the randomly selected character to the string
            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        boolean check = UserDAO.checkMobile(phone);

        if (check) {
            String newPass = req.getParameter("newPass");
            User u = UserDAO.getInfoByMobile(phone);
            UserDAO.updatePassword(newPass, u.getUser_id());
            req.setAttribute("mess", "Reset password successfull!!");
            req.getRequestDispatcher("/view/userAccess/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("phone", phone);
            req.setAttribute("code", req.getParameter("code"));
            req.setAttribute("newPass", req.getParameter("newPass"));
            req.setAttribute("reNewPass", req.getParameter("reNewPass"));
            req.setAttribute("error3", "Phone does not exist*");
            req.getRequestDispatcher("/view/userAccess/forgotPasswordPhone.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/userAccess/forgotPasswordPhone.jsp").forward(req, resp);

    }

}
