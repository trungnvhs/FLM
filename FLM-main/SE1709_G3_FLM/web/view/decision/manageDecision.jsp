<%-- 
    Document   : settinglist
    Created on : 26-May-2023, 19:16:44
    Author     : MSI
--%>
<%@page import="dao.DecisionDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">


        <title>Decision List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="../../assets/css/toast.css" rel="stylesheet">
        <link href="../../assets/css/styleFormImport.css" rel="stylesheet">
        <link rel="icon" href="../../assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="../../assets/images/favicon.png" />
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.all.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.css">
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
    </head>
    <body>
        <div class="toast-notifications">
            <i class="fa fa-info-circle"></i>
            <span>${mess}</span>
            <i class="fa-solid fa-xmark" onclick="removeToast(this.parentElement)"></i>
        </div>

        <%@include file="../common/Header.jsp" %>

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
        <div class="page-banner ovbl-dark" style="background-image:url(../../assets/images/banner/banner3.jpg);">
            <div class="container">
                <div class="page-banner-entry">
                    <h1 class="text-white">Decision List</h1>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="table-responsive">
                            <div style="margin-top: 15px;margin-bottom: 15px">
                                <form action="decisionController" method="POST" >
                                    <input style="border-radius: 10px"  type="text" name="textsearch" >

                                    <input style="border-radius: 10px" class="btn" type="submit"   value="Search" name="btn_search">
                                    <select style="margin-left: 200px ;border-radius: 10px" name="sort">
                                        <option value="decision_no">No</option>
                                        <option value="decision_name">Name</option>
                                    </select>
                                    <input style="border-radius: 10px" class="btn" type="submit"   value="Sort" name="btn_sort">

                                    <c:if test="${role=='Head' || 
                                                  role=='Staff'}">
                                          <svg style="margin-left: 250px" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                          <path  stroke-linecap="round" stroke-linejoin="round" d="M12 9v6m3-3H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
                                          </svg>
                                          <input style="border-radius: 10px" class="btn"  type="submit"   value="New Decision" name="btn_add">
                                    </c:if>
                                </form> 

                            </div>

                            <table class="table no-wrap user-table mb-0" style="height: 500px">
                                <thead>
                                    <tr>
                                        <th scope="col" class="border-0 text-uppercase font-medium"></th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">DecisionNo</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">DecisionName</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">CreateDate</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Creator</th>
                                            <c:if test="${role=='Head' || 
                                                          role=='Staff'}">
                                            <th scope="col" class="border-0 text-uppercase font-medium">Action</th>
                                            <th scope="col" class="border-0 text-uppercase font-medium"></th>
                                            </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    int counter = 1; // Khởi tạo biến counter
                                    %>
                                    <c:forEach items="${dataDe}" var="i">
                                        <tr>
                                            <td class="p-3"><%= counter++ %></td>
                                            <td class="p-3"><a href="decisionController?id=${i.getDecision_id()}&&mod=2" style="color: blue">${i.getDecision_no()}</a></td>
                                            <td class="p-3">${i.getDecision_name()}</td>
                                            <td class="p-3"><pre style="width: 100%">${i.getDecision_CreateDate()}</pre></td> 
                                            <td class="p-3">${DecisionDAO.getCreatorName(i.getCreator_id())}</td>
                                            <c:if test="${role=='Head' || role=='Staff' && i.getCreator_id() == account.getUser_id()}">
                                                <td>  
                                                    <a href="editDecisionController?id=${i.getDecision_id()}&&creatorid=${i.getCreator_id()}&&mod=2" style="color: blue">
                                                        <input style="border-radius: 10px" class="btn"  type="submit"   value="Edit" name="btn_edit">
                                                    </a> 
                                                </td>

                                                <td>
                                                    <a id="redirect_link" href="editDecisionController?mod=1&&id=${i.getDecision_id()}&&page=1&&creatorid=${i.getCreator_id()}" ></a>
                                                    <button style="margin-top: 5px" class="btn" onclick="confirmAndRedirect()"> <i  class="fa fa-trash-o"  ></i></button>
                                                </td>   
                                            </c:if>
                                        </tr> 
                                    </c:forEach>


                                </tbody>


                            </table>
                        </div>
                    </div>
                    <div class="pagination-bx rounded-sm gray clearfix row" style="margin-bottom: 20px">
                        <span style="width: 60px"></span>
                        <a class="col-md-2">
                            <!--<button name="submit" type="submit" value="Submit" class="btn button-md">Add New Subject</button>-->
                        </a>
                        <span style="width: 30px"></span>
                        <a class="col-md-3">
                            <!--<button name="submit" type="submit" value="Submit" class="btn-secondry  button-md">Import Subject</button>-->
                        </a>
                        <span class="col-md-2"></span>
                        <ul class="pagination  col-md-4">
                            <c:if test="${page==1}">
                                <li class="previous"><a><i class="ti-arrow-left"></i> Prev</a></li>
                                        </c:if>
                                        <c:if test="${page!=1}">
                                <li class="previous"><a href="../decision/decisionController?mod=1&&page=${page-1}"><i class="ti-arrow-left"></i> Prev</a></li>
                                </c:if>
                                <c:forEach begin="1" end="${endPage}" var="i">
                                    <c:if test="${i==page}">
                                    <li id="${i}" class="active"><a href="../decision/decisionController?mod=1&&page=${i}">${i}</a></li>
                                    </c:if>
                                    <c:if test="${i!=page}">
                                    <li id="${i}"><a href="../decision/decisionController?mod=1&&page=${i}">${i}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${page==endPage}">
                                <li class="previous"><a>Next <i class="ti-arrow-right"></i></li>
                                </c:if>
                                <c:if test="${page!=endPage}">
                                <li class="next"><a href="../decision/decisionController?mod=1&&page=${page+1}">Next <i class="ti-arrow-right"></i></a></li>
                                    </c:if>
                        </ul>

                    </div>          
                </div>
            </div>
        </div>
        <%@include file="../common/Footer.jsp" %>
        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">
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
    </body>
</html>
