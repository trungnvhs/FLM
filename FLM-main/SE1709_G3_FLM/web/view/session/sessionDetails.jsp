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
        <%@include file="../common/SubHeaderSyllabus.jsp" %>
            <div class="page-content bg-white">
                <!--Breadcrumb row--> 
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            
                        </ul>
                    </div>
                </div>
                <!--Breadcrumb row END-->
                <form >
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">

                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">   
                                        <div class="tab-pane" id="edit-profile">
                                            <div class="profile-head">
                                                <h3>Session Details</h3>
                                            </div>
                                            <div class="edit-profile" >
                                                <div class="">

                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Session ID:</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="ss_id" class="form-control" type="text" style="width: 580px" value="${dataSe.getSession_id()}" readonly="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Syllabus ID:</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                            <input type="text" name="sy_id" class="form-control" value="${dataSe.getSyllabus_id()}" readonly="">
                                                        </div>
                                                    </div> 
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Name:</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="name" class="form-control" style="width: 580px" type="text" value="${dataSe.getName()}" readonly="">
                                                        </div>
                                                    </div> 
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Details:</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                            <textarea name="details" class="form-control" readonly="">${dataSe.getDetails()} </textarea>
                                                        </div>
                                                    </div>                                                 
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Learning Type</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                            <input type="text" name="type" class="form-control" value="${dataSe.getLearning_type()}" readonly=""> 
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
        <!--        <script src="../../assets/js/jquery.min.js"></script>
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
                <script src='../../assets/vendors/switcher/switcher.js'></script>-->

    </body>
</html>
