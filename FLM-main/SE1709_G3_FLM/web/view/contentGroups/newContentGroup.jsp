<%-- 
    Document   : newContentGroup
    Created on : Jun 23, 2023, 10:48:30 PM
    Author     : Trung Quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="../../assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="../../assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
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
                            <li><a href="/SE1709_G3_FLM/view/curriculumSubject/curriculumSubjectController?mod=1&&page=1">Curriculum List</a></li>
                            <li>Subject PLOs</li>
                            <li><a href="/SE1709_G3_FLM/view/contentGroups/ContentGroupController?mod=paging&page=1">Content Groups</a></li>
                            <li>New Content Groups</li>
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
                                            <h3>New Content Group</h3>
                                        </div>
                                        <div class="edit-profile" >
                                            <form action="ContentGroupController" method="post">
                                                <input type="text" name="mod" value="add" style="display: none"/>
                                                <div class="form-group row">
                                                    <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label" style="text-align: right">Curriculum Code</label>
                                                    <div class="col-12 col-sm-8 col-md-8 col-lg-8">
                                                        <input name="curID" class="form-control" type="text" 
                                                               value="${CurriculumDAO.getCurriculumByCurriculumID(curID).getCode()}" readonly=""/>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label" style="text-align: right">Content Group Name<span style="color: red">*</span></label>
                                                    <div class="col-12 col-sm-8 col-md-8 col-lg-8">
                                                        <input name="name" class="form-control" type="text"  required="" value="${name}" maxlength="150"></input>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label" style="text-align: right">Display Order<span style="color: red">*</span></label>
                                                    <div class="col-12 col-sm-2 col-md-2 col-lg-2">
                                                        <input name="disOrder" class="form-control" type="text"  required="" value="${disOrder}" maxlength="2"></input>
                                                    </div>
                                                </div>
                                                <div class="seperator"></div>
                                                <div class="row">
                                                    <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                    </div>
                                                    <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                        <input name="btn_oke" class="btn" type="submit" value="Submit" >
                                                        <a href="/SE1709_G3_FLM/view/contentGroups/ContentGroupController?mod=paging&page=1" class="btn-secondry" >Cancel</a>
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
                <!--contact area END--> 
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <%@include file="../common/Footer.jsp" %>
            <!-- Footer END ==== -->
            <!--<button class="back-to-top fa fa-chevron-up" ></button>-->
        </div>
    </body>
</html>
