var JMODULE_NAME = "settings";
var JCONTROLLER = {
    smtp:"smtp",
};

var JCOMPONENT = {
    smtp_add_view:"smtp_add_view",
    smtp_active_inactive:"smtp_active_inactive",
    smtp_show:"smtp_show",
    smtp_list_view:"smtp_list_view",
    smtp_edit_view:"smtp_edit_view",

};

var API = {
    SMTP_GET: _baseurl_ + "settings/smtp/get",
    SMTP_SAVE: _baseurl_ + "settings/smtp/save",
    SMTP_UPDATE: _baseurl_ + "settings/smtp/update",
    SMTP_LIST: _baseurl_ + "settings/smtp/get/all",

};
