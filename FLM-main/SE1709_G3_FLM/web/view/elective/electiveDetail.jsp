<%-- 
    Document   : settinglist
    Created on : 26-May-2023, 19:16:44
    Author     : MSI
--%>
<%@page import="dao.UserDAO" %>

<%@page import="dao.CurriculumDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">


        <title>Setting List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            body{
                background: #edf1f5;
                margin-top:20px;
            }
            .card {
                position: relative;
                display: flex;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 0 solid transparent;
                border-radius: 0;
            }
            .btn-circle.btn-lg, .btn-group-lg>.btn-circle.btn {
                width: 50px;
                height: 50px;
                padding: 14px 15px;
                font-size: 18px;
                line-height: 23px;
            }
            .text-muted {
                color: #8898aa!important;
            }
            [type=button]:not(:disabled), [type=reset]:not(:disabled), [type=submit]:not(:disabled), button:not(:disabled) {
                cursor: pointer;
            }
            .btn-circle {
                border-radius: 100%;
                width: 40px;
                height: 40px;
                padding: 10px;
            }
            .user-table tbody tr .category-select {
                max-width: 150px;
                border-radius: 20px;
            }
            svg {
                width: 40px;
                height: 40px;
                margin-left: 450px;
            }
            .border-0{
                background-color: #330066;
                color: #ffffff;
            }
            pre {
                white-space: pre-wrap;
                word-wrap: break-word;
                font-family: Rubik!important;
                font-size: 16px!important;
            }
        </style>
        <link href="../../assets/css/toast.css" rel="stylesheet">

    </head>
    <body>
        <div class="toast-notifications">
            <i class="fa fa-info-circle"></i>
            <span>${mess}</span>
            <i class="fa-solid fa-xmark" onclick="removeToast(this.parentElement)"></i>
        </div>
        ${error} 
        <%@include file="../common/Header.jsp" %>
        <%@include file="../common/SubHeaderCurriculum.jsp" %>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
        <div class="breadcrumb-row">
            <div class="container">
                <ul class="list-inline">
                    <li><a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp">Home</a></li>
                    <li><a href="/SE1709_G3_FLM/view/curriculum/curriculumController?mod=1&&page=1">Curriculum List</a></li>
                    <li><a href="/SE1709_G3_FLM/view/elective/ElectiveController?cu_id=${curID}">Elective</a></li>
                    <li><a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp">Detail Elective</a></li>

                </ul>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="card">
                    <div style="padding: 20px">

                        <h4 style="border-bottom: 2px solid #8898aa;">
                            <b>View Elective Detail</b>
                        </h4>

                        <h5 style="border-bottom: 2px solid #8898aa;">

                            Elective Subject: ${elec.getName()} - ${elec.getCombo_subject()} - ${elec.getCurriculum_id()}
                            <br> <br>
                            Name: ${elec.getCombo_subject()} - ${elec.getCurriculum_id()}
                            <br> <br>
                            Note: 
                        </h5>

<!--                        <form action="ElectiveDetailController" method="post">
                            <c:if test="${role=='Staff' || role=='Head'}">
                                <div  style="text-align: right; margin-right: 20px">
                                    <button name="add1" type="submit" value="Submit" class="btn button-md">New Elective Details</button>
                                    <input type="hidden" name="elective_name" value="${elec.getName()}" />
                                    <br> comment 

                                    <p style="color: red">${error10}<p>
                                    <p style="color: red">${error1}<p>


                                </div>
                            </c:if>
                        </form>-->
                    </div>
                    <h4 style="padding-left: 20px">
                        <b>List Subject Elective</b>
                    </h4>
                    <div class="table-responsive">


                        <table class="table no-wrap user-table mb-0" style="height: 500px">
                            <thead>
                                <tr style="text-align: center">

                                    <th scope="col" class="border-0 text-uppercase font-medium" style="width: 200px">ID</th>
                                    <th scope="col" class="border-0 text-uppercase font-medium" style="width: 350px">Subject Code</th>
                                    <th scope="col" class="border-0 text-uppercase font-medium" style="width: 450px">Subject Name</th>
                                    <th scope="col" class="border-0 text-uppercase font-medium" style="width: 150px"></th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${dataelec}" var="i">
                                    <tr style="text-align: center;tab-size: 20px">

                                        <td class="p-3">${i.getGroup_id()}</td>
                                        <td class="p-3">${i.getCombo_subject()}</td>
                                        <td class="p-3">${i.getCurriculum_id()}</td>
                                        <c:if test="${role=='Staff' || role=='Head'}">
                                            <td class="col-md-2" style="align-content:center">
                                                <a id="redirect_link"  style="display: none;" href="ElectiveDetailController?subject_id=${i.getGroup_id()}&group_id=${groupid}&mod=delete" ></a>
                                                <button id="delete_form" onclick="confirmAndRedirect();" class="btn"><i class="fa fa-trash-o"></i> </button>
                                            </td>
                                        </c:if>

                                    </tr> 
                                </c:forEach>


                            </tbody>


                        </table>
                    </div>
                </div>

            </div>

        </div>
        <%@include file="../common/Footer.jsp" %>
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
        
        <!--            Toast comfirm xóa-->
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.all.min.js"></script>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.css">
            <script type="text/javascript">
                function confirmAndRedirect() {
                    Swal.fire({
                        title: 'Xác nhận',
                        text: 'Bạn có chắc chắn muốn xóa dữ liệu này?',
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Xác nhận',
                        cancelButtonText: 'Hủy bỏ',
                    }).then((result) => {
                        if (result.isConfirmed) {
                            // Nếu người dùng đồng ý xác nhận, tiếp tục chuyển hướng
                            const redirectLink = document.getElementById('redirect_link');
                            redirectLink.click();
                        } else {
                            // Nếu người dùng không đồng ý xác nhận, không làm gì cả
                        }
                    });
                }
            </script>
<!--            end toast confirm xóa-->
    </body>
</html>
