<%-- 
    Document   : syllabusManage
    Created on : Jul 19, 2023, 8:44:54 PM
    Author     : Trung Quan
--%>
<%@page import="dao.SyllabusDAO" %>
<%@page import="dao.CurriculumDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="../../assets/css/styleFormImport.css" rel="stylesheet">
        <link href="../../assets/css/toast.css" rel="stylesheet">
        <style>
            .pricingtable-features tr{
                background-color: #fff;
            }
            .pricingtable-features tr:nth-child(2n) {
                background-color: #cccccc;
            }
            #select-search-type{
                border: #cccccc 1px solid;
                width: 75px;
                margin-right: 10px;
            }
            pre {
                white-space: pre-wrap;
                word-wrap: break-word;
                font-family: Rubik!important;
                font-size: 16px!important;
            }
            .td-left{
                text-align: left;
            }
        </style>
    </head>
    <body>
        <div class="toast-notifications">
            <i class="fa fa-info-circle"></i>
            <span>${mess}</span>
            <i class="fa-solid fa-xmark" onclick="removeToast(this.parentElement)"></i>
        </div>
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <!-- Header Top ==== -->
            <%@include file="../common/Header.jsp" %>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!--Breadcrumb row--> 
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp">Home</a></li>
                            <li>Syllabus List</li>
                        </ul>
                    </div>
                </div>
                <!--Breadcrumb row END--> 
                <!--About Us--> 
                <div class="content-block">
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="card col-md-12">
                                    <div class="card-body" style="margin-left: 30px">
                                        <h4 class="card-title text-uppercase mb-0">Syllabus List</h4>
                                    </div>
                                    <div>
                                        <div class="row" style="margin-bottom: 20px">
                                            <div class="search-bx style-1 col-lg-4 col-xl-4 col-md-5">
                                                <form action="SubjectController" method="post">
                                                    <input type="text" name="mod" value="search" style="display: none" />
                                                    <div class="input-group">
                                                        <input id="search" name="text" class="form-control" placeholder="Enter your keywords..." type="text" value="${search}">
                                                        <span class="input-group-btn">
                                                            <button type="submit" class="fa fa-search" style="background-color: #4c1864"></button>
                                                        </span> 
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="col-md-3" style=""></div>
                                            <a class="col-md-2" href="newSubject.jsp">
                                                <button name="submit" type="submit" value="Submit" class="btn button-md">New Syllabus</button>
                                            </a>
                                            <div class="col-md-3">
                                                <button class="btn-secondry  button-md" onclick="showImport()">Import Syllabus</button>
                                            </div>
                                        </div>

                                        <div class="pricingtable-wrapper">
                                            <div class="pricingtable-inner">
                                                <table class="table no-wrap user-table mb-0 pricingtable-main">
                                                    <thead class="pricingtable-title" style="font-weight: 700">
                                                        <tr style="text-align: left">
                                                            <th style="width: 8.5%">Syllabus ID</th>
                                                            <th style="width: 8.5%">Subject Code</th>
                                                            <th style="width: 24%; line-height: 49px">Syllabus Name</th>
                                                            <th style="width: 14%; line-height: 49px">Designer</th>
                                                            <th style="width: 8.5%; line-height: 49px">IsActive</th>
                                                            <th style="width: 10%; line-height: 49px">IsApproved</th>
                                                            <th style="width: 16.7%">DecisionNo<br>MM/dd/yyyy</th>
                                                            <th style="width: 10.3%; line-height: 49px">Action</th>
                                                        </tr>
                                                    </thead>

                                                    <tbody class="pricingtable-features">
                                                        <c:forEach var="i" items="${dataSyllabus}">
                                                            <tr>
                                                                <td style="width: 8.5%">${i.getSyllabus_id()}</td>
                                                                <td style="width: 8.5%; text-align: justify">${SyllabusDAO.getSubjectCode(i.getSubject_id())}</td>
                                                                <td style="width: 24%; text-align: left"><a href="SyllabusController?id=${i.getSyllabus_id()}&mod=view">${i.getName()}</a></td>
                                                                <td style="width: 14%; text-align: left">${i.getDesigner().getUser_name()}</td>
                                                                <td style="width: 8.5%">${i.getIs_active()}</td>
                                                                <td style="width: 10%">${i.getIs_approved()}</td>
                                                                <td style="width: 16.7%; text-align: left">${CurriculumDAO.getDecisionNoDate(i.getDecision_id())}</td>
                                                                <td style="width: 10.3%; padding: 5px; vertical-align: middle">
                                                                    <a href="SyllabusController?id=${i.getSyllabus_id()}&mod=view"><button class="btn" style="width: 30%; padding: 5px"><i class="fa fa-eye"></i></button></a>
                                                                            <c:if test="${role=='Staff' || role=='Head'}">
                                                                        <a href="SyllabusController?id=${i.getSyllabus_id()}&mod=edit"><button class="btn" style="width: 30%; padding: 5px"><i class="fa fa-edit"></i> </button></a>
                                                                        <a href="SyllabusController?id=${i.getSyllabus_id()}&mod=del"><button class="btn" style="width: 30%; padding: 5px"><i class="fa fa-trash-o"></i> </button></a>
                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="height: 50px"></div>
                                    <div class="pagination-bx rounded-sm gray clearfix row" style="margin-bottom: 20px">
                                        <span style="width: 90px"></span>
                                        <span class="col-lg-7"></span>
                                        <ul class="pagination  col-md-4">
                                            <c:if test="${page==1}">
                                                <li class="previous"><a><i class="ti-arrow-left"></i> Prev</a></li>
                                                        </c:if>
                                                        <c:if test="${page!=1}">
                                                <li class="previous"><a href="../syllabus/SyllabusController?mod=1&&page=${page-1}<c:if test="${search!=null && search!=''}">&search=${search}</c:if>"><i class="ti-arrow-left"></i> Prev</a></li>
                                                </c:if>
                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                    <c:if test="${i==page}">
                                                    <li id="${i}" class="active"><a href="../syllabus/SyllabusController?mod=1&&page=${i}<c:if test="${search!=null && search!=''}">&search=${search}</c:if>">${i}</a></li>
                                                    </c:if>
                                                    <c:if test="${i!=page}">
                                                    <li id="${i}"><a href="../syllabus/SyllabusController?mod=1&&page=${i}<c:if test="${search!=null && search!=''}">&search=${search}</c:if>">${i}</a></li>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${page==endPage}">
                                                <li class="previous"><a>Next <i class="ti-arrow-right"></i></li>
                                                </c:if>
                                                <c:if test="${page!=endPage}">
                                                <li class="next"><a href="../syllabus/SyllabusController?mod=1&&page=${page+1}<c:if test="${search!=null && search!=''}">&search=${search}</c:if>">Next <i class="ti-arrow-right"></i></a></li>
                                                    </c:if>
                                        </ul>

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
            <button class="back-to-top fa fa-chevron-up" ></button>
        </div>
        <form class="form-import-file" action="subjectImportController" method="post" enctype="multipart/form-data">
            <div class="form-import-container">
                <div class="form-import-close" onclick="closeImport()">
                    <i class="ti-close" ></i>
                </div>
                <header class="form-import-header">
                    <i class="fa fa-cloud-upload" style="margin-right: 16px"></i>
                    Import Subject
                </header>
                <div class="form-import-body">
                    <div>
                        Choose a file excel
                    </div>
                    <input type="file" name="file" id="file" value=""/>
                    <br>
                    <div style="text-align: center">
                        <input type="submit" name="btn-import" value="Save" class="btn button-md"/>
                    </div>

                </div>
            </div>
        </form>
        <!-- External JavaScripts -->
        <!--<script src="../../assets/js/jquery.min.js"></script>-->
        <script src="../../assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="../../assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="../../assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="../../assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
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
        <script>
                    function showImport() {
                        var showBtn = document.querySelector('.form-import-file');
                        showBtn.classList.add('form-import-open');
                    }
                    function closeImport() {
                        var closeBtn = document.querySelector('.form-import-file');
                        closeBtn.classList.remove('form-import-open');
                    }
        </script>
        <script>
            function showToast() {
                var showBtn = document.querySelector('.toast-notifications');
                showBtn.classList.add('show-toast');
            }
            function closeToast() {
                var closeBtn = document.querySelector('.toast-notifications');
                closeBtn.classList.remove('show-toast');
                closeBtn.classList.add('remove-toast');
            }
            if ('${mess}' !== "") {
                showToast();
                setTimeout(closeToast, 5000);
            }
        </script>
    </body>
</html>