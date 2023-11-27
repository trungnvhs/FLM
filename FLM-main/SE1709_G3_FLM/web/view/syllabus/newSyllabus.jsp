<%-- 
    Document   : profile
    Created on : May 20, 2023, 5:27:57 PM
    Author     : Trung Quan
--%>

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
            .profile-avatar{
                width: 75%;
                height: 23%;
            }
        </style>
    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>

            <%@include file="../common/Header.jsp" %>

            <div class="page-content bg-white">
                <!--inner page banner--> 
                <div class="page-banner ovbl-dark" style="background-image:url(../../assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Add Syllabus</h1>
                        </div>
                    </div>
                </div>
                <form   action="addCurriculumController" method="post" >
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">

                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">   
                                        <div class="tab-pane" id="edit-profile">
                                            <div class="profile-head">
                                                <h3>New Syllabus</h3>
                                            </div>
                                            <div class="edit-profile" >
                                                <div class="">
                                                    <span style="color: red">${error1}</span>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Syllabus Name*</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="code" class="form-control" type="text" maxlength="100" value="${code}" required="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Subject Code*</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <textarea name="name" class="form-control" maxlength="150" required="">${name}</textarea>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Asignee</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <select name="ownerid">
                                                                <c:forEach  items="${dataStaff}" var="i">
                                                                    <option value="${i.getUser_id()}">${i.getUser_name()}
                                                                    </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div> 
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Description</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <textarea name="description" class="form-control">${description} </textarea>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Creator_id</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="creator" class="form-control" type="text" value="${account.getUser_id()}" readonly="">
                                                        </div>
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-3 col-form-label" style="color: red"></label>
                                                    </div>
                                                    <div class="seperator"></div>
                                                </div>
                                                <div class="">
                                                    <div class="">
                                                        <div class="row">
                                                            <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                            </div>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input name="btn_oke" class="btn" type="submit" value="Submit" >
                                                                <a href="../curriculum/curriculumController?mod=1&&page=1" class="btn-secondry" >Cancel</a>
                                                            </div>
                                                        </div>
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
            <button class="back-to-top fa fa-chevron-up" ></button>
        </div>
        <!-- External JavaScripts -->
        <script src="../../assets/js/jquery.min.js"></script>
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
