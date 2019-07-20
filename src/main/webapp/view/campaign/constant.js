var JMODULE_NAME = "campaign";
var JCONTROLLER = {
    template:"template",
    campaigns: "campaigns"
};
var JCOMPONENT = {
    template_add_view:"template_add_view",
    template_active_inactive:"template_active_inactive",
    template_show:"template_show",
    template_update:"template_update",
    template_list_view:"template_list_view",

    campaigns_add_view:"campaigns_add_view",
    campaigns_active_inactive:"campaigns_active_inactive",
    campaigns_show:"campaigns_show",
    campaigns_edit_view:"campaigns_edit_view",
    campaigns_list_view:"campaigns_list_view"
};

var API = {
    TEMPLATE_GET: _baseurl_ + "campaign/template/get",
    TEMPLATE_SAVE: _baseurl_ + "campaign/template/save",
    TEMPLATE_UPDATE: _baseurl_ + "campaign/template/update",
    TEMPLATE_LIST: _baseurl_ + "campaign/template/get/all",

    CAMPAIGNS_GET: _baseurl_ + "campaign/campaigns/get",
    CAMPAIGNS_SAVE: _baseurl_ + "campaign/campaigns/save",
    CAMPAIGNS_UPDATE: _baseurl_ + "campaign/campaigns/update",
    CAMPAIGNS_LIST: _baseurl_ + "campaign/campaigns/get/all"
};
