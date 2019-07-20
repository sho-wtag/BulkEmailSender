/* 
 * Created on : 24 May, 2018, 10:19:44 PM
 * Author     : sarker
 */

var app = angular.module('SettingsApp', ['rt.select2','720kb.datepicker', 'ngDialog', 'ngToast', 'ui.router','ui.bootstrap', 'ngMaterial', 'ngMessages', 'ngSanitize', 'ngAnimate'])
    
    .config(function($stateProvider, $urlRouterProvider, ngToastProvider) {
        ngToastProvider.configure({
            animation: 'slide'
        });

        $stateProvider

        .state( JCOMPONENT.smtp_list_view, {
            url: '/smtp_list_view',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/smtp/smtp_list.html',
            controller: 'SmtpListCtrl'
        }).state(JCOMPONENT.smtp_add_view, {
            url: '/smtp_add_view',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/smtp/template_form.html',
            controller: 'SmtpFormCtrl'
            
        }).state(JCOMPONENT.smtp_edit_view, {
            url: '/smtp_edit_view/:id',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/smtp/template_form.html',
            controller: 'SmtpFormCtrl'
        });
        
        $urlRouterProvider.otherwise('/' + JCOMPONENT.smtp_list_view);
        
    });
    