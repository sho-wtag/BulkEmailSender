app.controller('CampaignsFormCtrl', function ($scope, $http, $state, $timeout, $stateParams, $rootScope, $sce, $mdDialog, $interval, ClientService, DialogBox, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.current_state = $state.current.name;

    $scope.module = {
        campaign_id: "",
        cid: "",
        type: "",
        parent: "",
        campaign_name:"",
        description:"",
        template: "",
        source_url: "",
        editor_name: "",
        editor_data: "",
        last_check: new Date(),
        check_status: "",
        from: "",
        address: "",
        reply_to: "",
        subject: "",
        html: "",
        html_prepared:"",
        status: "",
        tracking_disabled: "",
        scheduled: new Date(),
        status_change: new Date(),
        delivered: "",
        opened: "",
        clicks:"",
        unsubscribed: "",
        bounced: "",
        complained: "",
        created: new Date()

    };

    if($state.current.name === JCOMPONENT.template_update){
        var req = Communication.request("GET", API.CAMPAIGNS_GET + '/' + $stateParams.campaign_id, $scope.module);

        req.then(function (resp) {
            log("campaigns settings edit: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
            } else{

            }

        }, function (err) {
            log("campaign settings  error", JSON.stringify(err));
        });
    }


    $scope.saveModule = function () {
        var req;


        //$scope.module.html = escape($scope.module.html);

        if($state.current.name === JCOMPONENT.campaigns_edit_view){
            req = Communication.request("PUT", API.CAMPAIGNS_UPDATE, $scope.module);
        } else{
            req = Communication.request("POST", API.CAMPAIGNS_SAVE, $scope.module);
        }
        log("req: " + JSON.stringify($scope.module));

        req.then(function (resp) {
            log("campaigns manage: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
                $state.go(JCOMPONENT.campaigns_add_view);

                $rootScope.toastSuccess("Successfully saved");
            } else{
                $rootScope.toastError(resp.message);
            }


        }, function (err) {
            log("campaigns error", JSON.stringify(err));
        });
    };


});