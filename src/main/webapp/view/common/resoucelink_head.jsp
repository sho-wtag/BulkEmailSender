<%-- 
    Document   : resoucelink_head
    Created on : May 22, 2018, 11:21:32 AM
    Author     : sarker
--%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="utf-8" />
    <title>${APP_NAME}</title>

    <link rel="icon" type="image/x-icon" href="${STATIC_RES}/images/favicon.ico" />
    <link rel="icon" type="image/png" sizes="32x32" href="${STATIC_RES}/images/icon.png">
    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <script type="text/javascript">
        var _baseurl_ = "${BASE_URL}";
        var APP = "${APP}";
        var _angular_ = "${ANGULAR}";
        var _NG_SRC_ = "${NG_SRC}";
        var _ROLE_NAME_ = "${ROLE_NAME}";
        var _ROLE_CODE_ = "${ROLE_CODE}";
        var _STATIC_RES_ = "${STATIC_RES}";
        var _USER_ID_ = "${USER_ID}";
        
        var _BRANCH_ID_ = "${BRANCH_ID}";
        var _COMPANY_ID_ = "${COMPANY_ID}";
        var isLoadingShown = false;
        var profile_pic_uri = null;
        var TOAST_TIMEOUT = 5000;
        
    </script>
    <script src="${STATIC_RES}/js/custom.js"></script>
    <script src="${STATIC_RES}/angular/app.constant.js"></script>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${STATIC_RES}/css/bootstrap.min.css?v=${SCRIPT_VERSION}" />
    <link rel="stylesheet" href="${STATIC_RES}/css/bootstrap-datetimepicker.min.css?v=${SCRIPT_VERSION}" />
    <link rel="stylesheet" href="${STATIC_RES}/font-awesome/4.5.0/css/font-awesome.min.css?v=${SCRIPT_VERSION}" />
    
    <link rel="stylesheet" href="${STATIC_RES}/angular/angular-material.min.css?v=${SCRIPT_VERSION}" />
    <link rel="stylesheet" href="${STATIC_RES}/css/ngToast.css?v=${SCRIPT_VERSION}" />
    <link rel="stylesheet" href="${STATIC_RES}/css/jquery-ui.min.css?v=${SCRIPT_VERSION}" />
    <script src="${STATIC_RES}/js/q.js"></script>
    
    <script src="${STATIC_RES}/js/sockjs.js"></script>
    <script src="${STATIC_RES}/js/stomp.js"></script>
    <script src="${STATIC_RES}/js/wowchat.js"></script>
    
    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${STATIC_RES}/css/fonts.googleapis.com.css?v=${SCRIPT_VERSION}" />

    <!-- ace styles -->
    <link rel="stylesheet" href="${STATIC_RES}/css/ace.min.css?v=${SCRIPT_VERSION}" class="ace-main-stylesheet" id="main-ace-style" />

    <!--[if lte IE 9]>
            <link rel="stylesheet" href="${STATIC_RES}/css/ace-part2.min.css?v=${SCRIPT_VERSION}" class="ace-main-stylesheet" />
    <![endif]-->
    <link rel="stylesheet" href="${STATIC_RES}/css/ace-skins.min.css?v=${SCRIPT_VERSION}" />
    <link rel="stylesheet" href="${STATIC_RES}/css/ace-rtl.min.css?v=${SCRIPT_VERSION}" />
    <link rel="stylesheet" href="${STATIC_RES}/css/select2.min.css?v=${SCRIPT_VERSION}" />

    <link rel="stylesheet" href="${STATIC_RES}/css/ui.jqgrid.min.css?v=${SCRIPT_VERSION}" />
    <link rel="stylesheet" href="${STATIC_RES}/css/angular-datepicker.css?v=${SCRIPT_VERSION}" />
    
    <link rel="stylesheet" href="${STATIC_RES}/css/ngDialog.css?v=${SCRIPT_VERSION}">
    <link rel="stylesheet" href="${STATIC_RES}/css/ngDialog-theme-default.css?v=${SCRIPT_VERSION}">
    
    <!--[if lte IE 9]>
      <link rel="stylesheet" href="${STATIC_RES}/css/ace-ie.min.css?v=${SCRIPT_VERSION}" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="${STATIC_RES}/js/ace-extra.min.js?v=${SCRIPT_VERSION}"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${STATIC_RES}/js/html5shiv.min.js?v=${SCRIPT_VERSION}"></script>
    <script src="${STATIC_RES}/js/respond.min.js?v=${SCRIPT_VERSION}"></script>
    <![endif]-->
    
    <link rel="stylesheet" href="${STATIC_RES}/css/custom.css?v=${SCRIPT_VERSION}" />