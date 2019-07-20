/* 
 * Created on : 24 May, 2018, 10:19:44 PM
 * Author     : sarker
 */

var app = angular.module('TemplateManagementApp', ['rt.select2','720kb.datepicker', 'ngDialog', 'ngToast', 'ui.router','ui.bootstrap', 'ngMaterial', 'ngMessages', 'ngSanitize', 'ngAnimate','ng.ckeditor'])
    
    .config(function($stateProvider, $urlRouterProvider, ngToastProvider) {
        ngToastProvider.configure({
            animation: 'slide'
        });

        $stateProvider

        // .state( JCOMPONENT.template_list_view, {
        //     url: '/template_list_view',
        //     cache: false,
        //     templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/template/role_list.html',
        //     controller: 'RoleListCtrl'
        // })
         .state(JCOMPONENT.template_add_view, {
            url: '/template_add_view',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/template/template_form.html',
            controller: 'TemplateFormCtrl'
            
        }).state(JCOMPONENT.template_update, {
            url: '/template_update/:template_id',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/template/template_form.html',
            controller: 'TemplateFormCtrl'
        }).state(JCOMPONENT.campaigns_add_view, {
            url: '/campaigns_add_view',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/campaigns/campaigns_form.html',
            controller: 'CampaignsFormCtrl'

        }).state(JCOMPONENT.campaigns_edit_view, {
            url: '/campaigns_edit_view/:campaign_id',
            cache: false,
            templateUrl: _NG_SRC_ + '/' + JMODULE_NAME +'/campaigns/campaigns_form.html',
            controller: 'CampaignsFormCtrl'
        });

        $urlRouterProvider.otherwise('/' + JCOMPONENT.campaigns_add_view);
        
    });
    