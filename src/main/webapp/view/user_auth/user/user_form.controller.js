app.controller('UserFormCtrl', function ($scope, $http, $state, $timeout, $stateParams, $rootScope, $sce, $mdDialog, $interval, ClientService, DialogBox, encrypt, Communication) {
   
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.current_state = $state.current.name;
    $scope.roleList = [];
    $scope.selected_role_id = "";
    $scope.email_cnt = -1;
    $scope.module = {
        user_id: "",
        user_code: "",
        first_name: "",
        last_name: "",
        email: "",
        password: "",
        phone: "",
        address: "",
        country_id: 1,
        role_id:"",
        role_name:""
    };
    
    
    $scope.getRoleList = function () {
        var req = Communication.request("GET", API.ROLE_LIST, {});
        req.then(function (resp) {
            log("role list: " + JSON.stringify(resp));
            if (resp.code === 200) {
                $scope.roleList = resp.body;
            }
        }, function (err) {
            log("role list error", JSON.stringify(err));
        });
    };
    
    if($state.current.name === JCOMPONENT.user_edit_view){
        var req = Communication.request("GET", API.USER_GET + '/' + $stateParams.user_id, $scope.module);
        req.then(function (resp) {
            log("user edit: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
                $scope.module.password = "";
            }
        }, function (err) {
            log("user edit error", JSON.stringify(err));
        });
    }
    
    
    $scope.saveModule = function () {
        
        if( $scope.email_cnt !== 0 && $state.current.name !== JCOMPONENT.user_edit_view ){
            $rootScope.toastError("Email is already in used! Please, use another.");
            return;
        }
        
        var req;
        
        if($state.current.name === JCOMPONENT.user_edit_view){
            if( $scope.module.password !== "" ){
                $scope.module.password = encrypt.SHA256( $scope.module.password );
            }
            req = Communication.request("PUT", API.USER_UPDATE + '/' + $scope.module.user_id, $scope.module);
        } else{
            $scope.module.password = encrypt.SHA256( $scope.module.password );
            req = Communication.request("POST", API.USER_SAVE, $scope.module);
        }
        
        req.then(function (resp) {
            log("user manage: " + JSON.stringify(resp));
            if (resp.code === 200) {
                $scope.module = resp.body;
                $rootScope.toastSuccess("Successfully saved");
                $state.go(JCOMPONENT.user_list_view);
            } else{
                $scope.module.password = "";
                $rootScope.toastError(resp.message);
            }
        }, function (err) {
            $scope.module.password = "";
            log("user error", JSON.stringify(err));
            $rootScope.toastError(err.message);
        });
    };
    
    $scope.selectRole = function(id){
        $scope.module.role_id = id;
        var sl = document.getElementById("role_id");
        $scope.module.role_name = sl.options[sl.selectedIndex].text;
        
        console.log(JSON.stringify($scope.module));
    };
    
    $scope.checkEmail = function(email){
        var req = Communication.request("PUT", API.CHECK_EMAIL, {"email":email});
        req.then(function (resp) {
            log("email check: " + JSON.stringify(resp));
            if (resp.code === 200 && resp.body.cnt > 0) {
                $scope.email_cnt = resp.body.cnt;
            } else{
                $scope.email_cnt = 0;
            }
        }, function (err) {
            log("email check error", JSON.stringify(err));
        });
    };

});