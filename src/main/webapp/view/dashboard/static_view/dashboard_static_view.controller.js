app.controller('DashboardStaticViewCtrl', function ($scope, $http, $state, $timeout, $rootScope, $mdDialog, $interval, DialogBox, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.areaList = [];
    $scope.atmList = [];
    $scope.is_all_selected = false;
    
    $scope.search = {
        start_date: "",
        end_date: "",
        area_id: "",
        atm_id: "",
        status: "99",
        is_archived: "0"
    };
    
    $scope.getAreaList = function () {
        var req = Communication.request("GET", API.ATM_AREA_LIST, {});
        req.then(function (resp) {
            log("area list: " + JSON.stringify(resp));
            if (resp.code === 200) {
                $scope.areaList = resp.body;
            }
        }, function (err) {
            log("area list error", JSON.stringify(err));
        });
    };
    
    $scope.getAtmList = function (area_id) {
        $scope.search.area_id = area_id;
        
        var req = Communication.request("GET", API.ATM_LIST + "/" + area_id, {});
        req.then(function (resp) {
            log("atm list: " + JSON.stringify(resp));
            if (resp.code === 200) {
                $scope.atmList = resp.body;
            }
        }, function (err) {
            log("atm list error", JSON.stringify(err));
        });
    };
    
    //filtering table column
    $scope.orderByField = '';
    $scope.reverseSort = false;
    
    //pagination varialbes
    $scope.data = {};
    $scope.data.items = [];
    $scope.data.itemCount = 0;
    $scope.currentPage = 1;
    $scope.itemPerPage = 20;

    $scope.getDataList = function (currentPage, itemPerPage) {
        
        $scope.currentPage = currentPage;
        $scope.data = {};
        $scope.data.items = [];
        $scope.data.itemCount = 0;

        DialogBox.showProgress();
        log("search obj: " + JSON.stringify($scope.search));
        var req = Communication.request("POST", API.LOG_FILTER + "/" + currentPage + "/" + itemPerPage, $scope.search);
        req.then(function (resp) {
            DialogBox.hideProgress();
            log("log list: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.data = resp.body;
            } else{
                $rootScope.toastError(resp.message);
            }

        }, function (err) {
            DialogBox.hideProgress();
            log("log list error", JSON.stringify(err));
        });
    };

    $scope.doFilter = function (currentPage, itemPerPage) {
        $scope.getDataList(currentPage, itemPerPage);
    };
    
    
    $scope.downloadImages = function(currentPage, itemPerPage){
        DialogBox.showProgress();
        
        var req = Communication.request("POST", API.LOG_FILTER + "/" + currentPage + "/" + itemPerPage, $scope.search);
        req.then(function (resp) {
            DialogBox.hideProgress();
            
            if (resp.code === 200) {
                window.location.assign(API.ZIP_DOWNLOAD + "/" + resp.body.fileName + "/" + resp.body.format);
            }

        }, function (err) {
            DialogBox.hideProgress();
            log("log list error", JSON.stringify(err));
        });
    };
    
});