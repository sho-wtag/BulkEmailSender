app.controller('RoleListCtrl', function ($scope, $http, $state, $timeout, $rootScope, $mdDialog, $interval, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.list = [];

    
    $scope.getDataList = function () {
        var req = Communication.request("GET", API.ROLE_LIST, {});
        req.then(function (resp) {
            log("role list: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.list = resp.body;
            }

        }, function (err) {
            log("role error", JSON.stringify(err));
        });
    };

    $scope.showEditForm = function (obj) {
        $state.go(JCOMPONENT.role_update, {role_id: obj.role_id});
    };

    $scope.activeInactive = function (obj, val) {

        obj.active = val;
        var req = Communication.request("PUT", API.ROLE_UPDATE + '/' + obj.role_id, obj);

        req.then(function (resp) {
            log("role active/inactive: " + JSON.stringify(resp));
            $scope.getDataList();
        }, function (err) {
            log("role error", JSON.stringify(err));
        });
    };

});