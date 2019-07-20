var JMODULE_NAME = "dashboard";
var JCONTROLLER = {
    statics:"statics",

};
var JCOMPONENT = {
    dashboard_static_view:"dashboard_static_view",
};

var API = {
    ATM_AREA_LIST: _baseurl_ + "atm_management/area/get/all",
    ATM_LIST: _baseurl_ + "atm_management/atm/area_wise",
    
    INVESTIGATION_FILTER: _baseurl_ + "case_management/investigation/filter",
    ZIP_DOWNLOAD: _baseurl_ + "case_management/investigation/zdownload",
    
    LOG_FILTER: _baseurl_ + "case_management/logs/filter",
};
