<%-- 
    Document   : changePass
    Created on : May 20, 2023, 2:10:00 PM
    Author     : Trung Quan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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
        ${success}
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <!-- Header Top ==== -->
            <%@include file="../common/Header.jsp" %>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(../../assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Profile</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
<!--                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="#">Home</a></li>
                            <li><a href="../userAccess/profile.jsp">Profile</a></li>
                            <li>Change Password</li>
                        </ul>
                    </div>
                </div>-->
                <!-- Breadcrumb row END -->
                <!-- inner page banner END -->
                <div class="content-block">
                    <!-- About Us -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-2 col-md-3 col-sm-11 m-b29">

                                </div>
                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">
                                        <div class="tab-pane" id="change-password">
                                            <div class="profile-head">
                                                <h3>Change Password</h3>
                                            </div>
                                            <form class="edit-profile" action="ChangePassword" method="post">
                                                <div class="">
                                                    <div class="form-group row">
                                                        <div class="col-12 col-sm-8 col-md-8 col-lg-9 ml-auto">
                                                            <h3>Password</h3>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">Current Password</label>
                                                        <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                            <input name="oldPass" class="form-control" type="password" value="${oldPass}">
                                                        </div>  
                                                    </div>
                                                    <c:if test="${err1!=null}">
                                                    <div class="form-group row">
                                                        <Span class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label"></Span>
                                                        <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                            <span style="color: red">${err1}</span>
                                                        </div>
                                                    </div>
                                                    </c:if>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">New Password</label>
                                                        <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                            <input name="newPass" class="form-control" type="password" value="${newPass}">
                                                        </div>
                                                    </div>
                                                    <c:if test="${err2!=null}">
                                                    <div class="form-group row">
                                                        <Span class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label"></Span>
                                                        <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                            <span style="color: red">${err2}</span>
                                                        </div>
                                                    </div>
                                                    </c:if>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">Re Type New Password</label>
                                                        <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                            <input name="preNewPass" class="form-control" type="password" value="${preNewPass}">
                                                        </div>
                                                    </div>
                                                    <c:if test="${err3!=null}">
                                                    <div class="form-group row">
                                                        <Span class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label"></Span>
                                                        <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                            <span style="color: red">${err3}</span>
                                                        </div>
                                                    </div>
                                                    </c:if>
                                                </div>
                                                <div class="row">
                                                    <div class="col-12 col-sm-4 col-md-4 col-lg-3">
                                                    </div>
                                                    <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                        <input type="submit" class="btn" value="Save changes">
                                                        <input type="submit" name="cancel" class="btn-secondry" value="Cancel">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- contact area END -->
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <%@include file="../common/Footer.jsp" %>
            <!-- Footer END ==== -->
            <button class="back-to-top fa fa-chevron-up" ></button>
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

