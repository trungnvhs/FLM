<%-- 
    Document   : cloDetail.jsp
    Created on : Jul 20, 2023, 11:19:32 PM
    Author     : ThinkPad P50
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.CLODAO" %>
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
            <%@include file="../common/SubHeaderSyllabus.jsp" %>

            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!--inner page banner--> 
                <div class="page-banner ovbl-dark" style="background-image:url(../../assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">CLO</h1>
                        </div>
                    </div>
                </div>
                <!--Breadcrumb row--> 
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp">Home</a></li>
                            <li><a href="../po/polist">CLO List</a></li>
                            <li>CLO Detail</li>
                        </ul>
                    </div>
                </div>
                <!--Breadcrumb row END--> 
                <!--inner page banner END--> 
                <!--About Us--> 
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
                                            <h3>CLO Detail</h3>
                                        </div>
                                        <form class="edit-profile m-b30" action="cloadd" method="post">
                                            <input type="text" name="mod" value="update" style="display: none">
                                            <div class="edit-profile" >
                                                <div class="">
                                                    <input name="cloid" value="${clo.clo_id}" style="display: none">
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">CLO Name : <i style="color: red">*</i></label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                            <div> <input name="name" class="form-control" type="text" value="${clo.clo_name}" required="" maxlength="20"></div>
                                                            <input name="namec" value="${clo.clo_name}" style="display: none">
                                                            <span style="color: red">${error1}</span>
                                                            <span style="color: red">${error2}</span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">CLO Description  : </label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                            <textarea name="description" class="form-control"  style="height: 250px;" required="" maxlength="250">${clo.clo_description}</textarea>
                                                        </div>
                                                    </div>   
                                                    <input name="sysID" value="${sysID}" style="display: none">  
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Is Active : </label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="isact" value="${clo.getIs_active()}" style="display: none">
                                                                <c:if test="${clo.is_active =='1'}">
                                                                <td class="col-md-2"><input type="checkbox"style="width: 19px; height: 19px; margin-top: 8px" disabled="" checked=""></td>
                                                                </c:if>
                                                                <c:if test="${clo.is_active !='1'}">
                                                                <td class="col-md-2"><input type="checkbox"style="width: 19px; height: 19px; margin-top: 8px" disabled=""></td>
                                                                </c:if>

                                                        </div>
                                                    </div>

                                                    <div class="seperator"></div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                    </div>
                                                    <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                        <c:if test="${role=='Staff' || role=='Head'}"> <input name="btn_oke" class="btn" type="submit" value="Save" ></c:if>

                                                            <a href="../clo/clolist?&&sysID=${sysID}" class="btn-secondry" >Cancel</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
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
    </body>
</html>
