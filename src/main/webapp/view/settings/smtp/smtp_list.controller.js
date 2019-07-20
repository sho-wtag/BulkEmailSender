app.controller('SmtpListCtrl', function ($scope, $http, $state, $timeout, $rootScope, $mdDialog, $interval, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.list = [];


    $scope.getDataList = function () {
        var req = Communication.request("GET", API.SMTP_LIST, {});
        req.then(function (resp) {
            log("smtp settings list: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.list = resp.body;
            }

        }, function (err) {
            log("smtp settings error", JSON.stringify(err));
        });
    };

    $scope.showEditForm = function (obj) {
        $state.go(JCOMPONENT.smtp_edit_view, {id: obj.id});
    };

    $scope.activeInactive = function (obj, val) {

        obj.active = val;
        var req = Communication.request("PUT", API.SMTP_UPDATE + '/' + obj.id, obj);

        req.then(function (resp) {
            log("Smtp settings active/inactive: " + JSON.stringify(resp));
            $scope.getDataList();
        }, function (err) {
            log("Smtp settings error", JSON.stringify(err));
        });
    };

});