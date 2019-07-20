<%-- 
    Document   : top_menu
    Created on : May 22, 2018, 12:53:32 PM
    Author     : sarker
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style type="text/css">
    .page-content{
        padding: 8px 10px 5px;
        padding-top: 0px;
    }
    .sidebar.h-sidebar {
        margin-top: 2px;
    }
</style>

<div id="sidebar" class="sidebar h-sidebar navbar-collapse collapse ace-save-state">
    <script type="text/javascript">
        try {
            ace.settings.loadState('sidebar');
        } catch (e) {
        }
    </script>

    <!--<div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>-->

    <ul class="nav nav-list">
        <c:forEach items="${USER.menu.features}" var="module" varStatus="moduleCounter">
            
            <c:choose>
                <c:when test="${fn:contains(module.feature_name, 'Dashboard')}">
                    <li class="hover">
                        <a href="${APP}/dashboard/statics/view#!/dashboard_static_view">
                            <i class="menu-icon fa fa-dashboard"></i>
                            <span class="menu-text">
                                Dashboard
                            </span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
            
                    <li class="hover">
                        <a class="dropdown-toggle">
                            <c:choose>
                                <c:when test="${fn:contains(module.feature_name, 'Auth')}">  
                                    <i class="menu-icon fa fa-key"></i>
                                </c:when>
                                <c:when test="${fn:contains(module.feature_name, 'Case')}">  
                                    <i class="menu-icon fa fa-user-secret"></i>
                                </c:when>
                                <c:when test="${fn:contains(module.feature_name, 'Report')}">  
                                    <i class="menu-icon fa fa-bar-chart"></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="menu-icon fa fa-desktop"></i>
                                </c:otherwise>
                            </c:choose>

                            <span class="menu-text">
                                ${module.feature_name}
                            </span>

                            <b class="arrow fa fa-angle-down"></b>
                        </a>

                        <b class="arrow"></b>

                        <ul class="submenu">
                            <c:forEach items="${module.features}" var="fg" varStatus="fgCounter">

                                <li class="hover">
                                    <a href="#" class="dropdown-toggle">
                                        <i class="menu-icon fa fa-hand-o-right"></i>
                                        <span class="menu-text">
                                            ${fg.feature_name}
                                        </span>

                                        <b class="arrow fa fa-angle-down"></b>
                                    </a>

                                    <b class="arrow"></b>

                                    <ul class="submenu">
                                        <c:forEach items="${fg.features}" var="menu" varStatus="menuCounter">

                                            <c:choose>
                                                <c:when test="${menu.is_menu}">
                                                    <li class="hover">
                                                        <a href="${APP}/${menu.module}/${menu.controller}/${menu.action}#!/${menu.component}">
                                                            <i class="menu-icon fa fa-caret-right"></i>
                                                            ${menu.feature_name}
                                                        </a>
                                                        <b class="arrow"></b>
                                                    </li>
                                                </c:when>
                                            </c:choose>

                                        </c:forEach>
                                    </ul>

                                </li>

                            </c:forEach>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        
        
    </ul><!-- /.nav-list -->
</div>
