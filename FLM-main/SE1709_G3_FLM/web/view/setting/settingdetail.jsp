<%-- 
    Document   : settingdetail
    Created on : 26-May-2023, 19:34:29
    Author     : MSI
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Setting</title>
    </head>
    <link rel="stylesheet" type="text/css" href="../../assets/css/assets.css">
    <link rel="stylesheet" type="text/css" href="../../assets/vendors/calendar/fullcalendar.css">

    <!-- TYPOGRAPHY ============================================= -->
    <link rel="stylesheet" type="text/css" href="../../assets/css/typography.css">

    <!-- SHORTCODES ============================================= -->
    <link rel="stylesheet" type="text/css" href="../../assets/css/shortcodes/shortcodes.css">

    <!-- STYLESHEETS ============================================= -->
    <link rel="stylesheet" type="text/css" href="../../assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="../../assets/css/dashboard.css">
    <link class="skin" rel="stylesheet" type="text/css" href="../../assets/css/color/color-1.css">
    <body>
        <%@include file="../common/Header.jsp" %>
        <div class="widget-box">
            <div class="wc-title">
                <h4>New Setting</h4>
            </div>
            <div class="widget-inner">
                <form class="edit-profile m-b30" action="settingdetail" method="POST">
                    <div class="row">
                        <div class="col-12">
                            <div class="ml-auto">
                                <h3>1. Setting info</h3>
                            </div>
                        </div>
                        <div class="form-group col-6">
                            <label class="col-form-label">Name</label>
                            <div>
                                <input class="form-control" type="text" value="${data.getSetting_name()}" name="name">
                            </div>
                        </div>
                                <input type="text" value="${data.getSetting_id()}" name="id" style="display:none ">

                        <div class="form-group col-6">
                            <div class="">
                                <label class="col-form-label" style="font-weight: bold;">Type</label>
                                <select class="form-control"name="type" id="type">
                                    <option>${data.getSetting_type()}</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-6">
                            <label class="col-form-label">Value</label>
                            <div>
                                <input class="form-control" type="text" value="${data.getSetting_value()}" name="value">
                            </div>
                        </div>
                        <div class="form-group col-6">
                            <label class="col-form-label">Display Order</label>
                            <div>
                                <input class="form-control" type="text" value="${data.getSetting_order()}" name="order">
                            </div>
                        </div>

                        <div class="seperator"></div>


                        <div class="form-group col-12">
                            <label class="col-form-label"> Description</label>
                            <div>
                                <textarea class="form-control"> </textarea>
                            </div>
                        </div>

                        <div class="col-12">
                            <button type="submit" class="btn-secondry add-item m-r5"><i class="fa fa-fw fa-plus-circle"></i>Submit</button>
                            <button type="reset" class="btn">Reset</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <%@include file="../common/Footer.jsp" %>
    </body>
</html>
