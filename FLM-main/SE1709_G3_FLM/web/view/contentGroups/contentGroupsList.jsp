<%-- 
   Document   : contentGroupsList
   Created on : Jun 23, 2023, 6:06:25 PM
   Author     : Trung Quan
--%>
<%@page import="dao.CurriculumDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="../../assets/css/styleFormImport.css" rel="stylesheet">
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
        </style>
    </head>
    <body>
        <div class="toast-notifications">
            <i class="fa fa-info-circle"></i>
            <span>${mess}</span>
            <i class="fa-solid fa-xmark" onclick="removeToast(this.parentElement)"></i>
        </div>
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
                            <li>Content Groups</li>
                        </ul>
                    </div>
                </div>
                <!--Breadcrumb row END--> 
                <!--inner page banner END--> 
                <!--About Us--> 
                <div class="content-block">
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="card col-md-12">
                                    <div class="card-body row" style="margin-left: 20px">
                                        <h4 class="card-title text-uppercase mb-0 col-lg-9" style="line-height: 52.85px">Content Groups Management</h4>
                                    </div>
                                    <div>
                                        <div class="row" style="margin-bottom: 20px; margin-left: 30px">
                                            <div class="search-bx style-1 col-lg-4 col-xl-4 col-md-5">
                                                <!--                                                <form action="SubjectController" method="post">
                                                                                                    <input type="text" name="mod" value="search" style="display: none" />
                                                                                                    <div class="input-group">
                                                                                                        <input name="text" class="form-control" placeholder="Enter your keywords..." type="text" value="${search}">
                                                                                                        <span class="input-group-btn">
                                                                                                            <button type="submit" class="fa fa-search" style="background-color: #4c1864"></button>
                                                                                                        </span> 
                                                                                                    </div>
                                                                                                </form>-->
                                            </div>
                                            <div class=" col-lg-5 col-xl-5 col-md-5"></div>
                                            <c:if test="${role=='Staff' || role=='Head'}">
                                                <div class="col-md-2 col-lg-2">
                                                    <a href="/SE1709_G3_FLM/view/contentGroups/newContentGroup.jsp">
                                                        <button name="submit" type="submit" value="Submit" class="btn button-md">New Content Group</button>
                                                    </a>
                                                </div>
                                            </c:if>
                                        </div>

                                        <div style="margin-left: 20px">
                                            <div class="row">
                                                <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">CurriCulum ID</label>
                                                <div class="col-12 col-sm-9 col-md-9 col-lg-7" style="padding: 7px 0">
                                                    ${curID}
                                                </div>
                                            </div>
                                            <div class="row">
                                                <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">CurriCulum Code</label>
                                                <div class="col-12 col-sm-9 col-md-9 col-lg-7" style="padding: 7px 0">
                                                    ${CurriculumDAO.getCurriculumByCurriculumID1(curID).getCode()}
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">CurriCulum Name</label>
                                                <div class="col-12 col-sm-9 col-md-9 col-lg-7" style="padding: 7px 0">
                                                    ${CurriculumDAO.getCurriculumByCurriculumID1(curID).getName()}
                                                </div>
                                            </div>
                                        </div>

                                        <div class="pricingtable-wrapper">
                                            <div class="pricingtable-inner">
                                                <table class="table no-wrap user-table mb-0 pricingtable-main">
                                                    <thead class="pricingtable-title">
                                                        <tr class="row">
                                                            <th class="col-md-2">Content Group ID</th>
                                                            <th class="col-md-8">Content Group Name</th>
                                                            <th class="col-md-2"><c:if test="${role=='Staff' || role=='Head'}">Action</c:if></th>
                                                            </tr>
                                                        </thead>

                                                        <tbody class="pricingtable-features">
                                                        <c:forEach items="${dataContentGroup}" var="i">
                                                            <tr class="row">
                                                                <td class="col-md-2">${i.getGroup_id()}</td>
                                                                <td class="col-md-8" style="text-align: left"><a href="ContentGroupController?id=${i.getGroup_id()}">${i.getName()}</a></td>
                                                                <td class="col-md-2" style="align-content:center">
                                                                    <c:if test="${role=='Staff' || role=='Head'}">
                                                                        <a href="ContentGroupController?id=${i.getGroup_id()}&mod=edit"><button class="btn"><i class="fa fa-edit"></i> </button></a>
                                                                        <a href="ContentGroupController?id=${i.getGroup_id()}&mod=delete"><button class="btn"><i class="fa fa-trash-o"></i> </button></a>
                                                                    </c:if>
                                                                </td>
                                                            </tr> 
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="height: 50px"></div>
                                    <div class="pagination-bx rounded-sm gray clearfix row" style="margin-bottom: 20px">
                                        <span style="width: 90px"></span>
                                        <span class="col-lg-7"></span>
                                        <ul class="pagination  col-md-4">
                                            <c:if test="${page==1}">
                                                <li class="previous"><a><i class="ti-arrow-left"></i> Prev</a></li>
                                                        </c:if>
                                                        <c:if test="${page!=1}">
                                                <li class="previous"><a href="../contentGroups/ContentGroupController?mod=paging&page=${page-1}"><i class="ti-arrow-left"></i> Prev</a></li>
                                                </c:if>
                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                    <c:if test="${i==page}">
                                                    <li id="${i}" class="active"><a href="../contentGroups/ContentGroupController?mod=paging&page=${i}">${i}</a></li>
                                                    </c:if>
                                                    <c:if test="${i!=page}">
                                                    <li id="${i}"><a href="../contentGroups/ContentGroupController?mod=paging&page=${i}">${i}</a></li>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${page==endPage}">
                                                <li class="previous"><a>Next <i class="ti-arrow-right"></i></li>
                                                </c:if>
                                                <c:if test="${page!=endPage}">
                                                <li class="next"><a href="../contentGroups/ContentGroupController?mod=paging&page=${page+1}">Next <i class="ti-arrow-right"></i></a></li>
                                                    </c:if>
                                        </ul>

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
