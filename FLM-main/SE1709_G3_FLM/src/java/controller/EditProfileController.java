/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.twilio.sdk.TwilioRestException;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Random;
import model.User;
import util.SmsUtil;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Trung Quan
 */
@MultipartConfig()
public class EditProfileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        String oleAvatar = user.getAvatar();
//        if (session.getAttribute("account") == null) {
//            request.setAttribute("error", "<script>alert('Login to continue!!!');</script>");
//            request.getRequestDispatcher("/view/userAccess/.jsp").forward(request, response);
//            /**
//             * Thêm tên file
//             */
//        }
        Part avatar = req.getPart("file");
        String realPart = getServletContext().getRealPath("/") + "assets\\images\\avatar";
        String realPartSave = realPart.replace("build\\", "");

        String fileName = req.getParameter("avatar");

        String fullName = req.getParameter("fullName");
        String userName = req.getParameter("userName");
        String mail = req.getParameter("mail");
        String phone = req.getParameter("phone");
        String verifyCode = req.getParameter("verifyCode");
        String checkCode = req.getParameter("checkCode");
        String jobTitle = req.getParameter("jobTitle");
        String company = req.getParameter("company");
        User userNew = new User(fullName, mail, phone, userName, fileName, jobTitle, company);
        // Xử lý update ảnh
        if (req.getParameter("btn-avatar") != null) {
            fileName = getFileName(avatar);
            String filePath = realPart + File.separator + fileName;
            String filePathSave = realPartSave + File.separator + fileName;
            OutputStream out = null;
            InputStream fileContent = null;

            // Add image vào folder có build, khi clear sẽ bị mất file
            try {
                out = new FileOutputStream(new File(filePath));
                fileContent = avatar.getInputStream();

                int read;
                byte[] buffer = new byte[4096];

                while ((read = fileContent.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
            } catch (Exception e) {
                req.setAttribute("err1", "File upload failed!");
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
                if (fileContent != null) {
                    fileContent.close();
                }
            }

            // Add image vào folder ko có build nhưng khi build lại thì mới hiển thị
            try {
                out = new FileOutputStream(new File(filePathSave));
                fileContent = avatar.getInputStream();

                int read;
                byte[] buffer = new byte[4096];

                while ((read = fileContent.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
            } catch (Exception e) {
                req.setAttribute("err1", "File upload failed!");
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
                if (fileContent != null) {
                    fileContent.close();
                }
            }
//            UserDAO.updateAvatar(user.getUser_id(), fileName);
//            user.setAvatar(fileName);
//            userNew.setAvatar(fileName);
//            session.setAttribute("account", user);
//            req.setAttribute("user", userNew);
            req.setAttribute("avatar", fileName);
//            req.getRequestDispatcher("/view/userAccess/profile.jsp").forward(req, resp);
//            return;
        }

        // Update infor
        if (req.getParameter("btn") != null || req.getParameter("btn-oke") != null) {

            // Check nếu click vào send verification code        
            if (req.getParameter("btn") != null) {
                String code = generateRandomString(6);

                // Nếu thêm sđt
                if (user.getMobile() == null && !phone.equals("")) {
                    if (UserDAO.checkMobile(phone)) {
                        req.setAttribute("ErrorPhone", "Phone number has existed");
                        req.setAttribute("user", userNew);
                        req.setAttribute("avatar", fileName);
                        req.getRequestDispatcher("/view/userAccess/profile.jsp").forward(req, resp);
                        return;
                    }
                    try {
                        SmsUtil.sendMessage("+84" + phone.substring(1), code);
                    } catch (TwilioRestException ex) {
                        System.out.println("Send code fail");
                    }

                }
                // End thêm sdt

                // Nếu thêm mail
                if (user.getEmail() == null && !mail.equals("")) {

                    if (UserDAO.checkEmail(mail)) {
                        req.setAttribute("ErrorMail", "Email has existed");
                        req.setAttribute("user", userNew);
                        req.setAttribute("avatar", fileName);
                        req.getRequestDispatcher("/view/userAccess/profile.jsp").forward(req, resp);
                        return;
                    }
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
                    Session ss = Session.getInstance(props, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {

                            return new PasswordAuthentication(username, password);
                        }
                    }
                    );

                    String emailSubject = "FPT Education Learning Method";
                    String emailContent = code;
                    System.out.println(emailContent);

                    try {
                        MimeMessage message = new MimeMessage(ss);
                        message.setFrom(new InternetAddress(username));
                        message.setRecipients(
                                Message.RecipientType.TO,
                                InternetAddress.parse(mail)
                        );
                        message.setSubject(emailSubject);
                        message.setText(emailContent);
                        Transport.send(message);
                        System.out.println("Done");

                    } catch (MessagingException e) {
                        System.out.println("ko gui dc mail");
                    }
                }
                // End thêm mail
                req.setAttribute("checkCode", code);
            }
            // End xử lý send verification code

            // Xử Lý update profile
            if (req.getParameter("btn-oke") != null) {
                if (((user.getEmail() == null && !phone.equals("")) || (user.getMobile() == null && !mail.equals(""))) && !verifyCode.equals(checkCode)) {
                    req.setAttribute("ErrorCode", "Verification code incorrect");
                    req.setAttribute("user", userNew);
                    req.setAttribute("avatar", fileName);
                    req.setAttribute("checkCode", checkCode);
                    req.getRequestDispatcher("/view/userAccess/profile.jsp").forward(req, resp);
                    return;
                }
                UserDAO.UpdateInfor(userNew, user.getUser_id());
                UserDAO.updateAvatar(user.getUser_id(), fileName);
                user.setAvatar(fileName);
                session.setAttribute("account", user);
            }
            // End xử lý update profile
            req.setAttribute("user", userNew);
        }

        if (req.getParameter("btn-cancel") != null) {
            req.setAttribute("user", (User) session.getAttribute("account"));
            req.setAttribute("avatar", oleAvatar);
            req.getRequestDispatcher("/view/userAccess/profile.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("user", userNew);
        req.setAttribute("avatar", fileName);
        req.getRequestDispatcher("/view/userAccess/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        if (session.getAttribute("account") == null) {
//            request.setAttribute("error", "<script>alert('Login to continue!!!');</script>");
//            request.getRequestDispatcher("/view/userAccess/.jsp").forward(request, response);
//            /**
//             * Thêm tên file
//             */
//        }
        User u = (User) session.getAttribute("account");
        req.setAttribute("user", u);
        req.setAttribute("avatar", u.getAvatar());
        req.getRequestDispatcher("/view/userAccess/profile.jsp").forward(req, resp);
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
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
}
