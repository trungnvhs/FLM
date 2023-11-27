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
            .profile-avatar img{
                width: 100%;
                height: 100%;
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
                <!--inner page banner--> 
                <div class="page-banner ovbl-dark" style="background-image:url(../../assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Profile</h1>
                        </div>
                    </div>
                </div>
                <!--Breadcrumb row--> 
<!--                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="#">Home</a></li>
                            <li>Profile</li>
                        </ul>
                    </div>
                </div>-->
                <!--Breadcrumb row END--> 
                <!--inner page banner END--> 
                <!--About Us--> 
                <form id="frm" class="content-block" action="EditProfileController" method="post" enctype="multipart/form-data">
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                    <div  class="profile-content-bx text-center">
                                        <div class="user-profile-thumb profile-avatar">
                                            <img src="../../assets/images/avatar/${avatar}" alt=""/>

                                        </div>

                                        <div  class="profile-social">
                                            <input type="file" name="file" id="file" class="inputfile" style="display: none" onchange="change()" value="${avatar}"/>
                                            <label class="btn" for="file">Change avatar image</label>
                                            <input id="btn-change-avatar" name="btn-avatar" type="submit" style="display: none">
                                        </div>
                                        <span style="color: red">${err1}</span>
                                        <input type="text" name="avatar" value="${avatar}" style="display: none">
                                    </div><!--</form>-->
                                </div>
                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">   
                                        <div class="tab-pane" id="edit-profile">
                                            <div class="profile-head">
                                                <h3>Profile</h3>
                                            </div>
                                            <div class="edit-profile" action="EditProfileController" method="post" enctype="multipart/form-data">
                                                <div class="">
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Full Name</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="fullName" class="form-control" type="text" value="${user.getFull_name()}" required="">
                                                        </div>
                                                    </div>

                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">User Name</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="userName" class="form-control" type="text" value="${user.getUser_name()}">
                                                        </div>
                                                    </div>
                                                    <c:if test="${account.getEmail()==null}">
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Email</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input name="mail" class="form-control" type="email" value="${user.getEmail()}" >
                                                            </div>
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-3 col-form-label" style="color: red">${ErrorMail}</label>

                                                        </div>
                                                    </c:if>
                                                    <c:if test="${account.getEmail()!=null}">
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Email</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input name="mail" class="form-control" type="email" value="${user.getEmail()}" readonly="">
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${account.getMobile()==null}">
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Mobile</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input name="phone" class="form-control" type="text" value="${user.getMobile()}">
                                                            </div>
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-3 col-form-label" style="color: red">${ErrorPhone}</label>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${account.getMobile()!=null}">
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Mobile</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input name="phone" class="form-control" type="text" value="${user.getMobile()}" readonly="">
                                                            </div>
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-3 col-form-label" style="color: red"></label>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${account.getMobile()==null || account.getEmail()==null}">
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Verification Code</label>
                                                            <div class="col-7 col-sm-5 col-md-5 col-lg-5">
                                                                <input name="verifyCode" class="form-control" type="text" value="">
                                                                <input name="checkCode" class="form-control" type="text" value="${checkCode}" style="display: none">
                                                            </div>
                                                            <div class="col-5 col-sm-4 col-md-4 col-lg-3">
                                                                <input class="btn" name="btn" type="submit" value="Send Code" >
                                                            </div>
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-3 col-form-label" style="color: red">${ErrorCode}</label>
                                                        </div>
                                                    </c:if>
                                                    <div class="seperator"></div>

                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Job Title</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="jobTitle" class="form-control" type="text" value="${user.getJob_title()}">
                                                        </div>
                                                    </div>

                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Company</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="company" class="form-control" type="text" value="${user.getCompany()}">
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="">
                                                    <div class="">
                                                        <div class="row">
                                                            <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                            </div>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input name="btn-oke" class="btn" type="submit" value="Save changes" >
                                                                <input name="btn-cancel"class="btn-secondry" type="submit" value="Cancel" >
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
                                                function change() {
                                                    document.getElementById("btn-change-avatar").click();
                                                }
        </script>
    </body>
</html>
