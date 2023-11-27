<%-- 
    Document   : subjectPloMapping
    Created on : Jun 23, 2023, 5:35:30 PM
    Author     : Trung Quan
--%>
<%@page import="dao.SubjectDAO" %>
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
            .pricingtable-features-sub td{
                border: #E2E2E2 1px solid;
            }
        </style>
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
                                    <form action="subPloMapController" method="post">
                                        <div class="card-body row" style="margin-left: 30px">
                                            <h4 class="card-title text-uppercase mb-0 col-lg-9" style="line-height: 52.85px">Subject PLOs Mapping</h4>
                                            <a class=" col-lg-3 btn button-md" href="/SE1709_G3_FLM/view/contentGroups/ContentGroupController?mod=paging&page=1">
                                                View Content Groups
                                            </a>
                                        </div>
                                        <div>
                                            <div class="row" style="margin-bottom: 20px"></div>
                                            <div class="pricingtable-wrapper">
                                                <div class="pricingtable-inner">
                                                    <table class="table no-wrap user-table mb-0 pricingtable-main" style="box-shadow: #cccccc 3px">
                                                        <thead class="pricingtable-title">
                                                            <tr>
                                                                <th>Subject Code</th>
                                                                    <c:forEach var="ItemPlo" items="${dataPLO}">
                                                                    <th>${ItemPlo.getName()}</th>
                                                                    </c:forEach>
                                                            </tr>
                                                        </thead>

                                                        <tbody class="pricingtable-features">
                                                            <c:forEach items="${dataContentGroup}" var="itemGroup">
                                                                <tr class="pricingtable-features-sub">
                                                                    <td style="color: red" colspan="100%">${itemGroup.getName()}</td>
                                                                </tr> 
                                                                <c:forEach items="${SubjectDAO.getListSubContent(itemGroup.getGroup_id(), curID)}" var="itemSub">
                                                                    <tr class="pricingtable-features-sub">
                                                                        <td>${itemSub.getCode()}</td>
                                                                        <c:forEach var="ItemPlo" items="${dataPLO}">
                                                                            <c:set var="para" value="mapping${itemSub.getSubject_id()}_${ItemPlo.getPlo_id()}" />
                                                                            <c:set var="checked" value="${mappingStatus[para]}" />
                                                                            <td>
                                                                                <c:if test="${role=='Staff' || role=='Head'}">
                                                                                    <input class="form-check-input"  type="checkbox" name="${para}" value="0" style="height: 16px; width: 16px"
                                                                                           <c:if test="${checked}">checked</c:if>/>
                                                                                </c:if>
                                                                                <c:if test="${role!='Staff' && role!='Head'}">
                                                                                    <c:if test="${checked}"><b style="font-size: 20px;"> ✓</b></c:if>
                                                                                </c:if>
                                                                            </td>
                                                                        </c:forEach>
                                                                    </tr>
                                                                </c:forEach>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div style="height: 50px"></div>
                                        <div style="text-align: center; margin-bottom: 30px">
                                            <input type="submit" id="btn-subPlo-map" class="btn button-md" name="btn-save" value="Save Change">
                                        </div>
                                    </form>
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
            <button class="back-to-top fa fa-chevron-up" ></button>
        </div>
        <script>
            var inputBtn = document.getElementById("btn-subPlo-map");
            if (${role!='Staff' && role!='Head'}) {
                inputBtn.style.display = 'none';
            } else {
                inputBtn.style.width = '200px';
            }
        </script>
    </body>
</html>
