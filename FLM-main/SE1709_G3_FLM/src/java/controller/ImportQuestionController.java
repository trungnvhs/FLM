/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.QuestionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import model.Question;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Asus
 */
@MultipartConfig()

public class ImportQuestionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String sys_id = (String) session.getAttribute("sysID");
        Part file = req.getPart("file");
        try {
            InputStream inp = file.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            Sheet sheet = wb.getSheetAt(0);
            Question question;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String session_id = String.valueOf(row.getCell(0).getNumericCellValue());
                String name = row.getCell(1).getStringCellValue();
                String details = row.getCell(2).getStringCellValue();
                System.out.println(name);
                System.out.println(session_id);
                if (session_id.equals("0")) {
                    session_id = null;
                }
                boolean check = false;
                String mess = "";
                if (session_id != null && !QuestionDAO.checkSession_id(session_id)) {
                    check = true;
                    mess += "Session_id in row " + i + " is empty!";
                }
                if (QuestionDAO.checkQuestion_name(name)) {
                    System.out.println(name);
                    check = true;
                    mess += "Question name in row " + i + " has existed!<br>";
                }
                if (details.trim().equals("")) {
                    check = true;
                    mess += "Details in row " + i + " is empty!";
                }
                if (check) {
                    req.setAttribute("mess", mess);
                    int count = QuestionDAO.getTotalQuestion(sys_id);
                    int endPage = count / 10;
                    if (count % 10 != 0) {
                        endPage++;
                    }
                    String page = String.valueOf(endPage);
                    ArrayList<Question> dataQue = QuestionDAO.getQuestionListPaging("1", sys_id);
                    req.setAttribute("dataQue", dataQue);
                    req.setAttribute("page", 1);
                    req.setAttribute("endPage", endPage);
                    req.getRequestDispatcher("/view/question/listQuestion.jsp").forward(req, resp);
                    return;
                }
                question = new Question(session_id, name, details);
                QuestionDAO.addQuestion(question);
            }
        } catch (FileNotFoundException fnf) {
            req.setAttribute("mess", "File Not Found!");
            int count = QuestionDAO.getTotalQuestion(sys_id);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = String.valueOf(endPage);
            ArrayList<Question> dataQue = QuestionDAO.getQuestionListPaging("1", sys_id);
            req.setAttribute("dataQue", dataQue);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/question/listQuestion.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            System.out.println("aaaaaa" + e.getMessage());
            req.setAttribute("mess", "Import Failed!");
            int count = QuestionDAO.getTotalQuestion(sys_id);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = String.valueOf(endPage);
            ArrayList<Question> dataQue = QuestionDAO.getQuestionListPaging("1", sys_id);
            req.setAttribute("dataQue", dataQue);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/question/listQuestion.jsp").forward(req, resp);
            return;
        }

        int count = QuestionDAO.getTotalQuestion(sys_id);
        int endPage = count / 10;
        if (count % 10 != 0) {
            endPage++;
        }
        String page = String.valueOf(endPage);
        ArrayList<Question> dataQue = QuestionDAO.getQuestionListPaging(page, sys_id);
        req.setAttribute("dataQue", dataQue);
        req.setAttribute("page", endPage);
        req.setAttribute("endPage", endPage);
        req.setAttribute("mess", "Import Questions successfull!");
        req.getRequestDispatcher("/view/question/listQuestion.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = getServletContext().getRealPath("/assets/importFile/Template_Import_Questions.xls");

        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);

        // Lấy kiểu MIME của file
        String mimeType = getServletContext().getMimeType(filePath);
        if (mimeType == null) {
            // Mặc định nếu không tìm thấy kiểu MIME
            mimeType = "application/octet-stream";
        }

        // Thiết lập thông tin phản hồi
        resp.setContentType(mimeType);
        resp.setContentLength((int) downloadFile.length());

        // Thiết lập thông tin header
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        resp.setHeader(headerKey, headerValue);

        // Lấy luồng đầu ra của response
        OutputStream outStream = resp.getOutputStream();

        // Tạo bộ đệm byte để truyền dữ liệu
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        // Đọc file từ đĩa và ghi vào response
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }

}
