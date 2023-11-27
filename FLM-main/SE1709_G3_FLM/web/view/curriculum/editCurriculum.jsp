<%-- 
    Document   : profile
    Created on : May 20, 2023, 5:27:57 PM
    Author     : Trung Quan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.CurriculumSubjectDAO" %>

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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">

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
            .header__nav-item {
                position: relative;
            }

            .dropdown-menu {
                display: none;
                position: absolute;
                top: 100%;
                left: 0;
                z-index: 999;
                background-color: #6846a5;
                padding: 10px;
                border: 1px solid #cccccc;
            }

            .header__nav-item:hover .dropdown-menu {
                display: block;
            }

            .dropdown-menu li {
                list-style: none;
            }

            .dropdown-menu li a {
                display: block;
                padding: 5px 10px;
                text-decoration: none;
                color: #6846a5;
            }

            .dropdown-menu li a:hover {
                background-color: #f5f5f5;
            }

        </style>
    </head>
    <body id="bg">
        <div class="toast-notifications">
            <i class="fa fa-info-circle"></i>
            <span>${mess}</span>
            <i class="fa-solid fa-xmark" onclick="removeToast(this.parentElement)"></i>
        </div>

        <div class="page-wraper">
            <div id="loading-icon-bx"></div>

            <%@include file="../common/Header.jsp" %>
            <%@include file="../common/SubHeaderCurriculum.jsp" %>

            <div class="page-content bg-white">
                <!--Breadcrumb row--> 
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp">Home</a></li>
                            <li><a href="/SE1709_G3_FLM/view/curriculum/curriculumController?mod=1&&page=1">Curriculum List</a></li>
                            <li>Edit Curriculum</li>
                        </ul>
                    </div>
                </div>
                <!--Breadcrumb row END--> 
                <form action="editCurriculumController" method="post">
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">

                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">   
                                        <div class="tab-pane" id="edit-profile">
                                            <div class="profile-head">
                                                <h3>Edit Curriculum</h3>
                                            </div>
                                            <div class="edit-profile" >
                                                <div class="">

                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Code<b style="color: red">(*)</b></label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">

                                                            <input name="id" readonly="" class="form-control" type="text" style="display: none" value="${dataCu.getCurriculum_id()}" required="">

                                                            <input name="code" readonly="" class="form-control" style="width: 580px" maxlength="255" type="text" value="${dataCu.getCode()}" required="">
                                                        </div>
                                                    </div>
                                                    ${error1}
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Name<b style="color: red">(*)</b></label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                            <textarea name="name" class="form-control" readonly="" maxlength="250" required="">${dataCu.getName()}</textarea>
                                                        </div>
                                                    </div>                                                   
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Description</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                            <textarea name="description" class="form-control" >${dataCu.getDescription()} </textarea>
                                                        </div>
                                                    </div>

                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Assignee</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="assignee" class="form-control" style="width: 580px" type="text" value="${assignee}" readonly="">
                                                            <input name="ownerid" class="form-control" style="width: 580px" type="hidden" value="${dataCu.getOwner_id()}">

                                                        </div>


                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Creator</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="Creator" class="form-control" style="width: 580px" type="text" value="${creator}" readonly="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Decision No&Date</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="Decision" class="form-control" style="width: 580px" type="text" value="${decisionnodate}" readonly="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row" style="display: none">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">DecisionID</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="decisionid" class="form-control"  style="width: 580px" type="text" value="${dataCu.getDecision_id()}" readonly="">
                                                        </div>
                                                    </div>                                                                                                                                                    
                                                </div> 
                                                <div class="form-group row">
                                                    <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Is Active</label>
                                                    <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                        <input id='is-active' name="isActive" type="checkbox" <c:if test="${dataCu.getIs_active() == '1'}">checked</c:if> value="${dataCu.getIs_active()}" style="width: 19px; height: 19px; margin-top: 8px">

                                                        </div>
                                                    </div>
                                                    <div class="">
                                                        <div class="">
                                                            <div class="row">
                                                                <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                                </div>
                                                                <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                    <input name="btn_oke" class="btn" type="submit" value="Submit" >
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <br><br>
                                                <br>
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
