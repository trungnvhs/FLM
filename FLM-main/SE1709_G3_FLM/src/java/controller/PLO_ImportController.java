/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PLO_DAO;
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
import model.PLO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Trung Quan
 */
@MultipartConfig
public class PLO_ImportController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String curID = (String) session.getAttribute("curID");
        if (req.getParameter("btn-save") != null) {
            ArrayList<PLO> list = (ArrayList<PLO>) session.getAttribute("listSub");
            for (PLO s : list) {
                PLO_DAO.addPLO1(s);
            }
            int count = PLO_DAO.getTotalPLO(curID);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            session.setAttribute("listSub", null);
            req.setAttribute("messErr", null);
            req.setAttribute("messSuc", null);
            req.setAttribute("mess", null);
            ArrayList<PLO> dataPLO = PLO_DAO.getPLOListPaging("1", curID);
            req.setAttribute("dataPLO", dataPLO);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/plo/plo_List.jsp").forward(req, resp);
            return;
        }

        if (req.getParameter("btn-cancel") != null) {
            session.setAttribute("listSub", null);
            req.setAttribute("messErr", null);
            req.setAttribute("messSuc", null);
            req.setAttribute("mess", null);
            int count = PLO_DAO.getTotalPLO(curID);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<PLO> dataPLO = PLO_DAO.getPLOListPaging("1", curID);
            req.setAttribute("dataPLO", dataPLO);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/plo/plo_List.jsp").forward(req, resp);
            return;
        }

        try {
            Part file = req.getPart("file");
            InputStream inp = file.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

            Sheet sheet = wb.getSheetAt(0);
            PLO plo;
            String messErr = "";
            String messSuc = "";
            ArrayList<PLO> ploList = new ArrayList<PLO>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String name = row.getCell(1).getStringCellValue();
                String description = row.getCell(2).getStringCellValue();

                
                boolean check = false;

                if (PLO_DAO.checkPLO_name(name, curID)) {
                    check = true;
                    messErr += "PLO name in row " + i + " has existed!<br>";
                }
                if (description.trim().equals("")) {
                    check = true;
                    messErr += "Description in row " + i + " is empty!<br>";
                }
                if (!check) {
                   messSuc += "PLO in row " + i + " is valid!<br>";
                } 
            }
            session.setAttribute("ploList", ploList);
            req.setAttribute("messErr", messErr);
            req.setAttribute("messSuc", messSuc);
            int count = PLO_DAO.getTotalPLO(curID);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<PLO> dataPLO = PLO_DAO.getPLOListPaging("1", curID);
            req.setAttribute("dataPLO", dataPLO);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/plo/plo_List.jsp").forward(req, resp);
            return;
        } catch (FileNotFoundException fnf) {
            req.setAttribute("mess", "File Not Found!");
            int count = PLO_DAO.getTotalPLO(curID);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<PLO> dataPLO = PLO_DAO.getPLOListPaging("1", curID);
            req.setAttribute("dataPLO", dataPLO);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/plo/plo_List.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            req.setAttribute("mess", "Import Failed!");
            int count = PLO_DAO.getTotalPLO(curID);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<PLO> dataPLO = PLO_DAO.getPLOListPaging("1", curID);
            req.setAttribute("dataPLO", dataPLO);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/plo/plo_List.jsp").forward(req, resp);
            return;
        }
//
//        int count = PLO_DAO.getTotalPLO(curID);
//        int endPage = count / 10;
//        if (count % 10 != 0) {
//            endPage++;
//        }
//        String page = String.valueOf(endPage);
//        ArrayList<PLO> dataPLO = PLO_DAO.getPLOListPaging(page, curID);
//        req.setAttribute("dataPLO", dataPLO);
//        req.setAttribute("page", endPage);
//        req.setAttribute("endPage", endPage);
//        req.setAttribute("mess", "Import PLO successfull!");
//        req.getRequestDispatcher("/view/plo/plo_List.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = getServletContext().getRealPath("/assets/importFile/Template_import_PLOs.xls");

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
