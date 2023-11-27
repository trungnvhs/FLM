<%-- 
    Document   : newSubject
    Created on : Jun 6, 2023, 12:30:58 PM
    Author     : Trung Quan
--%>
<%@page import="dao.SubjectDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="../../assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="../../assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="../../assets/css/assets.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="../../assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="../../assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="../../assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="../../assets/css/color/color-1.css">

    </head>
    <body id="bg">
        <c:if test="${role!='Staff' && role!='Head'}">
            <%
                request.setAttribute("mess", "<script>alert(Sorry!!! You don't have role to access this page.);</script>");
                request.getRequestDispatcher("/view/userAccess/homePage.jsp").forward(request, response);
            %>
        </c:if>

        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <!-- Header Top ==== -->
            <%@include file="../common/Header.jsp" %>
            <%@include file="../common/SubHeaderCurriculum.jsp" %>

            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!--inner page banner--> 
                <div class="page-banner ovbl-dark" style="background-image:url(../../assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Elective</h1>
                        </div>
                    </div>
                </div>
                <!--Breadcrumb row--> 
                <div class="breadcrumb-row">
                    <div class="container">

                    </div>
                </div>
                <!--Breadcrumb row END--> 
                <!--inner page banner END--> 
                <!--About Us--> 
                <form id="frm" class="content-block" action="ElectiveDetailController" method="post">
                    <input type="text" name="mod" value="add" style="display: none">
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-1 col-md-1 col-sm-12 m-b30">
                                </div>
                                <div class="col-lg-10 col-md-9 col-sm-12 m-b30">
                                    <div class="profile-content-bx">   
                                        <div class="tab-pane" id="edit-profile">
                                            <div class="profile-head">
                                                <h3>New Elective Detail</h3>
                                            </div>

                                            <div class="edit-profile" >
                                                <div class="">
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label"> Elective Name <b style="color: red"> (*)</b> </label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-6">
                                                            <input style="width: 600px" id="name" name="name" class="form-control" readonly='' type="text" value="${name}" >
                                                        </div>
                                                    </div>
<!--                                                    <div class="form-group row" >
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Type</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9" >
                                                            <input style="width: 600px" name="type" class="form-control" type="text"  required="" value="Elective" readonly="">
                                                        </div>
                                                    </div> 
                                                    <div class="form-group row" >
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Curriculum</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-6">
                                                            <input style="width: 600px"  name="type" class="form-control" type="text"  required="" value="${dataCu.getName()}" readonly="">
                                                            <input style="width: 600px" name="curriculum_id" class="form-control" type="hidden" value="${dataCu.getCurriculum_id()}" >
                                                        </div>
                                                    </div>-->
                                                    <div class="form-group row" >
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Subject</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-6">
                                                            <select name='subjectid'>
                                                                <c:forEach items="${dataCur}" var="i">
                                                                    <option value='${i.getCurriculum_id()}'>
                                                                        ${i.getGroup_id()}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>



<!--                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Is Active</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input id='is-active' name="isActive" type="checkbox" value="1" style="width: 19px; height: 19px; margin-top: 8px">
                                                        </div>
                                                    </div>-->
                                                    <div class="seperator"></div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                    </div>
                                                    <div class="col-12 col-sm-9 col-md-9 col-lg-7">

                                                        <input name="btn_oke1" class="btn" type="submit" value="Add" >
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> 
                                </div> 
                            </div>
                        </div>
                    </div>
                </form>
                <!--contact area END--> 
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <%@include file="../common/Footer.jsp" %>
            <!-- Footer END ==== -->
        </div>
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
            function checkIsActive() {
                isAct = "${isActive}";
                if (isAct === '1') {
                    document.getElementById("is-active").checked = true;
                }
            }
            checkIsActive();
        </script>
        <script>
            const input = document.getElementById('name');

            input.addEventListener('input', function () {
                if (this.value.length > 1000) {
                    this.value = this.value.slice(1, 1000); // Cắt bớt giá trị nhập vào
                }
            });
        </script>
    </body>
</html>