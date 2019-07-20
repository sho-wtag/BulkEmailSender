var JMODULE_NAME = "contacts";
var JCONTROLLER = {
    management:"management",
    segment:"segment",

};
var JCOMPONENT = {
    contact_list_view:"contact_list_view",
    contact_add_view:"contact_add_view",
    contact_edit_view:"contact_edit_view",
    
    segment_list_view:"segment_list_view",
    segment_add_view:"segment_add_view",
    segment_edit_view:"segment_edit_view",
};

var API = {
    ATM_AREA_LIST: _baseurl_ + "atm_management/area/get/all",
    ATM_LIST: _baseurl_ + "atm_management/atm/area_wise",
    
    CONTACT_FILTER: _baseurl_ + JMODULE_NAME + "/management/filter",
    CONTACT_GET: _baseurl_ + JMODULE_NAME + "/management/get",
    CONTACT_SAVE: _baseurl_ + JMODULE_NAME + "/management/save",
    CONTACT_UPDATE: _baseurl_ + JMODULE_NAME + "/management/update",
    
    
    SEGMENT_FILTER: _baseurl_ + JMODULE_NAME + "/segment/filter",
    SEGMENT_GET: _baseurl_ + JMODULE_NAME + "/segment/get",
    SEGMENT_SAVE: _baseurl_ + JMODULE_NAME + "/segment/save",
    SEGMENT_UPDATE: _baseurl_ + JMODULE_NAME + "/segment/update",
};
