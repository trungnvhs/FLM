<%-- 
    Document   : settinglist
    Created on : 26-May-2023, 19:16:44
    Author     : MSI
--%>
<%@page import="dao.QuestionDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">


        <title>Question List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="../../assets/css/toast.css" rel="stylesheet">
        <link href="../../assets/css/styleFormImport.css" rel="stylesheet">
        <link rel="icon" href="../../assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="../../assets/images/favicon.png" />
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

            #striped-table tr:nth-child(even) {
                background-color: #f2f2f2; /* Màu xám nhạt hoặc màu nền của bạn */
            }

            /* Định nghĩa màu nền cho dòng lẻ */
            #striped-table tr:nth-child(odd) {
                background-color: #ffffff; /* Màu trắng hoặc màu nền của bạn */
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
        <%@include file="../common/SubHeaderSyllabus.jsp" %>

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="table-responsive">
                            <div class="profile-head">
                                <h3>Session</h3>
                            </div>
                            <div style="margin-top: 15px;margin-bottom: 15px">
                                <form action="SessionController" method="POST" >
                                    <input style="border-radius: 10px"  type="text" name="textsearch" >
                                    <input style="border-radius: 10px" class="btn" type="submit"   value="Search" name="btn_search">   
                                    <c:if test="${role=='Head' || role=='Staff'}">
                                        <input style="border-radius: 10px;text-align: right;margin-left: 500px" class="btn"  type="submit"   value="New Curriculum" name="btn_add">
                                    </c:if>
                                </form> 

                            </div>
                            <!--                            <c:if test="${role=='Designer'}">                                   
                                                            <button class="btn-secondry  button-md" onclick="showImport()">Import Questions</button>
                            </c:if>
                            -->                           
                            <table id="striped-table" class="table no-wrap user-table mb-0" style="height: 500px">
                                <thead>
                                    <tr>
                                        <th scope="col" class="border-0 text-uppercase font-medium">STT</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Session ID</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Session name</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Topic</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Learning Type</th>
                                            <c:if test="${role =='Staff' || role =='Head' }">
                                            <th scope="col" class="border-0 text-uppercase font-medium">Action</th>
                                            </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    int counter = 1; // Khởi tạo biến counter
                                    %>
                                    <c:forEach items="${dataSe}" var="i">
                                        <tr>
                                            <td class="p-3"><%= counter++ %></td>
                                            <td class="p-3">${i.getSession_id()}</td>
                                            <td class="p-3">${i.getName()}</td>
                                            <td class="p-3"><pre style="width: 100%">${i.getDetails()}</pre></td> 
                                            <td class="p-3">${i.getLearning_type()}</td>   

                                            <c:if test="${role =='Staff' || role =='Head' }">

                                                <td class="p-3"> 
                                                    <a href="SessionController?id=${i.getSession_id()}&mod=edit"><button class="btn" style="width: 30%; padding: 5px"><i class="fa fa-edit"></i> </button></a>
                                                    <a href="SessionController?id=${i.getSession_id()}&mod=edit"><button class="btn" style="width: 30%; padding: 5px"><i class="fa fa-eye"></i> </button></a>
                                                    <a href="SessionController?id=${i.getSession_id()}&sy_id=${i.getSyllabus_id()}&mod=delete&page=1" onclick="confirmDelete(); return false;"><button class="btn" style="width: 30%; padding: 5px"><i class="fa fa-trash-o"></i> </button></a>
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
                                <li class="previous"><a href="../session/SessionController?mod=1&&page=${page-1}"><i class="ti-arrow-left"></i> Prev</a></li>
                                </c:if>
                                <c:forEach begin="1" end="${endPage}" var="i">
                                    <c:if test="${i==page}">
                                    <li id="${i}" class="active"><a href="../session/SessionController?mod=1&&page=${i}">${i}</a></li>
                                    </c:if>
                                    <c:if test="${i!=page}">
                                    <li id="${i}"><a href="../session/SessionController?mod=1&&page=${i}">${i}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${page==endPage}">
                                <li class="previous"><a>Next <i class="ti-arrow-right"></i></li>
                                </c:if>
                                <c:if test="${page!=endPage}">
                                <li class="next"><a href="../session/SessionController?mod=1&&page=${page+1}"><i class="ti-arrow-right"></i>Next</a></li>
                                    </c:if>
                        </ul>

                    </div>          
                </div>
            </div>
        </div>
        <%@include file="../common/Footer.jsp" %>

        <form class="form-import-file" action="importQuestionController" method="post" enctype="multipart/form-data">
            <div class="form-import-container">
                <div class="form-import-close" onclick="closeImport()">
                    <i class="ti-close" ></i>
                </div>
                <header class="form-import-header">
                    <i class="fa fa-cloud-upload" style="margin-right: 16px"></i>
                    Import Questions
                </header>
                <div class="form-import-body">
                    <a href="importQuestionController" style="color: #4c1864"><b>Download Template</b></a>
                    <h4>
                        Choose a file excel
                    </h4>
                    <input type="file" name="file" id="file" value=""/>
                    <br>
                    <div style="text-align: center">
                        <input type="submit" name="btn-import" value="Save" class="btn button-md"/>
                    </div>

                </div>
            </div>
        </form>
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
        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                    function showImport() {
                        var showBtn = document.querySelector('.form-import-file');
                        showBtn.classList.add('form-import-open');
                    }
                    function closeImport() {
                        var showBtn = document.querySelector('.form-import-file');
                        showBtn.classList.remove('form-import-open');
                    }
        </script>  
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

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.all.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.css">
        <script type="text/javascript">

            function confirmDelete() {
                var confirmation = confirm("Bạn có chắc chắn muốn xóa dữ liệu này?");
                if (confirmation) {
                    // Nếu người dùng đồng ý xác nhận, tiếp tục thực hiện xóa
                    document.getElementById("delete_form").submit();
                } else {
                    // Nếu người dùng không đồng ý xác nhận, không làm gì cả
                }
            }
        </script>

    </body>
</html>
