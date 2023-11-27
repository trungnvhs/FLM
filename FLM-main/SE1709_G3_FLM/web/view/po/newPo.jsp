<%-- 
    Document   : newPo.jsp
    Created on : Jun 18, 2023, 1:46:19 PM
    Author     : ThinkPad P50
--%>

<%@page import="dao.PODAO" %>
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
                <!--Breadcrumb row--> 
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp">Home</a></li>
                            <li><a href="../po/polist">PO List</a></li>
                            <li>New PO</li>
                        </ul>
                    </div>
                </div>
                <!--Breadcrumb row END--> 
                <!--inner page banner END--> 
                <!--About Us--> 
                <form id="frm" class="content-block" action="poadd" method="post">
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
                                                <h3>New PO</h3>
                                            </div>
                                            <div class="edit-profile" >
                                                <div class="">
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">PO Name<i style="color: red">*</i></label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                            <input placeholder="Enter Name..." name="name"  maxlength="50" class="form-control" type="text" maxlength="20" required="" value="${name}">
                                                            <span style="color: red">${error1}</span>
                                                            <span style="color: red">${error2}</span>
                                                        </div>
                                                    </div> 

                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">PO Description</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                            <textarea name="description" class="form-control"  style="height: 250px;" required="" maxlength="250">${description} </textarea>
                                                        </div>
                                                    </div>   
                                                            <input name="cu_id" value="${curID}" style="display: none">
                                                        <input  name="isactive" value="0" style="display: none">
                                                    <div class="seperator"></div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                    </div>
                                                    <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                        <input name="btn_oke" class="btn" type="submit" value="Submit" >
                                                        <a href="../po/polist?&&cu_id=${curID}" class="btn-secondry" >Cancel</a>
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

    </body>
</html>
