/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PODAO;
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
import model.PO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author ThinkPad P50
 */
@MultipartConfig()
public class PoImportController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Part file = req.getPart("file");
        String cu_id = (String) session.getAttribute("curID");
        try {
            InputStream inp = file.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

            Sheet sheet = wb.getSheetAt(0);
            PO po;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String name = row.getCell(0).getStringCellValue();
                String description = row.getCell(1).getStringCellValue();
                String isactive = "0";

                boolean check = false;
                String mess = "";
                if (PODAO.checkPoName(name, cu_id)) {
                    check = true;
                    mess += "PO Name in row " + i + " has existed!<br>";
                }
                if (check) {
                    req.setAttribute("mess", "File Not Found!");
                    int count = PODAO.getTotalPo(cu_id);
                    int endPage = count / 10;
                    if (count % 10 != 0) {
                        endPage++;
                    }
                    String page = String.valueOf(endPage);
                    ArrayList<PO> dataPo = PODAO.getPoListPaging(page, cu_id);
                    req.setAttribute("dataPo", dataPo);
                    req.setAttribute("page", 1);
                    req.setAttribute("endPage", endPage);
                    req.getRequestDispatcher("/view/po/poList.jsp").forward(req, resp);
                    return;
                }
                if (description.equals("")) {
                    description = null;
                }
                PO p = new PO(name, description, cu_id, isactive);
                PODAO.addPo(p);
            }
        } catch (FileNotFoundException fnf) {
            req.setAttribute("mess", "File Not Found!");
            int count = PODAO.getTotalPo(cu_id);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = String.valueOf(endPage);
            ArrayList<PO> dataPo = PODAO.getPoListPaging(page, cu_id);
            req.setAttribute("dataPo", dataPo);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/po/poList.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            req.setAttribute("mess", "File Not Found!");
            int count = PODAO.getTotalPo(cu_id);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            String page = String.valueOf(endPage);
            ArrayList<PO> dataPo = PODAO.getPoListPaging(page, cu_id);
            req.setAttribute("dataPo", dataPo);
            req.setAttribute("page", 1);
            req.setAttribute("endPage", endPage);
            req.getRequestDispatcher("/view/po/poList.jsp").forward(req, resp);
            return;
        }

        int count = PODAO.getTotalPo(cu_id);
        int endPage = count / 10;
        if (count % 10 != 0) {
            endPage++;
        }
        String page = String.valueOf(endPage);
        ArrayList<PO> dataPo = PODAO.getPoListPaging(page, cu_id);
        req.setAttribute("dataPo", dataPo);
        req.setAttribute("page", 1);
        req.setAttribute("endPage", endPage);
        req.setAttribute("mess", "Import PO successfull!");
        req.getRequestDispatcher("/view/po/poList.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String filePath = getServletContext().getRealPath("/assets/importFile/Template_import_POs.xls");

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
