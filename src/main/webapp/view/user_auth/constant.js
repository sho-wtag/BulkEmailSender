var JMODULE_NAME = "user_auth";
var JCONTROLLER = {
    role:"role",
    user:"user",
    feature:"feature",
    PROFILE:"profile"
};
var JCOMPONENT = {
    role_add_view:"role_add_view",
    role_active_inactive:"role_active_inactive",
    role_show:"role_show",
    role_update:"role_update",
    role_list_view:"role_list_view",
    role_feature_view:"role_feature_view",
    
    user_add_view:"user_add_view",
    user_list_view:"user_list_view",
    user_show:"user_show",
    user_edit_view:"user_edit_view",
    user_active_inactive:"user_active_inactive",
    user_profile_view:"user_profile_view",
    
    feature_list_view:"feature_list_view",
    feature_show:"feature_show",
    feature_update:"feature_update",
    feature_active_inactive:"feature_active_inactive",
    feature_add_view:"feature_add_view"
    
};

var API = {
    ROLE_GET: _baseurl_ + "user_auth/role/get",
    ROLE_SAVE: _baseurl_ + "user_auth/role/save",
    ROLE_UPDATE: _baseurl_ + "user_auth/role/update",
    ROLE_LIST: _baseurl_ + "user_auth/role/get/all",
    ROLE_FEATURE_LIST: _baseurl_ + "user_auth/role/features",
    ROLE_FEATURE_MAP: _baseurl_ + "user_auth/role/mapRoleFeatures",
    
    FEATURE_GET: _baseurl_ + "user_auth/feature/get",
    FEATURE_SAVE: _baseurl_ + "user_auth/feature/save",
    FEATURE_UPDATE: _baseurl_ + "user_auth/feature/update",
    FEATURE_LIST: _baseurl_ + "user_auth/feature/get/all",
    
    USER_GET: _baseurl_ + "user_auth/user/get",
    HOSPITALS_GET: _baseurl_ + "user_auth/user/get/hospitals",
    USER_SAVE: _baseurl_ + "user_auth/user/save",
    CHECK_EMAIL: _baseurl_ + "user_auth/user/check-email",
    USER_UPDATE: _baseurl_ + "user_auth/user/update",
    USER_LIST: _baseurl_ + "user_auth/user/get/all",  
    SHOW_PROFILE: _baseurl_ + JMODULE_NAME + "/" + JCONTROLLER.PROFILE + "/show",
    CHANGE_PASSWORD: _baseurl_ + JMODULE_NAME + "/" + JCONTROLLER.PROFILE + "/change-password"
};
