app.controller('SegmentFormCtrl', function ($scope, $http, $state, $timeout, $stateParams, $rootScope, $sce, $mdDialog, $interval, ClientService, DialogBox, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.current_state = $state.current.name;
    
    $scope.module = {
        segment_id: "",
        name: "",
        description: ""
    };
    
    if($state.current.name === JCOMPONENT.segment_edit_view){
        var req = Communication.request("GET", API.SEGMENT_GET + '/' + $stateParams.segment_id, $scope.module);

        req.then(function (resp) {
            log("segment edit: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
            }
            
        }, function (err) {
            log("segment edit error", JSON.stringify(err));
        });
    }
    
    
    $scope.saveModule = function () {
        var req;
        
        if($state.current.name === JCOMPONENT.segment_edit_view){
            req = Communication.request("PUT", API.SEGMENT_UPDATE + '/' + $scope.module.segment_id, $scope.module);
        } else{
            req = Communication.request("POST", API.SEGMENT_SAVE, $scope.module);
        }
        
        req.then(function (resp) {
            log("segment manage: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
                $state.go(JCOMPONENT.segment_list_view);
                
                $rootScope.toastSuccess("Successfully saved");
            } else{
                $rootScope.toastError(resp.message);
            }
            
            
        }, function (err) {
            log("segment error", JSON.stringify(err));
        });
    };


});