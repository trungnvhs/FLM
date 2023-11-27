<%-- 
    Document   : homePageGuest
    Created on : May 22, 2023, 9:47:40 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="../../assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="../../assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>FLM : FPT Education Learning Materials </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="../../assets/css/assets.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="../../assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="../../assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="../../assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="../../assets/css/color/color-1.css">
        <link href="../../assets/css/toast.css" rel="stylesheet">
    </head>
    <body>
        ${mess}
        <%@include file="../common/Header.jsp" %>
        <div class="page-content bg-white">
            <!-- inner page banner -->
            <div class="page-banner ovbl-dark" style="background-image:url(../../assets/images/banner/banner3.jpg);">
                <div class="container">
                    <div class="page-banner-entry">
                        <c:if test="${role=='Admin'}">
                            <h1 class="text-white">Admin Features</h1>
                        </c:if>
                        <c:if test="${role=='Student'}">
                            <h1 class="text-white">Student Features</h1>
                        </c:if>
                        <c:if test="${role=='Teacher'}">
                            <h1 class="text-white">Teacher Features</h1>
                        </c:if>
                        <c:if test="${role=='Head'}">
                            <h1 class="text-white">CRDD Head Features</h1>
                        </c:if>
                        <c:if test="${role=='Staff'}">
                            <h1 class="text-white">CRDD Staff Features</h1>
                        </c:if>
                        <c:if test="${role=='Reviewer'}">
                            <h1 class="text-white">Reviewer Features</h1>
                        </c:if>
                        <c:if test="${role=='Designer'}">
                            <h1 class="text-white">Designer Features</h1>
                        </c:if>
                        <c:if test="${role=='Guest'}">
                            <h1 class="text-white">Guest Features</h1>
                        </c:if>

                    </div>
                </div>
            </div>
            <!-- Breadcrumb row -->
            <div class="breadcrumb-row">
                <div class="container">
                    <ul class="list-inline">
                        <c:if test="${role=='Admin'}">
                            <li>Admin Features</li>
                            </c:if>
                            <c:if test="${role=='Student'}">
                            <li>Student Features</li>
                            </c:if>
                            <c:if test="${role=='Teacher'}">
                            <li>Teacher Features</li>
                            </c:if>
                            <c:if test="${role=='Head'}">
                            <li>CRDD Head Features</li>
                            </c:if>
                            <c:if test="${role=='Staff'}">
                            <li>CRDD Staff Features</li>
                            </c:if>
                            <c:if test="${role=='Reviewer'}">
                            <li>Reviewer Features</li>
                            </c:if>
                            <c:if test="${role=='Designer'}">
                            <li>Designer Features</li>
                            </c:if>
                            <c:if test="${role=='Guest'
                                          || role==null}">
                            <li>Guest Features</li>
                            </c:if>

                    </ul>
                </div>
            </div>
            <!-- Breadcrumb row END -->
            <!-- inner page banner END -->
            <div class="content-block">
                <!-- About Us -->
                <div class="section-area section-sp1">
                    <div class="container">
                        <div class="row">
                            <div >
                                <div class="row" style="padding-left: 250px;padding-bottom: 100px">
                                    <c:if test="${role=='Guest'
                                                  || role=='Student'
                                                  || role=='Teacher'
                                                  || role==null}">
                                          <div class="col-md-12" style="padding-bottom: 50px">
                                              <div class="cours-bx">
                                                  <div class="info-bx text-center">
                                                      <br><br>
                                                      <h2><a href="../curriculum/curriculumController?mod=1&&page=1">View Curriculum</a></h2>
                                                  </div>
                                              </div>
                                          </div>
                                    </c:if>
                                    <c:if test="${role=='Student'
                                                  || role=='Teacher'}">
                                          <div class="col-md-12" style="padding-bottom: 50px">
                                              <div class="cours-bx">
                                                  <div class="info-bx text-center">
                                                      <br><br>
                                                      <h2><a href="../syllabus/SyllabusController?mod=1&&page=1">View Training Syllabus</a></h2>
                                                  </div>
                                              </div>
                                          </div>
                                    </c:if>
                                    <c:if test="${role=='Guest'
                                                  || role=='Student'
                                                  || role=='Teacher'
                                                  || role==null}">
                                          <div class="col-md-12" style="padding-bottom: 50px">
                                              <div class="cours-bx">
                                                  <div class="info-bx text-center">
                                                      <br><br>
                                                      <h2><a href="../subject/subjectpre">Subject Predeccessors</a></h2>
                                                  </div>
                                              </div>
                                          </div>
                                          <div class="col-md-12" style="padding-bottom: 50px">
                                              <div class="cours-bx">
                                                  <div class="info-bx text-center">
                                                      <br><br>
                                                      <h2><a href="../subject/subjectsucc">Subject Successors</a></h2>
                                                  </div>
                                              </div>
                                          </div>
                                    </c:if>
                                    <c:if test="${role=='Admin'}">
                                        <div class="col-md-12" style="padding-bottom: 50px">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="../setting/settinglist">System Settings</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="#">Role Premissions</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12" style="padding-top: 50px">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="../userAccess/accountlist">User Management</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                        
                                    </c:if>
                                    <c:if test="${role=='Reviewer'}">
                                        <div class="col-md-12" style="padding-bottom: 50px">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="../syllabus/SyllabusController?mod=1&&page=1">Review Syllabus</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${role=='Designer'}">
                                        <div class="col-md-12" style="padding-bottom: 50px">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="../syllabus/SyllabusController?mod=1&&page=1">Design Syllabus</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${role=='Staff' || role=='Head'}">
                                        <div class="col-md-12" style="padding-bottom: 50px">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="../curriculum/curriculumController?mod=1&&page=1">Manage Curricula</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12" style="padding-bottom: 50px">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="../subject/SubjectController?mod=1&&page=1">Manage Subjects</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12" style="padding-bottom: 50px">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="../syllabus/SyllabusController?mod=1&&page=1">Manage Syllabi</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12" style="padding-bottom: 50px">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="../decision/decisionController?mod=1&&page=1">Material Decisions</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                       
                                    </c:if>
                                    <c:if test="${role=='Head'}">
                                        <div class="col-md-12" style="padding-bottom: 50px">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="">Assign, Approve, Disapprove Curricula</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12" style="padding-bottom: 50px">
                                            <div class="cours-bx">
                                                <div class="info-bx text-center">
                                                    <br><br>
                                                    <h2><a href="" >Approve, Disapprove Syllabi</a></h2>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- contact area END -->
            </div>
        </div>
        <%@include file="../common/Footer.jsp" %>
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
            var mess = '<%= request.getAttribute("mess") %>';
            if (mess !== "") {
                showToast();
                setTimeout(closeToast, 5000);
            }
        </script>
    </body>
</html>
