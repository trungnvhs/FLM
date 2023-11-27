<%-- 
    Document   : Login
    Created on : May 17, 2023, 4:37:48 PM
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
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

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
        <link class="skin" rel="stylesheet" type="text/css" href="../assets/css/color/color-1.css">
        <link href="../../assets/css/toast.css" rel="stylesheet">
    </head>
    <body>
        <div class="toast-notifications">
            <i class="fa fa-info-circle"></i>
            <span>${mess}</span>
            <i class="fa-solid fa-xmark" onclick="removeToast(this.parentElement)"></i>
        </div>
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <div class="account-form">
                <div class="account-head" style="background-image:url(../../assets/images/background/bg2.jpg);">
                    <a href="/SE1709_G3_FLM/index.jsp"><b style="color: #ffffff;font-size: 100px">FLM<br><br> </b></a>
                </div>
                <div class="account-form-inner">
                    <div class="account-container">
                        <div class="heading-bx left">
                            <h2 class="title-head">Login to your <span>Account</span></h2>
                            <p>Don't have an account? <a href="register.jsp">Create one here</a></p>
                        </div>	
                        <form class="contact-bx" action="loginController" method="POST">
                            

                            <div class="row placeani">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label>Your username,email or phone*</label><br>
                                            <input name="account" type="text" required="" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                            <label>Your Password</label><br>
                                            <input name="password" type="password" class="form-control" required="">
                                        </div>
                                    </div>
                                </div>
                                <div style="color: red"> ${error}</div>
                                <div class="col-lg-12">
                                    <div class="form-group form-forget">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="customControlAutosizing">
                                        </div>
                                        <a href="forgotPassword.jsp" class="ml-auto">Forgot Password?</a>
                                    </div>
                                </div>
                                <div class="col-lg-12 m-b30">
                                    <button name="submit" type="submit" value="Submit" class="btn button-md">Login</button>
                                </div>
                                <div class="col-lg-12">
                                    <h6>Login with Social media</h6>
                                    <div class="d-flex">

                                        <a class="btn flex-fill m-l5 google-plus" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/SE1709_G3_FLM/view/userAccess/loginGoogleController&response_type=code&client_id=971248339336-fdinpp124g6dev8pls45b88plra3q3mn.apps.googleusercontent.com&approval_prompt=force"><i class="fa fa-google-plus"></i>Google Plus</a>
                                    </div>
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
    </body>
</html>
