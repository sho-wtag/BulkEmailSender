<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../common/headblock_leftmenu.jsp" %>
<script src="${NG_SRC}/settings/constant.js?v=${SCRIPT_VERSION}"></script>

<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try{
            ace.settings.loadState('main-container');
        } catch (e) {
        }
    </script>

    <%@include file="../common/left_menu.jsp" %>

    <div class="main-content">
        <div class="main-content-inner" style="background: #FFF;" ng-app="SettingsApp">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-gear home-icon"></i>
                        Settings
                    </li>
                    <li class="active" id="page_name"></li>
                </ul><!-- /.breadcrumb -->

                <div class="nav-search" id="nav-search" >
                    <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                        
                        <a class="btn btn-sm btn-primary" ui-sref="{{JCOMPONENT.smtp_add_view}}" ng-if="hasPermission(JMODULE_NAME,  JCOMPONENT.smtp_add_view)">
                            <i class="ace-icon fa fa-plus-circle"></i> Smtp Setting
                        </a>
                        <a class="btn btn-sm btn-primary" ui-sref="{{JCOMPONENT.smtp_list_view}}" ng-if="hasPermission(JMODULE_NAME, JCOMPONENT.smtp_list_view)">
                            <i class="ace-icon fa fa-list"></i> Smtp Settings
                        </a>

                    </div>

                </div><!-- /.nav-search -->
            </div>

            <div class="page-content">
                
                <div id="operation_scs_alert" class="hide alert alert-success">
                    <p id="operation_scs_msg">this alert dialog</p>
                </div>
                
                <div id="operation_dng_alert" class="hide alert alert-danger">
                    <p id="operation_dng_msg">this alert dialog</p>
                </div>
                
                <ui-view></ui-view>

            </div><!-- /.page-content -->

        </div>
    </div><!-- /.main-content -->

    <%@include file="../common/footblock.jsp" %>
</div><!-- /.main-container -->
<%@include file="../common/resoucelink_footer.jsp" %>

<!-- angularJS script -->
<script src="${NG_SRC}/settings/app.js?v=${SCRIPT_VERSION}"></script>

<!-- please include the app.service.js & app.directives.js file after each and every app.js file -->
<script src="${ANGULAR}/app.directives.js?v=${SCRIPT_VERSION}"></script>
<script src="${ANGULAR}/app.services.js?v=${SCRIPT_VERSION}"></script>

<!-- include here rest of your ng app controller -->
<script src="${NG_SRC}/settings/smtp/smtp_form.controller.js?v=${SCRIPT_VERSION}"></script>
<script src="${NG_SRC}/settings/smtp/smtp_list.controller.js?v=${SCRIPT_VERSION}"></script>

<%@include file="../common/close_page.jsp" %>




