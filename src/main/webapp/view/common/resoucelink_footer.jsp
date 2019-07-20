<%-- 
    Document   : resoucelink_head
    Created on : May 22, 2018, 11:21:32 AM
    Author     : sarker
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${STATIC_RES}/js/jquery-2.1.4.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/ckeditor/ckeditor.js?v=${SCRIPT_VERSION}"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${STATIC_RES}/js/jquery-1.11.3.min.js?v=${SCRIPT_VERSION}"></script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement)
        document.write("<script src='${STATIC_RES}/js/jquery.mobile.custom.min.js?v=${SCRIPT_VERSION}'>" + "<" + "/script>");
</script>
<script src="${STATIC_RES}/js/bootstrap.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/js/moment.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/js/bootstrap-datetimepicker.min.js?v=${SCRIPT_VERSION}"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
  <script src="${STATIC_RES}/js/excanvas.min.js?v=${SCRIPT_VERSION}"></script>
<![endif]-->
<script src="${STATIC_RES}/js/jquery-ui.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/js/jquery.ui.touch-punch.min.js?v=${SCRIPT_VERSION}"></script>

<!-- ace scripts -->
<script src="${STATIC_RES}/js/ace-elements.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/js/ace.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/js/select2.full.min.js?v=${SCRIPT_VERSION}"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function ($) { 
        $(document).find('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true,
            dateFormat: 'yy-mm-dd'
        });
    });
</script>

<!-- AngularJS 1.6.10 -->
<script src="${STATIC_RES}/angular/angular.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/angular/ui-bootstrap-tpls-0.10.0.min.js?v=${SCRIPT_VERSION}"></script>

<script src="${STATIC_RES}/angular/angular-ui-router.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/angular/angular-animate.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/angular/angular-aria.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/angular/angular-messages.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/angular/angular-sanitize.js?v=${SCRIPT_VERSION}"></script>

<script src="${STATIC_RES}/angular/angular-material.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/angular/angular-datepicker.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/angular/angular-select2.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/angular/ngDialog.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/angular/ngToast.js?v=${SCRIPT_VERSION}"></script>

<script src="${STATIC_RES}/js/jquery.jqGrid.min.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/js/grid.locale-en.js?v=${SCRIPT_VERSION}"></script>

<!--
<script src="${STATIC_RES}/angular/app.js?v=${SCRIPT_VERSION}"></script>
<script src="${STATIC_RES}/angular/app.services.js?v=${SCRIPT_VERSION}"></script>
-->