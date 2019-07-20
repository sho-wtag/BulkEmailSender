app.controller('SmtpFormCtrl', function ($scope, $http, $state, $timeout, $stateParams, $rootScope, $sce, $mdDialog, $interval, ClientService, DialogBox, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.current_state = $state.current.name;

    $scope.module = {
        smtp_hostname: "",
        smtp_port: "",
        smtp_encryption: "",
        smtp_user: "",
        smtp_pass: "",
        smtp_max_connections: "",
        smtp_max_messages: "",
        smtp_log: "",
        service_url:"",
        admin_email:""

    };

    if($state.current.name === JCOMPONENT.smtp_edit_view){
        var req = Communication.request("GET", API.SMTP_GET + '/' + $stateParams.id, $scope.module);

        req.then(function (resp) {
            log("smtp settings edit: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
            } else{

            }

        }, function (err) {
            log("smtp settings  error", JSON.stringify(err));
        });
    }


    $scope.saveModule = function () {
        var req;

        if($state.current.name === JCOMPONENT.smtp_edit_view){
            req = Communication.request("PUT", API.SMTP_UPDATE, $scope.module);
        } else{
            req = Communication.request("POST", API.SMTP_SAVE, $scope.module);
        }
        log("req: " + JSON.stringify($scope.module));
        req.then(function (resp) {
            log("smtp manage: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
                $state.go(JCOMPONENT.smtp_list_view);

                $rootScope.toastSuccess("Successfully saved");
            } else{
                $rootScope.toastError(resp.message);
            }


        }, function (err) {
            log("smtp error", JSON.stringify(err));
        });
    };


});