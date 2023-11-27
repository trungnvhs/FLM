<%-- 
    Document   : subjectList
    Created on : Jun 6, 2023, 12:18:04 PM
    Author     : Trung Quan
--%>
<%--<%@page import="dao.SubjectDAO" %>--%>
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
                            <li>Subject List</li>
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
                                        <h4 class="card-title text-uppercase mb-0">Subject List</h4>
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
                                                <button name="submit" type="submit" value="Submit" class="btn button-md">Add New Subject</button>
                                            </a>
                                            <div class="col-md-3">
                                                <button class="btn-secondry  button-md" onclick="showImport()">Import Subject</button>
                                            </div>
                                        </div>

                                        <div class="pricingtable-wrapper">
                                            <div class="pricingtable-inner">
                                                <table class="table no-wrap user-table mb-0 pricingtable-main">
                                                    <thead class="pricingtable-title">
                                                        <tr class="row">
                                                            <th class="col-md-2">Subject Code</th>
                                                            <th class="col-md-3">Subject Name</th>
                                                            <th class="col-md-5">Description</th>
                                                            <th class="col-md-2">Is Active</th>
                                                            <!--<th>Display Order</th>-->
                                                        </tr>
                                                    </thead>

                                                    <tbody class="pricingtable-features">
                                                        <c:forEach items="${dataSubject}" var="i">
                                                            <tr class="row">
                                                                <td class="col-md-2">${i.getCode()}</td>
                                                                <td class="col-md-3"><a href="SubjectController?id=${i.getSubject_id()}">${i.getName()}</a></td>
                                                                <c:if test="${i.getDescription()== null}">
                                                                <td class="col-md-5 td-left"></td>
                                                                </c:if>
                                                                <c:if test="${i.getDescription().length()<150}">
                                                                    <td class="col-md-5 td-left"><pre style="width: 100%">${i.getDescription()}</pre></td>
                                                                    </c:if>
                                                                    <c:if test="${i.getDescription().length()>=150}">
                                                                    <td class="col-md-5 td-left"><pre style="width: 100%">${i.getDescription().substring(0, 150)} ...</pre></td>
                                                                </c:if>

                                                                <c:if test="${i.getIs_active()=='1'}">
                                                                    <td class="col-md-2"><input type="checkbox"style="width: 19px; height: 19px; margin-top: 8px" disabled="" checked=""></td>
                                                                    </c:if>
                                                                    <c:if test="${i.getIs_active()!='1'}">
                                                                    <td class="col-md-2"><input type="checkbox"style="width: 19px; height: 19px; margin-top: 8px" disabled=""></td>
                                                                    </c:if>
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
                                        <span class="col-lg-6"></span>
                                        <ul class="pagination  col-md-5">
                                            <c:if test="${page==1}">
                                                <li class="previous"><a><i class="ti-arrow-left"></i> Prev</a></li>
                                                        </c:if>
                                                        <c:if test="${page!=1}">
                                                <li class="previous"><a href="../subject/SubjectController?mod=1&page=${page-1}<c:if test="${search!=null && search!=''}">&search=${search}</c:if>"><i class="ti-arrow-left"></i> Prev</a></li>
                                                </c:if>
                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                    <c:if test="${i==page}">
                                                    <li id="${i}" class="active"><a href="../subject/SubjectController?mod=1&page=${i}<c:if test="${search!=null && search!=''}">&search=${search}</c:if>">${i}</a></li>
                                                    </c:if>
                                                    <c:if test="${i!=page}">
                                                    <li id="${i}"><a href="../subject/SubjectController?mod=1&page=${i}<c:if test="${search!=null && search!=''}">&search=${search}</c:if>">${i}</a></li>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${page==endPage}">
                                                <li class="previous"><a>Next <i class="ti-arrow-right"></i></li>
                                                </c:if>
                                                <c:if test="${page!=endPage}">
                                                <li class="next"><a href="../subject/SubjectController?mod=1&&page=${page+1}<c:if test="${search!=null && search!=''}">&search=${search}</c:if>">Next <i class="ti-arrow-right"></i></a></li>
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
                    <a href="subjectImportController" style="color: #4c1864"><b>Download Template</b></a>
                    <h4>
                        Choose a file excel
                    </h4>
                    <input type="file" name="file" id="file" value="${file}"/>
                    <br>
                    <c:if test="${messErr!=null || messSuc!=null}">
                        <br>
                        <div>${messSuc}</div>
                        <div style="color: red">${messErr}</div>
                        <div style="text-align: center">
                            <input type="submit" name="btn-save" value="Save" class="btn button-md"/>
                            <input type="submit" name="btn-cancel" value="Cancel" class="btn button-md"/>
                        </div><br>
                    </c:if>
                    <c:if test="${messErr==null && messSuc==null}">
                        <div style="text-align: center">
                            <input type="submit" name="btn-import" value="Import" class="btn button-md"/>
                        </div>
                    </c:if>


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
                    if (${messErr!=null || messSuc!=null}) {
                        var showBtn = document.querySelector('.form-import-file');
                        showBtn.classList.add('form-import-open');
                    }
        </script>
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
