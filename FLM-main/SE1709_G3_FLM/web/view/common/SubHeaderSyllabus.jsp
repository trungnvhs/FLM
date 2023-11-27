<%-- 
    Document   : SubHeader
    Created on : Jun 7, 2023, 3:06:53 PM
    Author     : Trung Quan
--%>
<%@page import="dao.SyllabusDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .sub-header-li>a{
                color: #fff;
                padding: 15px 12px;
            }
            .menu-bar2{
                background: #4c1864;
                color: #fff;
                width: 100%;
                position: relative;
                box-shadow: 0 0 13px 0 rgba(0, 0, 0, 0.4);
            }
            .is-fixed .menu-bar2{
                position: fixed;
                left: 0;
                z-index: 4;
                box-shadow: 0 0 13px 0 rgba(0, 0, 0, 0.4);
            }
            .container2{
                width: 100%;
                padding-right: 15px;
                padding-left: 15px;
                margin-right: 30px;
                margin-left: 80px;
            }
            .container2 a{
                color: #fff !important;
            }
            .container2 a:hover{
                color: #ff33ff !important;
            }
        </style>
    </head>
    <body>
        <div class="sticky-header navbar-expand-lg">
            <div class="menu-bar2 clearfix">
                <div style="border-bottom: #fff 1px dashed; margin-bottom: 5px; width: 100%">
                    <div class="container2 clearfix">
                        <!-- Header Logo ==== -->
                        <div class="menu-logo" style="height: 60px; text-align: center">
                            <label style="color: #fff; font-size: 16px; padding-top: 5px; margin: 0">Syllabus Details</label>
                            <label style="color: #fff; font-size: 20px;margin: 0">${SyllabusDAO.getSubjectCodeBySYID(sysID)}</label>

                        </div>
                        <!-- Navigation Menu ==== -->
                        <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown"  style="height: 60px">
                            <div class="menu-logo">
                                <a><b style="color: #004085;font-size: 50px">FLM</b></a>
                            </div>

                            <ul class="nav navbar-nav">	
                                <li style="width: 60px"></li>
                                <li><a href="../syllabus/SyllabusController?id=${sysID}&mod=view"">Overview</a></li>
                                <li><a href="../clo/clolist?&&sysID=${sysID}">CLOs</a></li>
                                <li><a href="../clo/cloplo?sysID=${sysID}"">CLO-PLOs</a></li>
                                <li><a href="#">Materials</a></li>
                                <li><a href="#">Schedule</a></li>
                                <li><a href="/SE1709_G3_FLM/view/question/questionController?sy_id=${sysID}&&page=1&&mod=1">CQs</a></li>
                                <li><a href="/SE1709_G3_FLM/view/session/SessionController?sy_id=${sysID}&&page=1&&mod=1">Session</a></li>
                                <li><a href="#">Assessments</a></li>

                            </ul>
                        </div>
                        <!-- Navigation Menu END ==== -->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
