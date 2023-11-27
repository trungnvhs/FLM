<%-- 
    Document   : forgotPassword
    Created on : May 22, 2023, 11:46:02 PM
    Author     : Asus
--%>

<%@page import="dao.UserDAO" %>
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
        <title>EduChamp : Education HTML Template </title>

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
        <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-auth.js"></script>
    </head>

    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <div class="account-form">
                <div class="account-head" style="background-image:url(../../assets/images/background/bg2.jpg);">
                    <a href="/SE1709_G3_FLM/index.jsp"><b style="color: #ffffff;font-size: 100px">FLM<br><br> </b></a>
                </div>
                <div class="account-form-inner">
                    <div class="account-container">
                        <div class="heading-bx left">
                            <h2 class="title-head">Register Account With <span>Phone</span></h2>

                            <p style="color: #000000"><i class="fa fa-envelope-o" style="font-size:45px;color:#ffcc00"></i> register with Email<a href="register.jsp" > Click here</a><p>
                            <p>Login Your Account <a href="login.jsp">Click here</a></p>

                        </div>	
                        <form id="registerForm" class="contact-bx" action="registerPhoneController" method="POST" >
                            <div class="row placeani">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div style="margin-bottom: 5px" class="input-group">
                                            <label>Your Phone Number</label>
                                            <br>
                                            <input id="mobile" name="phone" type="text" required="" class="form-control" value="${phone}"> 
                                        </div>
                                        <div id="err-phone" style="color: red">${error3}</div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group row" style="margin: 15px 0">
                                            <label>Your Code</label><br>
                                            <input id="code" name="code" type="text" value="${code}" class="form-control col-md-7" required="">
                                            <input id="checkCode" type="text" style="display: none">
                                            <button id="btn-send-otp" style="margin-top: 5px; margin-left: 20px" class="btn button-md col-md-4" type="button" name ="op" onclick="sendOTP()" value="noclick">Send Code</button>
                                            <span id="recaptcha-container"></span>
                                        </div>
                                        <div id="err-otp" style="color: red"></div>
                                    </div>
                                </div>

                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label>New Password</label>
                                            <br>
                                            <input id="newPass" name="newPass"  value="${newPass}" type="password" class="form-control" required="">
                                        </div>
                                        <div id="err-pass" style="color: red"></div>
                                    </div>
                                </div><!-- comment -->

                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label>Re-New Password</label>
                                            <br>
                                            <input id="reNewPass" name="reNewPass" value="${reNewPass}" type="password"  class="form-control" required="">
                                        </div>
                                        <div id="err-repass" style="color: red"></div>
                                    </div>
                                </div><!-- comment -->

                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group" style="display: none">
                                            <label>avatar</label>
                                            <br>
                                            <input name="avatar" type="text" value="avatar.jpg" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 m-b30">
                                    <button name="op" type="button" class="btn button-md" onclick="checkRregister()" value="">Submit</button>
                                </div>
                            </div>
                        </form>
                        <div class="alart" id="alart">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- External JavaScripts -->
        <script src="../../assets/js/jquery.min.js"></script>
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
        <script src="../../assets/js/Register.js"></script>
    </body>

</html>
