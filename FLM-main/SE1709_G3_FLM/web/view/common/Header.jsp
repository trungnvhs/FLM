<%-- 
    Document   : headerNew
    Created on : May 31, 2023, 4:34:34 PM
    Author     : Trung Quan
--%>

<%@page import="dao.RoleDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../../assets/css/dashboard.css">
        <link class="skin" rel="stylesheet" type="text/css" href="../../assets/css/color/color-1.css">
        <style>
            #link-avatar{
                padding-left: 0;
                padding-right: 0;
                padding-top: 0;
                width: 50px;
                height: 70px;
                margin-top: 15px;
                box-sizing: border-box;
            }
            #avatarImage{
                width: 50px;
                height: 50px;
                border-radius: 50%;
            }
        </style>
    </head>
    <body>
        <header class="header rs-nav">
            <div class="sticky-header navbar-expand-lg">
                <div class="menu-bar clearfix">
                    <div style="border-bottom: rgba(0, 0, 0, 0.3) 1px dashed; margin-bottom: 5px; width: 100%">
                        <div class="container clearfix">
                            <!-- Header Logo ==== -->
                            <div class="menu-logo">
                                <a href="/SE1709_G3_FLM/view/userAccess/homePage.jsp"><b style="color: #004085;font-size: 50px">FLM</b></a>
                            </div>
                            <!-- Mobile Nav Button ==== -->
                            <button class="navbar-toggler collapsed menuicon justify-content-end" type="button" data-toggle="collapse" data-target="#menuDropdown" aria-controls="menuDropdown" aria-expanded="false" aria-label="Toggle navigation">
                                <span></span>
                                <span></span>
                                <span></span>
                            </button>
                            <!-- Author Nav ==== -->
                            <div class="secondary-menu" style="padding: 0">
                                <c:if test="${account!=null}">
                                    <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown">
                                        <ul class="nav navbar-nav">	
                                            <li class="active">
                                                <a class="link-dropdown" id="link-avatar"><img id="avatarImage" src="../../assets/images/avatar/${account.getAvatar()}" alt="Avatar"></a>
                                                <ul class="sub-menu">
                                                    <li><a href="/SE1709_G3_FLM/view/userAccess/EditProfileController">Profile</a></li>
                                                    <li><a href="/SE1709_G3_FLM/view/userAccess/ChangePassword">Change Password</a></li>
                                                    <li ><a href="/SE1709_G3_FLM/view/userAccess/logoutController">Log out</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>
                                </c:if>
                                <c:if test="${account==null}">
                                    <div class="secondary-inner" style="padding-top: 20px">
                                        <ul> 
                                            <li><a href="/SE1709_G3_FLM/view/userAccess/login.jsp">
                                                    <input type="submit" style="padding: 10px;padding-left: 20px;padding-right: 20px; font-weight: bold;border: none; background-color: #330099;color: #ffffff " value="Login"  /></a></li>
                                            <!-- Search Button ==== -->
                                            <li><a href="/SE1709_G3_FLM/view/userAccess/register.jsp">
                                                    <input type="submit" style="padding: 10px;padding-left: 20px;padding-right: 20px; font-weight: bold;border: none; background-color: #330099;color: #ffffff " value="Register" /></a></li>
                                        </ul>
                                    </div>
                                </c:if>


                            </div>


                            <!-- Search Box ==== -->
                            <div class="nav-search-bar">
                                <form action="#">
                                    <input name="search" value="" type="text" class="form-control" placeholder="Type to search">
                                    <span><i class="ti-search"></i></span>
                                </form>
                                <span id="search-remove"><i class="ti-close"></i></span>
                            </div>
                            <!-- Navigation Menu ==== -->
                            <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown">
                                <div class="menu-logo">
                                    <a href="##"><b style="color: #004085;font-size: 50px">FLM</b></a>
                                </div>
                                <ul class="nav navbar-nav">	
                                    <li id="cur" onclick="activeLink('cur')"><a href="/SE1709_G3_FLM/view/curriculum/curriculumController?mod=1&&page=1"> Curriculum <!--<i class="fa fa-chevron-down"></i>--></a>
                                    </li>
                                    <c:if test="${role!='Designer' && 
                                                  role!='Reviewer' && 
                                                  role!='Guest' && 
                                                  role!=null }">
                                          <li id="syl" onclick="activeLink('syl')"><a href="../syllabus/SyllabusController?mod=1&&page=1">Syllabus <!--<i class="fa fa-chevron-down"></i>--></a>
                                          </li>
                                    </c:if>
                                    <li id="subPre" onclick="activeLink('subPre')"><a href="/SE1709_G3_FLM/view/subject/subjectpre">Subject Predecessors </a></li>
                                    <li id="subSuc" onclick="activeLink('subSuc')"><a href="/SE1709_G3_FLM/view/subject/subjectsucc">Subject Sucessors </a></li>
                                        <c:if test="${role!='Teacher' && 
                                                      role!='Student' && 
                                                      role!='Guest' && 
                                                      role!=null }">
                                        <li id="dshb"><a class="link-dropdown">Dashboard <i class="fa fa-chevron-down"></i></a>
                                                <c:if test="${role=='Head' || 
                                                              role=='Staff'}">
                                                <ul class="sub-menu">
                                                    <li><a href="/SE1709_G3_FLM/view/curriculum/curriculumController?mod=1&&page=1">Training Curriculums</a></li>
                                                    <li><a href="/SE1709_G3_FLM/view/subject/SubjectController?mod=1&&page=1">Subject Management</a></li>
                                                    <li><a href="#">Material Decisions</a></li>
                                                </ul>
                                            </c:if>

                                            <c:if test="${role=='Admin'}">
                                                <ul class="sub-menu">
                                                    <li><a href="/SE1709_G3_FLM/view/setting/settinglist">System Settings</a></li>
                                                    <li><a href="#">Role Permissions</a></li>
                                                    <li><a href="/SE1709_G3_FLM/view/userAccess/accountlist">User Management</a></li>
                                                </ul>
                                            </c:if>
                                            <c:if test="${role=='Reviewer'
                                                          || role=='Designer'}">
                                                  <ul class="sub-menu">
                                                      <li><a href="../syllabus/SyllabusController?mod=1&&page=1">Syllabus Designing</a></li>
                                                  </ul>
                                            </c:if>
                                        </li>
                                    </c:if>
                                </ul>

                            </div>
                            <!-- Navigation Menu END ==== -->
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </body>
</html>
