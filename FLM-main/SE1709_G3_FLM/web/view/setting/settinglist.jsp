<%-- 
    Document   : settinglist
    Created on : 26-May-2023, 19:16:44
    Author     : MSI
--%>

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

        </style>
    </head>
    <body>
        <%@include file="../common/Header.jsp" %>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title text-uppercase mb-0">Setting List</h5>
                        </div>
                        <div class="table-responsive">


                            <div>
                                <form action="settinglist" method="POST" >
                                    <select name="settingType" onchange="change()">
                                        <c:if test="${type==null}">
                                            <c:forEach  items="${dataType}" var="i">
                                                <option value="${i}">${i}
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${type!=null}">
                                                <c:forEach  items="${dataType}" var="i">
                                                    <c:if test="${type==i}">
                                                    <option value="${i}" selected="">${i} 
                                                    </c:if>
                                                    <c:if test="${type!=i}">
                                                    <option value="${i}">${i} 
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                    </select>
                                    <input class="searchSetting_name" type="text" name="name" 
                                           style="width: 220px"  placeholder="Enter setting name to search">
                                    <input class="searchButton" type="submit"   value="Search" name="btn_search">
                                </form> 
                            </div>

                            <table class="table no-wrap user-table mb-0" style="height: 500px">
                                <thead>
                                    <tr>
                                        <th scope="col" class="border-0 text-uppercase font-medium">ID</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Name</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Type</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Value</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Display Order</th>
                                        <th scope="col" class="border-0 text-uppercase font-medium">Action</th>
                                    </tr>

                                    <c:forEach items="${data}" var="i">
                                        <tr>
                                            <td class="p-3">${i.getSetting_id()}</td>
                                            <td class="p-3">${i.getSetting_name()}</td>
                                            <td class="p-3">${i.getSetting_type()}</td>
                                            <td class="p-3">${i.getSetting_value()}</td>
                                            <td class="p-3">${i.getSetting_order()}</td>
                                            <td class="text-end p-3">
                                                <a href="settingdetail?id=${i.getSetting_id()}" class="btn btn-icon btn-pills btn-soft-success" >Edit</a>

                                            </td>
                                        </tr> 
                                    </c:forEach>

                                </thead>

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../common/Footer.jsp" %>
        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

        </script>

    </body>
</html>
