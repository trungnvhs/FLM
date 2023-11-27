<%-- 
    Document   : settinglist
    Created on : 26-May-2023, 19:16:44
    Author     : MSI
--%>
<%@page import="dao.UserDAO" %>

<%@page import="dao.CurriculumDAO" %>
<%@page import="dao.CurriculumSubjectDAO" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">


        <title>Setting List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            body{
                background: #edf1f5;
                margin-top:20px;
            }
            .card {
                position: relative;
                display: flex;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 0 solid transparent;
                border-radius: 0;
            }
            .btn-circle.btn-lg, .btn-group-lg>.btn-circle.btn {
                width: 50px;
                height: 50px;
                padding: 14px 15px;
                font-size: 18px;
                line-height: 23px;
            }
            .text-muted {
                color: #8898aa!important;
            }
            [type=button]:not(:disabled), [type=reset]:not(:disabled), [type=submit]:not(:disabled), button:not(:disabled) {
                cursor: pointer;
            }
            .btn-circle {
                border-radius: 100%;
                width: 40px;
                height: 40px;
                padding: 10px;
            }
            .user-table tbody tr .category-select {
                max-width: 150px;
                border-radius: 20px;
            }
            svg {
                width: 40px;
                height: 40px;
                margin-left: 900px;
            }
            .border-0{
                background-color: #330066;
                color: #ffffff;
            }
            pre {
                white-space: pre-wrap;
                word-wrap: break-word;
                font-family: Rubik!important;
                font-size: 16px!important;
            }
            /* Áp dụng màu nền xen kẽ cho các hàng chẵn và lẻ */
            tr:nth-child(odd) {
                background-color: #f2f2f2; /* Màu nền xám cho hàng lẻ */
            }

            tr:nth-child(even) {
                background-color: #ffffff; /* Màu nền trắng cho hàng chẵn */
            }
        </style>
    </head>
    <body>
        ${error} 
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
        <div class="page-wraper">
            <!-- Header Top ==== -->
            <%@include file="../common/Header.jsp" %>
            <%@include file="../common/SubHeaderCurriculum.jsp" %>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!--Breadcrumb row--> 
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp">Home</a></li>
                            <li><a href="/SE1709_G3_FLM/view/curriculum/curriculumController?mod=1&&page=1">Curriculum List</a></li>
                            <li>Curriculum Subject List</li>
                        </ul>
                    </div>
                </div>
                <div class="content-block">
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="card col-md-12">
                                    <div class="card-body" style="margin-left: 30px">
                                        <h4 class="card-title text-uppercase mb-0">Curriculum Subject List</h4>
                                    </div>
                                    <div class="table-responsive">
                                        <div style="margin-top: 15px;margin-bottom: 15px">

                                            <c:if test="${role=='Head' || 
                                                          role=='Staff'}">
                                                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                                  <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v6m3-3H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
                                                  </svg>
                                                  <a href="addCurriculumSubject.jsp" ><input style="border-radius: 10px" class="btn"  type="submit"   value="New Subject" name="btn_add"></a>

                                            </c:if>   



                                        </div>

                                        <table class="table no-wrap user-table mb-0" style="height: 500px">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 text-uppercase font-medium"></th>
                                                    <th scope="col" class="border-0 text-uppercase font-medium" style="width: 320px">Code</th>
                                                    <th scope="col" class="border-0 text-uppercase font-medium" style="width: 320px">Name</th>
                                                    <th scope="col" class="border-0 text-uppercase font-medium" style="width: 320px">Semester</th>
                                                    <th scope="col" class="border-0 text-uppercase font-medium" style="width: 320px">No Credit</th>
                                                    <th scope="col" class="border-0 text-uppercase font-medium" style="width: 320px">PreRequisite</th>

                                                </tr>
                                            </thead>
                                            <tbody >
                                                <%
                                                int counter = 1; // Khởi tạo biến counter
                                                %>                                   
                                                <c:forEach items="${dataCur}" var="i">

                                                    <tr style="height: 100px ">


                                                        <td class="p-3"><%= counter++ %></td>
                                                        <td class="p-3">${i.getSubject_id()}</td>
                                                        <td class="p-3">${i.getGroup_id()}</td>
                                                        <td class="p-3">${i.getSemester()}</td>
                                                        <td class="p-3">${i.getNo_credit()}</td>
                                                        <td class="p-3">
                                                            
                                                                ${CurriculumSubjectDAO.getPrerequisite(i.getCurriculum_id(),curID)}
                                                            
                                                        </td>
                                                    </tr> 
                                                </c:forEach>


                                            </tbody>


                                        </table>
                                    </div>
                                    <div style="height: 50px"></div>
                                    <div class="pagination-bx rounded-sm gray clearfix row" style="margin-bottom: 20px">
                                        <a class="col-md-2">
                                            <!--<button name="submit" type="submit" value="Submit" class="btn button-md">Add New Subject</button>-->
                                        </a>
                                        <a class="col-md-3">
                                            <!--<button name="submit" type="submit" value="Submit" class="btn-secondry  button-md">Import Subject</button>-->
                                        </a>
                                        <span class="col-md-2"></span>
                                        <ul class="pagination  col-md-4">
                                            <c:if test="${page==1}">
                                                <li class="previous"><a><i class="ti-arrow-left"></i> Prev</a></li>
                                                        </c:if>
                                                        <c:if test="${page!=1}">
                                                <li class="previous"><a href="../curriculumSubject/curriculumSubjectController?mod=1&&page=${page-1}"><i class="ti-arrow-left"></i> Prev</a></li>
                                                </c:if>
                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                    <c:if test="${i==page}">
                                                    <li id="${i}" class="active"><a href="../curriculumSubject/curriculumSubjectController?mod=1&&page=${i}">${i}</a></li>
                                                    </c:if>
                                                    <c:if test="${i!=page}">
                                                    <li id="${i}"><a href="../curriculumSubject/curriculumSubjectController?mod=1&&page=${i}">${i}</a></li>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${page==endPage}">
                                                <li class="previous"><a>Next <i class="ti-arrow-right"></i></li>
                                                </c:if>
                                                <c:if test="${page!=endPage}">
                                                <li class="next"><a href="../curriculumSubject/curriculumSubjectController?mod=1&&page=${page+1}">Next <i class="ti-arrow-right"></i></a></li>
                                                    </c:if>
                                        </ul>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../common/Footer.jsp" %>
        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

        </script>

    </body>
</html>
