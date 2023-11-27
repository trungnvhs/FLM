<%-- 
    Document   : accountList.jsp
    Created on : May 23, 2023, 4:36:11 PM
    Author     : ThinkPad P50
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="dao.UserDAO" %>
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
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!--inner page banner--> 
                <div class="page-banner ovbl-dark" style="background-image:url(../../assets/images/banner/banner1.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Account List</h1>
                        </div>
                    </div>
                </div>
                <!--Breadcrumb row END--> 
                <!--inner page banner END--> 
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="db-breadcrumb">
                                <h1 class="breadcrumb-title">
                                </h1>
                                <div class="container" style=" margin-top: -50px; padding-left: 850px">
                                    <div class="row">
                                        <div class="col-12">
                                            <!--                                        Search-->

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table class="table no-wrap user-table mb-0" style="width: 100%">
                                    <div class="header__search-content" style="margin-bottom: 15px; margin-left: 20px">
                                        <form action="accountlist" method="post">
                                            <input style="border-radius: 10px" class="searchCurriculum" value="${txt}" type="text" name="txt" placeholder="Something">
                                            <input style="border-radius: 10px" class="btn" type="submit" value="Search" name="Search">
                                            <span style="margin-left: 1000px">

                                                <!--<input style="border-radius: 10px" class="btn" type="submit" value="Filter" name="btn_filter">-->
                                            </span>
                                        </form>
                                    </div>
                                    <thead>
                                        <tr>
                                            <th scope="col" class="border-0 text-uppercase font-medium">ID</th>
                                            <!--                                        // Screen Full Name-->
                                            <th scope="col" class="border-0 text-uppercase font-medium">Full Name</th>
                                            <!--                                            //Screen Email-->
                                            <th scope="col" class="border-0 text-uppercase font-medium">Email</th>

                                            <!--                                            //Screen Mobile-->
                                            <th scope="col" class="border-0 text-uppercase font-medium">Mobile</th>
                                            <!--                                            //Screen Job Title-->
                                            <th scope="col" class="border-0 text-uppercase font-medium">Job Titile</th>   
                                            <!--                                        // Screen Status-->
                                            <th scope="col" class="border-0 text-uppercase font-medium">Status</th>  

                                            <!--                                        //Screen User Role-->
                                            <th scope="col" class="border-0 text-uppercase font-medium">User Role</th>
                                            <!--                                        //Screen Manage-->
                                            <th scope="col" class="border-0 text-uppercase font-medium">Manage</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!--In ra list account-->
                                        <c:forEach items="${data}" var="item">
                                            <tr>
                                                <td class="pl-4">${item.getUser_id()}</td>
                                                <td>
                                                    <h5 class="font-medium mb-0">${item.getFull_name()}</h5>
                                                </td>
                                                <td>
                                                    <span class="text-muted">${item.getEmail()}</span>
                                                </td>
                                                <td>
                                                    <span class="text-muted">${item.getMobile()}</span>
                                                </td>
                                                <td>
                                                    <span class="text-muted">${item.getJob_title()}</span>
                                                </td>
                                                <td>
                                                    <span class="text-muted">
                                                        <c:if test="${item.getStatus() == '1'}">
                                                            <span class="text-muted">Active</span>                                                                            </c:if>
                                                        <c:if test="${item.getStatus()!= '1'}">
                                                            <span class="text-muted">DeActive</span>
                                                        </c:if>

                                                    </span>
                                                </td>
                                                <td>
                                                    <span class="text-muted">${item.getSetting_name()}</span>
                                                </td>
                                                <td>
                                                    <a href="detail?id=${item.getUser_id()}"><button class="btn btn-outline-info btn-circle btn-lg btn-circle ml-2"><i class="fa fa-edit"></i></button></a>
                                                        <c:if test="${item.getStatus() == '1'}">
                                                        <a href="accountlist?id=${item.getUser_id()}&mod=2"><button class="btn btn-outline-info btn-circle btn-lg btn-circle ml-2"><i class="fa fa-unlock"></i> </button></a>
                                                    </c:if>
                                                    <c:if test="${item.getStatus()!= '1'}">
                                                        <a href="accountlist?id=${item.getUser_id()}&mod=1"><button class="btn btn-outline-info btn-circle btn-lg btn-circle ml-2"><i class="fa fa-lock"></i> </button></a>
                                                    </c:if>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>  
                                    </tbody>
                                </table>


                                <div class="pagination-bx rounded-sm gray clearfix row" style="margin-bottom: 20px">
                                    <span style="width: 90px"></span>
                                    <span class="col-lg-7"></span>

                                    <ul class="pagination  col-md-4">
                                        <c:if test="${page==1}">
                                            <li class="previous"><a><i class="ti-arrow-left"></i> Prev</a></li>
                                                    </c:if>
                                                    <c:if test="${page!=1}">
                                            <li class="previous"><a href="accountlist?page=${page-1}"><i class="ti-arrow-left"></i> Prev</a></li>
                                            </c:if>
                                            <c:forEach begin="1" end="${endPage}" var="i">
                                                <c:if test="${i==page}">
                                                <li id="${i}" class="active"><a href="accountlist?page=${i}">${i}</a></li>
                                                </c:if>
                                                <c:if test="${i!=page}">
                                                <li id="${i}"><a href="accountlist?page=${i}">${i}</a></li>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${page==endPage}">
                                            <li class="previous"><a>Next <i class="ti-arrow-right"></i></li>
                                            </c:if>
                                            <c:if test="${page!=endPage}">
                                            <li class="next"><a href="accountlist?page=${page+1}">Next <i class="ti-arrow-right"></i></a></li>
                                                </c:if>
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <%@include file="../common/Footer.jsp" %>
            <!-- Footer END ==== -->
            <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
            <script type="text/javascript">
            </script>
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
