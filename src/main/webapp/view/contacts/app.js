/* 
 * Created on : 24 May, 2018, 10:19:44 PM
 * Author     : sarker
 */

var app = angular.module('ContactManagementApp', ['rt.select2','720kb.datepicker', 'ngDialog', 'ngToast', 'ui.router','ui.bootstrap', 'ngMaterial', 'ngMessages', 'ngSanitize', 'ngAnimate'])
    
    .config(function($stateProvider, $urlRouterProvider, ngToastProvider) {
        ngToastProvider.configure({
            animation: 'slide'
        });

        $stateProvider

        .state( JCOMPONENT.contact_list_view, {
            url: '/contact_list_view',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/management/contact_list_view.html',
            controller: 'ContactListCtrl'
        })
        .state( JCOMPONENT.contact_add_view, {
            url: '/contact_add_view',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/management/contact_form_view.html',
            controller: 'ContactFormCtrl'
        })
        .state( JCOMPONENT.contact_edit_view, {
            url: '/contact_edit_view/:contact_id',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/management/contact_form_view.html',
            controller: 'ContactFormCtrl'
        })
        
        
        
        .state( JCOMPONENT.segment_list_view, {
            url: '/segment_list_view',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/segment/segment_list_view.html',
            controller: 'SegmentListCtrl'
        })
        .state( JCOMPONENT.segment_add_view, {
            url: '/segment_add_view',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/segment/segment_form_view.html',
            controller: 'SegmentFormCtrl'
        })
        .state( JCOMPONENT.segment_edit_view, {
            url: '/segment_edit_view/:segment_id',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/segment/segment_form_view.html',
            controller: 'SegmentFormCtrl'
        })
        
        ;
        
        $urlRouterProvider.otherwise('/' + JCOMPONENT.contact_list_view);
        
    });
    