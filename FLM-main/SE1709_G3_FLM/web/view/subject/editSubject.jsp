<%-- 
    Document   : subjectDetail
    Created on : Jun 6, 2023, 4:25:17 PM
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
        <style>
            pre {
                white-space: pre-wrap;
                word-wrap: break-word;
                font-family: Rubik!important;
                font-size: 16px!important;
                color: #505050!important;
            }
        </style>
    </head>
    <body id="bg">
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
                            <li><a href="../subject/SubjectController?mod=1&&page=1">Subject List</a></li>
                            <li>Edit Subject</li>
                        </ul>
                    </div>
                </div>
                <!--Breadcrumb row END--> 
                <!--inner page banner END--> 
                <!--About Us--> 
                <div class="section-area section-sp1">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-1 col-md-1 col-sm-12 m-b30">
                            </div>
                            <div class="col-lg-10 col-md-9 col-sm-12 m-b30">
                                <div class="profile-content-bx">   
                                    <div class="tab-pane" id="edit-profile">
                                        <div class="profile-head">
                                            <h3>Edit Subject</h3>
                                        </div>
                                        <div class="edit-profile" >
                                            <div class="">
                                                <div class="form-group row">
                                                    <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Subject Code</label>
                                                    <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                        <input name="code" class="form-control" type="text" value="${subject.getCode()}" required="">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Subject Name</label>
                                                    <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                        <input name="name" class="form-control" type="text"  required="" value="${subject.getName()}"></input>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Type</label>
                                                    <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                        <input name="type" class="form-control" type="text"  required="" value="${subject.getType()}"></input>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Description</label>
                                                    <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                        <textarea name="description" class="form-control" required="">${subject.getDescription()} </textarea>
                                                    </div>
                                                </div>                                                 
                                                <div class="form-group row">
                                                    <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">IsActive</label>
                                                    <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                        <input id='is-active' name="isActive" type="checkbox" value="1" style="width: 19px; height: 19px; margin-top: 8px" disabled="">
                                                    </div>
                                                </div>

                                            </div>
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
                    isAct = "${subject.getIs_active()}";
                    if (isAct === '1') {
                        document.getElementById("is-active").checked = true;
                    }
                }
                checkIsActive();
            </script>
    </body>
</html>
