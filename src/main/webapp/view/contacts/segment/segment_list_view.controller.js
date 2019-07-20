app.controller('SegmentListCtrl', function ($scope, $http, $state, $timeout, $rootScope, $mdDialog, $interval, DialogBox, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    
    $scope.search = {
        name: "",
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
        var req = Communication.request("POST", API.SEGMENT_FILTER + "/" + currentPage + "/" + itemPerPage, $scope.search);
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
    
    $scope.showEditForm = function(obj){
        $state.go(JCOMPONENT.segment_edit_view, {"segment_id":obj.segment_id});
    };
    
});