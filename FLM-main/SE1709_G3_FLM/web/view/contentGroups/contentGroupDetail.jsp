<%-- 
    Document   : contentGroupDetail
    Created on : Jun 24, 2023, 5:20:38 PM
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
        <link href="../../assets/css/select2.min.css" rel="stylesheet" />
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
                                <%--<c:if>--%>
                            <li>Content Group Detail</li>
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
                            <div class="col-lg-10 col-md-10 col-sm-12 m-b30">
                                <form class="profile-content-bx" action="ContentGroupController" method="post">
                                    <input type="text" name="mod" value="subGroup" style="display: none"/>
                                    <input type="text" name="id" value="${group.getGroup_id()}" style="display: none"/>
                                    <div class="tab-pane" id="edit-profile">
                                        <div class="profile-head">
                                            <h3>View Content Group Detail</h3>
                                        </div>
                                        <div class="edit-profile" >
                                            <input type="text" name="mod" value="add" style="display: none"/>
                                            <div class="form-group row">
                                                <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label" style="text-align: right">Content Group Name<span style="color: red">*</span></label>
                                                <div class="col-12 col-sm-8 col-md-8 col-lg-8">
                                                    <input id="name" name="name" class="form-control" type="text"  required="" value="${group.getName()}" maxlength="150" onchange="showBtnSave()"></input>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label" style="text-align: right">Display Order<span style="color: red">*</span></label>
                                                <div class="col-12 col-sm-2 col-md-2 col-lg-2">
                                                    <input id="disOrder" name="disOrder" class="form-control" type="text"  required="" value="${group.getDisplay_order()}" maxlength="2" onchange="showBtnSave()"></input>
                                                </div>
                                            </div>
                                            <div class="seperator row">
                                                <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label"></label>
                                                <input id="BtnSave" type="submit" class="btn button-md"
                                                       name="btn-save" value="Save Change" style="display: none; margin-left: 12px"/>
                                            </div>
                                        </div>
                                        <div class="profile-head row">
                                            <h3 class="col-md-6">List Subject Content Group</h3>
                                            <c:if test="${role=='Staff' || role=='Head'}">
                                            <div class="col-md-3">
                                                <select class="form-control time-during select2input" name="subject">
                                                    <option value="">Select Subject</option>
                                                    <c:forEach items="${dataSubNoGroup}" var="i">
                                                        <option value="${i.getSubject_id()}">${i.getCode()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <input type="submit" class="btn button-md col-md-2" name="btn-add-sub" style="padding: 11px 16px;" value="Add Subject"/>
                                            </c:if>
                                        </div>
                                        <div class="edit-profile" >
                                            <div class="pricingtable-wrapper">
                                                <div class="pricingtable-inner">
                                                    <table class="table no-wrap user-table mb-0 pricingtable-main">
                                                        <thead class="pricingtable-title">
                                                            <tr class="row">
                                                                <th class="col-md-1">ID</th>
                                                                <th class="col-md-3">Subject Code</th>
                                                                <th class="col-md-6">Subject Name</th>
                                                                <th class="col-md-2"><c:if test="${role=='Staff' || role=='Head'}">Action</c:if></th>
                                                            </tr>
                                                        </thead>

                                                        <tbody class="pricingtable-features">
                                                            <c:forEach items="${dataSubjectContentGroup}" var="i">
                                                                <tr class="row">
                                                                    <td class="col-md-1">${i.getSubject_id()}</td>
                                                                    <th class="col-md-3">${i.getCode()}</th>
                                                                    <th class="col-md-6">${i.getName()}</th>
                                                                    <td class="col-md-2" style="align-content:center">
                                                                        <c:if test="${role=='Staff' || role=='Head'}">
                                                                        <button type="submit" name="btn-del-sub" value="${i.getSubject_id()}" class="btn btn-outline-info btn-circle btn-lg btn-circle"><i class="fa fa-trash-o"></i> </button>
                                                                        </c:if>
                                                                    </td>
                                                                </tr> 
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form> 
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
        <!-- Select2 -->
        <script src="../../assets/js/select2.min.js"></script>
        <script src="../../assets/js/select2.init.js"></script>
        <script>
            if(${role!='Staff' && role!='Head'}){
                document.getElementById('name').disabled='true';
                document.getElementById('disOrder').disabled='true';
            }
            function showBtnSave(){
                document.getElementById('BtnSave').style.display = 'inline-block';
            }
        </script>
    </body>
</html>
