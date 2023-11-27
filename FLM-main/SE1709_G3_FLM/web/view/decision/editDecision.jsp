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
            <select name="curriculumdecision">
                <c:forEach  items="${dataCu}" var="i">
                    <option value="${i.getCurriculum_id()}">${i.getCode()}</option> 
                </c:forEach>
            </select>
            <div class="page-content bg-white">
                <!--inner page banner--> 
                <div class="page-banner ovbl-dark" style="background-image:url(../../assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Edit Decision</h1>
                        </div>
                    </div>
                </div>
                <form   action="editDecisionController" method="post" >
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">

                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">   
                                        <div class="tab-pane" id="edit-profile">
                                            <div class="profile-head">
                                                <h3>Edit Decision</h3>
                                            </div>
                                            <div class="edit-profile" >
                                                <div class="">
                                                    <span style="color: red">${error1}</span>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Decision_No</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="id" class="form-control" type="text" style="display: none" value="${dataDe.getDecision_id()}" required="">

                                                            <input name="decisionNo" maxlength="50" class="form-control" type="text" value="${dataDe.getDecision_no()}" required="">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Decision_Name</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="decisionName" maxlength="100" class="form-control" type="text" value="${dataDe.getDecision_name()}" required="">
                                                        </div>
                                                    </div> 
                                                    <c:if test="${dataDe.getDecision_ApprovedDate() == null}">
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">ApprovedDate</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input name="approveddate"  class="form-control" type="date" required="">
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${dataDe.getDecision_ApprovedDate() != null}">
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">ApprovedDate</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input name="approveddate"  class="form-control" type="text" value="${dataDe.getDecision_ApprovedDate()}" required="" >
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Note</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="note" class="form-control" type="text" value="${dataDe.getNote()}">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <input name="decisionDate" class="form-control" type="text" value="${dataDe.getDecision_CreateDate()}" required="" style="display: none">
                                                        </div>
                                                    </div>  
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Curriculum</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <select name="curriculumdecision">
                                                                <c:forEach  items="${dataCu}" var="i">
                                                                    <option value="${i.getCurriculum_id()}">${i.getCode()} </option> 
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div> 
                                                    <div class="form-group row">
                                                        <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Syllabus</label>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                            <select name="syllabusdecision" >
                                                                <c:forEach  items="${dataSy}" var="i">
                                                                    <option value="${i.getSyllabus_id()}">${i.getName()}</option> 
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div> 
                                                    <c:if test="${account.getUser_id()!=null}">
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Created_id</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input name="createdid" class="form-control" type="text" value="${account.getUser_name()}" readonly="">
                                                            </div>
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-3 col-form-label" style="color: red"></label>
                                                        </div>
                                                    </c:if>

                                                    <div class="seperator"></div>
                                                </div>
                                                <div class="">
                                                    <div class="">
                                                        <div class="row">
                                                            <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                            </div>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input name="btn_oke" class="btn" type="submit" value="Submit" >
                                                                <a href="../decision/decisionController?mod=1&&page=1" class="btn-secondry" >Cancel</a>
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

    </body>
</html>
