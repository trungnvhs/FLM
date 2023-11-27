<%-- Document : subjectList Created on : Jun 6, 2023, 12:18:04 PM Author : Trung Quan --%>
<%@page import="dao.SyllabusDAO" %>
<%@page import="dao.CurriculumDAO" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="../../assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="../../assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
              rel="stylesheet">
        <link href="../../assets/css/styleFormImport.css" rel="stylesheet">
        <link href="../../assets/css/toast.css" rel="stylesheet">
        <style>
            .pricingtable-features tr {
                background-color: #fff;
            }

            .pricingtable-features tr:nth-child(2n) {
                background-color: #cccccc;
            }

            #select-search-type {
                border: #cccccc 1px solid;
                width: 75px;
                margin-right: 10px;
            }

            pre {
                white-space: pre-wrap;
                word-wrap: break-word;
                font-family: Rubik !important;
                font-size: 16px !important;
            }

            .td-left {
                text-align: left;
            }
        </style>
    </head>

    <body>
        <div class="notifications"></div>
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <!-- Header Top ==== -->
            <%@include file="../common/Header.jsp" %>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!--inner page banner-->
                <div class="page-banner ovbl-dark"
                     style="background-image:url(../../assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Subject Predecessors</h1>
                        </div>
                    </div>
                </div>
                <!--Breadcrumb row-->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp">Home</a></li>
                            <li>Subject Predecessors</li>
                        </ul>
                    </div>
                </div>
                <!--Breadcrumb row END-->
                <!--inner page banner END-->
                <!--About Us-->
                <div class="content-block">
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="card col-md-12">
                                    <div class="card-body" style="margin-left: 30px">
                                        <h4 class="card-title text-uppercase mb-0">Subject Predecessors
                                        </h4>
                                    </div>
                                    <div style="margin-top: -50px">
                                        <div class="row" style="margin-bottom: 20px">
                                            <div class="col-lg-8 col-xl-8 col-md-7"></div>
                                            <div class="search-bx style-1 col-lg-4 col-xl-4 col-md-5">
                                                <form action="subjectpre" method="post">
                                                    <input type="text" name="mod" value="search"
                                                           style="display: none" />
                                                    <div class="input-group">
                                                        <div
                                                            style="margin-top: 10px; margin-right: 10px">
                                                            Subject Code:</div>
                                                        <input name="text" class="form-control" placeholder="Enter your keywords..."type="text" value="${search}">
                                                        <span class="input-group-btn">
                                                            <button type="submit" class="fa fa-search" style="background-color: #4c1864"></button>
                                                        </span>
                                                    </div>
                                                    <div style="color: red; margin-left: 120px">${error}</div>
                                                    <div style="color: green; margin-left: 120px">${error1}</div>
                                                </form>
                                            </div>
                                        </div>

                                        <div class="pricingtable-wrapper">
                                            <div class="pricingtable-inner">
                                                <table
                                                    class="table no-wrap user-table mb-0 pricingtable-main">
                                                    <thead class="pricingtable-title">
                                                        <tr class="row">
                                                            <th class="col-md-2">Syllabus ID</th>
                                                            <th class="col-md-2">Subject Code</th>
                                                            <th class="col-md-3">Syllabus Name</th>
                                                            <th class="col-md-2">Decision No.<br>(yyyy/MM/dd)</th>
                                                            <th class="col-md-3">All subjects need to learn before</th>
                                                            <!--<th>Display Order</th>-->
                                                        </tr>
                                                    </thead>

                                                    <tbody class="pricingtable-features">

                                                        <tr class="row">
                                                            <td class="col-md-2">${s.getSyllabus_id()}</td>
                                                            <td class="col-md-2">
                                                                ${s.getSubject().getCode()}
                                                            </td>

                                                            <td class="col-md-3">
                                                                <pre style="width: 100%">${s.getName()}</pre>
                                                            </td>
                                                            <td class="col-md-2"> ${CurriculumDAO.getDecisionNoDate(s.getDecision_id())}</td>
                                                            
                                                            <td class="col-md-3">
                                                                <div style="text-align: left">${s.getSubject().getCode()}
                                                                    <c:forEach var="i" items="${sList}">
                                                                        :${i.getCode()},
                                                                    </c:forEach>
                                                                    <c:forEach var="i" items="${sList}">
                                                                        <c:forEach var="item" items="${SyllabusDAO.getPre(i.getCode())}">
                                                                            ${item.getCode()},
                                                                        </c:forEach>
                                                                    </c:forEach>
                                                                </div> 
                                                                <div style="text-align: left">
                                                                    <c:forEach var="i" items="${sList}">
                                                                        <span> - </span>${i.getCode()}:
                                                                        <c:if test="${SyllabusDAO.getPre(i.getCode()).size() == 0}">None</c:if>
                                                                        <c:if test="${SyllabusDAO.getPre(i.getCode()).size() != 0}">
                                                                            <c:forEach var="item" items="${SyllabusDAO.getPre(i.getCode())}">
                                                                                ${item.getCode()},
                                                                            </c:forEach>
                                                                            <br>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </div>
                                                            </td>
                                                        </tr>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="height: 50px"></div>
                                    <div class="pagination-bx rounded-sm gray clearfix row"
                                         style="margin-bottom: 20px">
                                        <span class="col-md-2"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--contact area END-->
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <%@include file="../common/Footer.jsp" %>
            <!-- Footer END ==== -->

        </form>
        <!-- External JavaScripts -->
        <!--<script src="../../assets/js/jquery.min.js"></script>-->
        <script src="../../assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="../../assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="../../assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script
        src="../../assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="../../assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="../../assets/vendors/counter/waypoints-min.js"></script>
        <script src="../../assets/vendors/counter/counterup.min.js"></script>
        <script src="../../assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="../../assets/vendors/masonry/masonry.js"></script>
        <script src="../../assets/vendors/masonry/filter.js"></script>
        <script src="../../assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src="../../assets/js/functions.js"></script>
        <script src="../../assets/js/contact.js"></script>
        <script src='../../assets/vendors/switcher/switcher.js'></script>

</body>

</html>
