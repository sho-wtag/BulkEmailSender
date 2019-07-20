app.controller('UserListCtrl', function ($scope, $http, $state, $timeout, $rootScope, $mdDialog, $interval, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.list = [];
    
    
    $scope.getDataList = function () {
        var req = Communication.request("GET", API.USER_LIST, {});
        req.then(function (resp) {
            log("user list: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.list = resp.body;
            }

        }, function (err) {
            log("user error", JSON.stringify(err));
        });
    };

    $scope.showEditForm = function (obj) {
        $state.go(JCOMPONENT.user_edit_view, {user_id: obj.user_id});
    };
    
    $scope.activeInactive = function (obj, val) {
        obj.active = val;
        var req = Communication.request("PUT", API.USER_UPDATE + '/' + obj.user_id, obj);
        req.then(function (resp) {
            log("user active/inactive: " + JSON.stringify(resp));
            $scope.getDataList();
        }, function (err) {
            log("user error", JSON.stringify(err));
        });
    };

});