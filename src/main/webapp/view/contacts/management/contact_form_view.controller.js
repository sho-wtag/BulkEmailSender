app.controller('ContactFormCtrl', function ($scope, $http, $state, $timeout, $stateParams, $rootScope, $sce, $mdDialog, $interval, ClientService, DialogBox, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.current_state = $state.current.name;
    
    $scope.module = {
        contact_id: "",
        cid:"",
        first_name:"",
        last_name:"",
        email:"",
        active:1
    };
    
    if($state.current.name === JCOMPONENT.contact_edit_view){
        var req = Communication.request("GET", API.CONTACT_GET + '/' + $stateParams.contact_id, $scope.module);

        req.then(function (resp) {
            log("contact edit: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
            }
            
        }, function (err) {
            log("contact edit error", JSON.stringify(err));
        });
    }
    
    
    $scope.saveModule = function () {
        var req;
        
        if($state.current.name === JCOMPONENT.contact_edit_view){
            req = Communication.request("PUT", API.CONTACT_UPDATE + '/' + $scope.module.contact_id, $scope.module);
        } else{
            req = Communication.request("POST", API.CONTACT_SAVE, $scope.module);
        }
        
        req.then(function (resp) {
            log("contact manage: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
                $state.go(JCOMPONENT.contact_list_view);
                
                $rootScope.toastSuccess("Successfully saved");
            } else{
                $rootScope.toastError(resp.message);
            }
            
            
        }, function (err) {
            log("contact error", JSON.stringify(err));
        });
    };


});