app.controller('UserProfileCtrl', function ($scope, $http, $state, $timeout, $stateParams, $rootScope, $sce, $mdDialog, $interval, ClientService, DialogBox, encrypt, Communication) {
    $rootScope.setPageName(JMODULE_NAME,$state.current.name);
    $scope.current_state = $state.current.name;
    
    $scope.current_password = "";
    $scope.new_password = "";
    $scope.confirm_new_password = "";
    
    $scope.module = {
        user_id: "",
        user_code: "",
        first_name: "",
        last_name: "",
        email: "",
        password: "",
        phone: "",
        address: "",
        country_id: "",
        role_name:"",
        branch_name:"",
        company_name:""
    };
    
    $scope.getDetails = function(){
        var req = Communication.request("GET", API.SHOW_PROFILE, $scope.module);

        req.then(function (resp) {
            console.log("user details: " + JSON.stringify(resp));

            if (resp.code === 200) {
                $scope.module = resp.body;
            }
            
        }, function (err) {
            console.log("user details error", JSON.stringify(err));
        });
    };
    
    
    $scope.changePassword = function () {
        
        if( $scope.current_password === "" || $scope.new_password === "" || $scope.confirm_new_password === "" ){
            DialogBox.alert("Warning", "Password is required!");
            angular.element('#current_password').focus();
            return;
        }
        
        if( $scope.new_password !== $scope.confirm_new_password ){
            DialogBox.alert("Warning", "Password doesn't match!");
            angular.element('#new_password').focus();
            return;
        }
        
        DialogBox.confirm("Are you sure to change password?").then(function(resp){
            console.log(resp+"");
            if( resp ){
                var pwd = encrypt.SHA256( $scope.current_password );
                var new_pwd = encrypt.SHA256( $scope.new_password );
                var re_cnf_pwd = encrypt.SHA256( $scope.confirm_new_password );
                
                var req = Communication.request("PUT", API.CHANGE_PASSWORD, {"pwd":pwd, "new_pwd":new_pwd, "re_cnf_pwd":re_cnf_pwd});
                
                req.then(function (resp) {
                    console.log( JSON.stringify(resp) );
                    if (resp.code === 200 && (resp.body === 1 || resp.body === "1" ) ) {
                        
                        $scope.current_password = "";
                        $scope.new_password = "";
                        $scope.confirm_new_password = "";
                        
                        DialogBox.alert("Success", "Password has been changed");
                    } else{
                        DialogBox.alert("Success", "Sorry, unable to change password");
                    }
                });
            }
        });
        
    };

});