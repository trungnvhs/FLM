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
                <!--Breadcrumb row--> 
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp">Home</a></li>
                            <li><a href="/SE1709_G3_FLM/view/curriculumSubject/curriculumSubjectController?mod=1&&page=1">Curriculum List</a></li>
                            <li><a href="/SE1709_G3_FLM/view/curriculumSubject/curriculumSubjectController?mod=1&&page=1">Curriculum Subject List</a></li>
                            <li>Add Curriculum Subject</li>
                        </ul>
                    </div>
                </div>
                <!--Breadcrumb row END-->
                <form   action="/SE1709_G3_FLM/view/curriculumSubject/curriculumSubjectController" method="Post" >
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">

                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">   
                                        <div class="tab-pane" id="edit-profile">
                                            <div class="profile-head">
                                                <h3>New Curriculum Subject</h3>
                                            </div>
                                            <div class="edit-profile" >
                                                <div class="">
                                                    
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Subject ID</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="subjectid" class="form-control" type="text" value="${subjectid}" required="">
                                                        </div>
                                                        <span style="color: red">${error1}</span>
                                                        <span style="color: red">${error2}</span>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Syllabus ID</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="syllabusid" class="form-control" type="text" value="${syllabusid}" required="">

                                                        </div>
                                                        <span style="color: red">${error3}</span>
                                                    </div>                                                   
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">group ID</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="groupid" class="form-control" type="text" value="${groupid}" required="">
                                                        </div>
                                                        <span style="color: red">${error4}</span>
                                                    </div>                                                 
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Semester</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="semester" class="form-control" type="text" value="${semester}" required="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">NO_Credit</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="nocredit" class="form-control" type="text" value="${nocredit}" required="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Is Active</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input id='is-active' name="isActive" type="checkbox" value="1" style="width: 19px; height: 19px; margin-top: 8px">
                                                        </div>
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
                                                                <a href="../curriculumSubject/curriculumSubjectController?mod=1&&page=1" class="btn-secondry" >Cancel</a>
                                                            </div>
                                                            ${error5}
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
