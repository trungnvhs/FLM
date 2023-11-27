<%-- 
    Document   : forgotPassword
    Created on : May 22, 2023, 11:46:02 PM
    Author     : Asus
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
                            <h2 class="title-head">Forget <span>Password</span></h2>
                            <p>Login Your Account <a href="login.jsp">Click here</a></p>

                            <p style="color: #000000"><i class="fa fa-whatsapp" style="font-size:45px;color:#ffcc00"></i> Forgot Password with PhoneNumber<a href="forgotPasswordPhone.jsp" > Click here</a><p>

                        </div>	
                        <form class="contact-bx" action="forgotMailController" method="POST" >
                            <input name="noncode" type="text"  value="${emailContent}" style="display: none" >    
                            <div style="color: red"> ${error3}</div>
                            <div class="row placeani">
                                <div class="col-lg-12">
                                    <div class="form-group">

                                        <div style="margin-bottom: 5px" class="input-group ">
                                            <label>Your Email Address</label>
                                            <input name="email" type="email" required="" class="form-control" value="${email}">                                         
                                        </div>

                                        <input style="margin-top: 5px" class="btn button-md" type="submit" name ="op" value ="Send Reset Code">  
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label>Your Code</label>
                                            <br>
                                            <input name="code" type="text"  class="form-control">
                                        </div>    
                                    </div>
                                </div> 
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label>New Password</label>
                                            <br>
                                            <input name="newPass" type="text" class="form-control">
                                        </div>  
                                    </div>
                                </div> 

                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label>Re-New Password</label>
                                            <br>
                                            <input name="reNewPass" type="text"  class="form-control">
                                        </div>    
                                    </div>
                                </div> 



                                <div style="color: red"> ${error1}</div>
                                <div style="color: red"> ${error2}</div>

                                <div class="col-lg-12 m-b30">
                                    <button name="op" type="submit" value="Submit" class="btn button-md">Submit</button>
                                </div>
                            </div>
                        </form>
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
    </body>

</html>
