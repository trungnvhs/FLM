<%-- 
    Document   : mappingplo_po
    Created on : Jun 24, 2023, 10:20:45 PM
    Author     : ThinkPad P50
--%>

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
            <!-- Start Page Content -->
            <div class="page-content bg-white">
                <!--Breadcrumb row--> 
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp">Home</a></li>
                            <li><a href="../po/polist">PO List</a></li>
                            <li>PLO-PO Mapping</li>
                        </ul>
                    </div>
                </div>
                <main class="page-content bg-light">

                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 mt-4">
                                <div class="table-responsive shadow rounded">
                                    <form action="poplo" method="post">
                                        <table  class="table table-center bg-white mb-0">
                                            <thead>
                                                <tr class="headtable" >
                                                    <td colspan="100%"  class="border-bottom p-9" style="background-color:#396cf0;text-align: center;height:60px; color: #ffffff; background-color: #4c1864">Mapping POs to PLOs</td>
                                                </tr>
                                                <tr>
                                                    <th class="border-bottom p-3" style="text-align: center" >PLO_PO</th>
                                                        <c:forEach var="ListPo" items="${ListPo}">
                                                        <th class="border-bottom p-3" style="min-width: 120px;text-align: center">${ListPo.name}</th>
                                                        </c:forEach>
                                                </tr>
                                            </thead>
                                            <tbody>


                                                <c:forEach var="plo" items="${ListPlo}">
                                                    <tr class="ListPLO ">
                                                        <td class="p-3" >${plo.name}</td>
                                                        <c:forEach var="po" items="${ListPo}">
                                                            <c:set var="para" value="mapping${plo.plo_id}_${po.po_id}" />
                                                            <c:set var="checked" value="${mappingStatus[para]}" />
                                                            <td style="text-align: center; ">
                                                                <c:if test="${role=='Staff' || role=='Head'}">
                                                        <li class="d-flex justify-content-center" style="text-align: center; ">
                                                            <div class="form-check form-switch">
                                                                <input class="form-check-input"  type="checkbox" name="mapping${plo.plo_id}_${po.po_id}" value="1" style="height: 25px; width: 25px"
                                                                       <c:if test="${checked}">checked</c:if>/>
                                                                </div>
                                                            </li>
                                                    </c:if>
                                                    <c:if test="${role!='Staff' && role!='Head'}">
                                                        <c:if test="${checked}"> ✓</c:if>
                                                    </c:if>
                                                </c:forEach>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <br>
                                        <input name="cu_id" value="${curID}" style="display: none">
                                        <!--<button  type="submit">Save Change</button>-->
                                        <c:if test="${role=='Staff' || role=='Head'}">
                                            <div>
                                                <input class="btn btn-outline-secondary"  type="submit" value="Save Change" name="save">
                                            </div>
                                        </c:if>
                                        <!--end row-->
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

            </div>

            <!-- Content END-->
            <!-- Footer ==== -->
        </main>
        <%@include file="../common/Footer.jsp" %>
        <!-- Footer END ==== -->
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
