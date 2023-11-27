<%-- 
    Document   : SubHeader
    Created on : Jun 7, 2023, 3:06:53 PM
    Author     : Trung Quan
--%>
<%@page import="dao.CurriculumDAO" %>
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
        <div class="sticky-header navbar-expand-lg"  style="top: 50px">
            <div class="menu-bar2 clearfix">
                <div style="border-bottom: #fff 1px dashed; margin-bottom: 5px; width: 100%">
                    <div class="container2 clearfix">
                        <!-- Header Logo ==== -->
                        <div class="menu-logo" style="height: 60px; text-align: center">
                            <label style="color: #fff; font-size: 16px; padding-top: 5px; margin: 0">Curriculum Details</label>
                            <label style="color: #fff; font-size: 20px;margin: 0">${CurriculumDAO.getCurriculumByCurriculumID(curID).getCode()}</label>
                        </div>

                        <!-- Navigation Menu ==== -->
                        <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown"  style="height: 60px">
                            <ul class="nav navbar-nav">	
                                <li><a href="/SE1709_G3_FLM/view/curriculum/curriculumController?id=${curID}&mod=2">Overview</a></li>
                                <li><a href="../po/polist?&&cu_id=${curID}">POs</a></li>
                                <li><a href="/SE1709_G3_FLM/view/plo/PLO_Controller?mod=1&&page=1&&cu_id=${curID}">PLOs</a></li>
                                <li><a href="../po/poplo?cu_id=${curID}">PLO-POs</a></li>
                                <li><a href="/SE1709_G3_FLM/view/curriculumSubject/curriculumSubjectController?mod=1&&page=1">Subjects</a></li>
                                <li><a href="/SE1709_G3_FLM/view/subject/subPloMapController">Subject-PLOs</a></li>
                                <li><a href="/SE1709_G3_FLM/view/comboo/ComboController?cu_id=${curID}">Combos</a></li>
                                <li><a href="/SE1709_G3_FLM/view/elective/ElectiveController?cu_id=${curID}">Electives</a></li>
                            </ul>

                        </div>
                        <!-- Navigation Menu END ==== -->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
