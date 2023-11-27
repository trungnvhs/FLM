/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SubjectDAO;
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
import model.Subject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Trung Quan
 */
@MultipartConfig()
public class SubjectImportController extends HttpServlet {

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        if (req.getParameter("btn-save") != null) {
            ArrayList<Subject> list = (ArrayList<Subject>) session.getAttribute("listSub");
            for (Subject s : list) {
                SubjectDAO.addSubject(s);
            }
            int count = SubjectDAO.getTotalSubject();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            session.setAttribute("listSub", null);
            req.setAttribute("messErr", null);
            req.setAttribute("messSuc", null);
            req.setAttribute("mess", "Import subject successfull!");
            ArrayList<Subject> dataSubject = SubjectDAO.getSubjectListPaging("1");
            req.setAttribute("dataSubject", dataSubject);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/subject/subjectList.jsp").forward(req, resp);
            return;
        }
        
        if (req.getParameter("btn-cancel") != null) {
            session.setAttribute("listSub", null);
            req.setAttribute("messErr", null);
            req.setAttribute("messSuc", null);
            req.setAttribute("mess", null);
            int count = SubjectDAO.getTotalSubject();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<Subject> dataSubject = SubjectDAO.getSubjectListPaging("1");
            req.setAttribute("dataSubject", dataSubject);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/subject/subjectList.jsp").forward(req, resp);
            return;
        }
        
        
        try {
            Part file = req.getPart("file");
            InputStream inp = file.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

            ArrayList<Subject> subList = new ArrayList<Subject>();
            Sheet sheet = wb.getSheetAt(0);
            Subject sbj;
            String messErr = "";
            String messSuc = "";
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String code = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String type = row.getCell(2).getStringCellValue();
                String is_active = String.valueOf((int) row.getCell(3).getNumericCellValue());
                String description = row.getCell(4).getStringCellValue();

                boolean check = false;
                if (SubjectDAO.checkSubjectCode(code)) {
                    check = true;
                    messErr += "Subject code in row " + i + " has existed!<br>";
                } else {
                    messSuc += "Subject in row " + i + " is valid!<br>";
                    sbj = new Subject(1, code, name, type, is_active, description, null, null);
                    subList.add(sbj);
                }
            }
            session.setAttribute("listSub", subList);
            req.setAttribute("messErr", messErr);
            req.setAttribute("messSuc", messSuc);
            int count = SubjectDAO.getTotalSubject();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<Subject> dataSubject = SubjectDAO.getSubjectListPaging("1");
            req.setAttribute("dataSubject", dataSubject);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.setAttribute("file", req.getPart("file"));
            req.getRequestDispatcher("/view/subject/subjectList.jsp").forward(req, resp);
            return;
        } catch (FileNotFoundException fnf) {
            req.setAttribute("mess", "File Not Found!");
            int count = SubjectDAO.getTotalSubject();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = String.valueOf(endPage);
            ArrayList<Subject> dataSubject = SubjectDAO.getSubjectListPaging(page);
            req.setAttribute("dataSubject", dataSubject);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/subject/subjectList.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            System.out.println(e.toString());
            req.setAttribute("mess", "Import Error!");
            int count = SubjectDAO.getTotalSubject();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = String.valueOf(endPage);
            ArrayList<Subject> dataSubject = SubjectDAO.getSubjectListPaging(page);
            req.setAttribute("dataSubject", dataSubject);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/subject/subjectList.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = getServletContext().getRealPath("/assets/importFile/Template_import_Subject.xls");

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
