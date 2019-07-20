<%-- 
    Document   : version
    Created on : 5 Jul, 2018, 11:35:42 AM
    Author     : sarker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <link rel="icon" type="image/x-icon" href="${STATIC_RES}/images/favicon.ico" />
        <title>Version History - <spring:message code="app_name" text="SmartBilling"/></title>

        <meta name="description" content="Restyling jQuery UI Widgets and Elements" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="${STATIC_RES}/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${STATIC_RES}/font-awesome/4.5.0/css/font-awesome.min.css" />
        
        <link rel="stylesheet" href="${STATIC_RES}/css/custom.css?v=${SCRIPT_VERSION}" />

        <!-- page specific plugin styles -->
        <link rel="stylesheet" href="${STATIC_RES}/css/jquery-ui.min.css" />

        <!-- text fonts -->
        <link rel="stylesheet" href="${STATIC_RES}/css/fonts.googleapis.com.css" />

        <!-- ace styles -->
        <link rel="stylesheet" href="${STATIC_RES}/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

        <!--[if lte IE 9]>
                <link rel="stylesheet" href="${STATIC_RES}/css/ace-part2.min.css" class="ace-main-stylesheet" />
        <![endif]-->
        <link rel="stylesheet" href="${STATIC_RES}/css/ace-skins.min.css" />
        <link rel="stylesheet" href="${STATIC_RES}/css/ace-rtl.min.css" />

        <!--[if lte IE 9]>
          <link rel="stylesheet" href="${STATIC_RES}/css/ace-ie.min.css" />
        <![endif]-->

        <!-- inline styles related to this page -->

        <!-- ace settings handler -->
        <script src="${STATIC_RES}/js/ace-extra.min.js"></script>

        <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

        <!--[if lte IE 8]>
        <script src="${STATIC_RES}/js/html5shiv.min.js"></script>
        <script src="${STATIC_RES}/js/respond.min.js"></script>
        <![endif]-->
        
        <style type="text/css">
            .vcontent{
                background: #f0f0f0;
            }
        </style>
    </head>

    <body class="login-layout light-login">
        <div class="navbar-container ace-save-state" id="navbar-container">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">

                    <div class="center">
                        <h1>
                            <i class="ace-icon fa fa-server primary-color"></i>
                            <span class="primary-color">AtmImagePullerWeb</span>
                            <span class="grey" id="id-text2"></span>
                        </h1>
                        <h4>Atm Image Puller Web</h4>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <h3 class="header blue lighter smaller">
                                <i class="ace-icon fa fa-list smaller-90"></i>
                                Version History
                            </h3>

                            <div id="accordion" class="accordion-style2">
                                <div class="group">
                                    <h3 class="accordion-header">v1.0.0 - 11 Jun 2019</h3>

                                    <div class="vcontent">
                                        <p>Welcome to our AtmImagePullerWeb.</b></p>

                                        <ol>
                                            <li>User Management</li>
                                            <li>Feature Management</li>
                                            <li>Role Management</li>
                                        </ol>
                                    </div>
                                </div>
                            </div><!-- #accordion -->
                        </div><!-- ./span -->

                    </div><!-- ./row -->

                </div>
            </div>
            <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
            </a>
        </div>




        <!--[if !IE]> -->
        <script src="${STATIC_RES}/js/jquery-2.1.4.min.js"></script>

        <!-- <![endif]-->

        <!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
        <script type="text/javascript">
            if ('ontouchstart' in document.documentElement)
                document.write("<script src='${STATIC_RES}/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
        </script>
        <script src="${STATIC_RES}/js/bootstrap.min.js"></script>

        <!-- page specific plugin scripts -->
        <script src="${STATIC_RES}/js/jquery-ui.min.js"></script>
        <script src="${STATIC_RES}/js/jquery.ui.touch-punch.min.js"></script>

        <!-- ace scripts -->
        <script src="${STATIC_RES}/js/ace-elements.min.js"></script>
        <script src="${STATIC_RES}/js/ace.min.js"></script>

        <!-- inline scripts related to this page -->
        <script type="text/javascript">
            jQuery(function ($) {

                //jquery accordion
                $("#accordion").accordion({
                    collapsible: true,
                    heightStyle: "content",
                    animate: 250,
                    header: ".accordion-header"
                }).sortable({
                    axis: "y",
                    handle: ".accordion-header",
                    stop: function (event, ui) {
                        // IE doesn't register the blur when sorting
                        // so trigger focusout handlers to remove .ui-state-focus
                        ui.item.children(".accordion-header").triggerHandler("focusout");
                    }
                });
                //jquery tabs
                $("#tabs").tabs();


                //progressbar
                $("#progressbar").progressbar({
                    value: 37,
                    create: function (event, ui) {
                        $(this).addClass('progress progress-striped active')
                                .children(0).addClass('progress-bar progress-bar-success');
                    }
                });


                //selectmenu
                $("#number").css('width', '200px')
                        .selectmenu({position: {my: "left bottom", at: "left top"}})

            });
        </script>
    </body>
</html>

