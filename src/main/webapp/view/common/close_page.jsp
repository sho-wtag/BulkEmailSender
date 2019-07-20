<%-- 
    Document   : close_page
    Created on : 27 May, 2018, 1:51:15 PM
    Author     : sarker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <script src="${ANGULAR}/form_button_perm.controller.js?v=${SCRIPT_VERSION}"></script>

    
    <script type="text/javascript">
        var url = APP + "/" + window.location.href.replace(_baseurl_,''); 
        markSelectedMenu(url);
        function markSelectedMenu(url){
            deSelectPreviousMenu();
            // if these substring not found in the URL then select/active
            if( url.indexOf("profile") === -1  ){
                var selectedEl = $('a[href="' + url + '"]');
                selectedEl.parent().addClass('active');
                selectedEl.parent().parent().css("display", "block");
                selectedEl.parent().parent().parent().addClass('open');
                selectedEl.parent().parent().parent().parent().css("display", "block");
                selectedEl.parent().parent().parent().parent().parent().addClass('open');
            }
        }
        function deSelectPreviousMenu() {
            var selectedEl = $('a.submenu-custom');
            selectedEl.parent().removeClass('active');
            selectedEl.parent().parent().removeAttr('style');
            selectedEl.parent().parent().parent().removeClass('open');
            selectedEl.parent().parent().parent().parent().removeAttr('style');
            selectedEl.parent().parent().parent().parent().parent().removeClass('open');
        }
    </script>
    
    </body>
</html>
