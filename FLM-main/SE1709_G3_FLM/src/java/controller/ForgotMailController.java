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
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.User;

/**
 *
 * @author Asus
 */
public class ForgotMailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        boolean check = UserDAO.checkEmail(email);
        if (check) {
            if (req.getParameter("op").equals("Send Reset Code")) {
                String username = "trungtomdragon01@gmail.com";
                String password = "ttvhdleygthikxix";
                //Thong so ket noi Smtp Sever
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                //Ket noi Smtp Server
                //Dang nhap duoc email
                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(username, password);
                    }
                }
                );

                String emailSubject = "FPT Education Learning Method";
                String emailContent = generateRandomString(6);
                System.out.println(emailContent);

                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(
                            Message.RecipientType.TO,
                            InternetAddress.parse(email)
                    );
                    message.setSubject(emailSubject);
                    message.setText(emailContent);
                    Transport.send(message);
                    System.out.println("Done");
                } catch (MessagingException e) {
                    System.out.println("ko gui dc mail");
                }
                req.setAttribute("email", email);
                req.setAttribute("emailContent", emailContent);
                req.getRequestDispatcher("/view/userAccess/forgotPassword.jsp").forward(req, resp);
            }

            if (req.getParameter("op").equals("Submit")) {
                String noncode = req.getParameter("noncode");
                String code = req.getParameter("code");
                System.out.println(code);
                System.out.println(noncode);
                String newPass = req.getParameter("newPass");
                String reNewPass = req.getParameter("reNewPass");
                if (noncode.equals(code)) {
                    User u = new User();
                    //Get tất cả các thuộc tính của Account
                    u = UserDAO.getInfoByEmail(email);
                    //get user id de lay role id
                    String userid = u.getUser_id();
                    if (newPass.equals(reNewPass)) {
                        UserDAO.updatePassword(newPass, userid);
                        req.setAttribute("error", "<script>alert('Reset Password is successfully!');</script>");
                        req.getRequestDispatcher("/view/userAccess/login.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("email", email);
                        req.setAttribute("emailContent", noncode);
                        req.setAttribute("error1", "Newpass is not the same as renewpass*");
                        req.getRequestDispatcher("/view/userAccess/forgotPassword.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("email", email);
                    req.setAttribute("emailContent", noncode);
                    req.setAttribute("error2", " Reset Code is wrong*");
                    req.getRequestDispatcher("/view/userAccess/forgotPassword.jsp").forward(req, resp);
                }
            }
        } else {
            req.setAttribute("error3", "Email does not exist*");
            req.getRequestDispatcher("/view/userAccess/forgotPassword.jsp").forward(req, resp);

        }
    }

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/userAccess/forgotPassword.jsp").forward(req, resp);
    }

}
