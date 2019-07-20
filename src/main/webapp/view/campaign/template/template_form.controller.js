app.controller('TemplateFormCtrl', function ($scope, $http, $state, $timeout, $stateParams, $rootScope, $sce, $mdDialog, $interval, ClientService, DialogBox, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.current_state = $state.current.name;

    $scope.module = {
        name: "",
        description: "",
        html: "",
        text: "",
        created: new Date()

    };

    if($state.current.name === JCOMPONENT.template_update){
        var req = Communication.request("GET", API.TEMPLATE_GET + '/' + $stateParams.template_id, $scope.module);

        req.then(function (resp) {
            log("template settings edit: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
            } else{

            }

        }, function (err) {
            log("template settings  error", JSON.stringify(err));
        });
    }


    $scope.saveModule = function () {
        var req;


        $scope.module.html = escape($scope.module.html);

        if($state.current.name === JCOMPONENT.template_update){
            req = Communication.request("PUT", API.TEMPLATE_UPDATE, $scope.module);
        } else{
            req = Communication.request("POST", API.TEMPLATE_SAVE, $scope.module);
        }
        log("req: " + JSON.stringify($scope.module));

        req.then(function (resp) {
            log("template manage: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
                $state.go(JCOMPONENT.campaigns_add_view);

                $rootScope.toastSuccess("Successfully saved");
            } else{
                $rootScope.toastError(resp.message);
            }


        }, function (err) {
            log("template error", JSON.stringify(err));
        });
    };


});