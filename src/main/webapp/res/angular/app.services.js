
app.factory('CommunicationService', function($http) {
    return {
        getRequest: function(type, url, reqData) {
            
            return returnObj = {
                method: type.toUpperCase(),
                url: url,
                headers: {'Content-Type': 'application/json'},
                dataType: 'json',
                data: JSON.stringify(reqData)
            };
        }

    };
});

app.factory('Communication', function ($http, $q, $timeout, CommunicationService) {
    return {
        request: function (type, url, reqData) {
            
            var deferred = $q.defer();
            
            $timeout(function () {
                var req = CommunicationService.getRequest(type, url, reqData);
                log("URL", url);

                $http(req).then(function (msg) {
                    deferred.resolve(msg.data);

                }, function (err) {
                    deferred.reject(err);
                });
            });
            
            return deferred.promise;
        },
        
        globalReqData:function(){
            var topping = 0;
            var from_date = "1970-01-01";
            var to_date = "1970-01-31";
            
            if (arguments.length === 0 || arguments.length === 1) { 
                topping = arguments[0];
                var _DATE_ = new Date();
                _DATE_ = addDays(_DATE_, 1);
                
                var _year = _DATE_.getFullYear();
                var _month = _DATE_.getMonth() + 1;
                var _dayOfMonth = _DATE_.getDate();
                
                _month = (_month<10) ? "0" +_month : _month;
                
                to_date = _year + "-" + _month + "-" + _dayOfMonth;
                
            } else if( arguments.length > 1 ){ // requires two parameter first one is topping, and second one is selected date
                topping = arguments[0];
                var selected_date = arguments[1];
                
                
                var dateArray = selected_date.split(" ");
                var _d = new Date(dateArray[1], parseInt(getMonthIndex( dateArray[0] ), 10), 0);
                
                
                from_date = dateArray[1] + "-" + getMonthIndex( dateArray[0] ) + "-01";
                to_date = dateArray[1] + "-" + getMonthIndex( dateArray[0] )  + "-" + _d.getDate();
                
            }
            
            
            var req = {"FROM_DATE":from_date, "TO_DATE":to_date, "TOPPING": topping, "month_selected":"2017-11-01"};
            return req;
        }
        
        
    };
});


app.factory('autoCompleteDataService', [function () {
    return {
        getSource: function () {
            //this is where you'd set up your source... could be an external source, I suppose. 'something.php'
            //return ['apples', 'oranges', 'bananas'];
            /*return [
                {name:"Shahadat", mobile:"01824880161", age:"28", dob:"", gender:"Male"},
                {name:"Imtiaz", mobile:"01824880162", age:"28", dob:"", gender:"Male"},
                {name:"Jubayer", mobile:"01824880163", age:"28", dob:"", gender:"Male"},
                {name:"Sharif", mobile:"01824880164", age:"28", dob:"", gender:"Male"},
                {name:"Awlad Hossain", mobile:"01824880165", age:"28", dob:"", gender:"Male"},
                {name:"Tonmoy", mobile:"01824880165", age:"28", dob:"", gender:"Male"}
            ];*/
            return [
                {
                  value: "jquery",
                  label: "jQuery",
                  desc: "the write less, do more, JavaScript library",
                  icon: "jquery_32x32.png"
                },
                {
                  value: "jquery-ui",
                  label: "jQuery UI",
                  desc: "the official user interface library for jQuery",
                  icon: "jqueryui_32x32.png"
                },
                {
                  value: "sizzlejs",
                  label: "Sizzle JS",
                  desc: "a pure-JavaScript CSS selector engine",
                  icon: "sizzlejs_32x32.png"
                }
              ];
        }
    };
}]);

app.factory("AutoCompleteService", ["$http", function ($http) {
    return {
        search: function (term) {
            /*return $http.get("http://YourServiceUrl.com/" + term).then(function (response) {
                return response.data;
            });*/
            
            return [
                {"name":"Shahadat", "mobile":"01824880161", "age":"28", "dob":"", "gender":"Male"},
                {"name":"Imtiaz", "mobile":"01824880162", "age":"28", "dob":"", "gender":"Male"},
                {"name":"Jubayer", "mobile":"01824880163", "age":"28", "dob":"", "gender":"Male"},
                {"name":"Sharif", "mobile":"01824880164", "age":"28", "dob":"", "gender":"Male"},
                {"name":"Awlad Hossain", "mobile":"01824880165", "age":"28", "dob":"", "gender":"Male"},
                {"name":"Tonmoy", "mobile":"01824880165", "age":"28", "dob":"", "gender":"Male"}
            ];
            
        }
    };
}]);

app.factory('ClientService', function ($q, $filter){
    return {
        setLocalStorage: function (_key, _stringValue) {
            window.localStorage[_key] = _stringValue;
        },
        getLocalStorage: function (_key) {
            if (window.localStorage[_key]) {
                var _resp = window.localStorage[_key];
            } else {
                // var _resp = {};
                var _resp = "";
            }
            return _resp;

        },
        getLocalStorageJSON: function (_key) {
            if (window.localStorage[_key]) {
                var _resp = window.localStorage[_key];
            } else {
                var _resp = "{}";
            }
            return _resp;

        },
        clearLocalStorage: function () {
            window.localStorage.clear();
        },
        clearLocalStorageByKey: function (param) {
            window.localStorage.removeItem(param);
        },
        
        setUser: function (user) {
            window.localStorage['uSrLoggDIfo'] = JSON.stringify(user);
        },
        
        getUser: function () {
            var _resp = window.localStorage['uSrLoggDIfo'];
            return JSON.parse(_resp);
        },
        
        isLoggedIn: function () {
            var _resp = window.localStorage['uSrLoggDIfo'];
            var usr = JSON.parse(_resp);
            
            if (usr.user_id !== "") {
                return true;
            } else {
                return false;
            }
        },

        isRegistered: function () {
            if (window.localStorage['UserLGOIN']) {
                return true;
            } else {
                return false;
            }
        },

        hasPermission: function (module, controller, component) {
            var features = JSON.parse( window.localStorage[KEY.LOCAL.features] );
            var feature=features[module+'##'+component];
            if(feature===undefined){
                return false;
            }
            else return true;
        },
        featureName: function (module,component) {
            
            try{
                var features = JSON.parse( window.localStorage[KEY.LOCAL.features] );
                return features[module+'##'+component].feature_name;
            } catch (e){
                return "";
            }
            
        },
        log: function (tag,messaage) {
             log(tag,messaage);
        }
    };
});

app.factory('DialogBox', function ($q, $mdDialog, $timeout, ngDialog, ngToast){
    return {
        
        toast: function (_title, _message) {
            ngDialog.open({
                template: '\
                <h4>' + _title + '</h4>\
                <p>' + _message + '</p>\
                <div class="ngdialog-buttons">\
                    <button type="button" class="ngdialog-button ngdialog-button-primary" ng-click="closeThisDialog(0)">Ok</button>\
                </div>',
                plain: true,
                className: 'ngdialog-theme-default'
            });
        },
        alert: function (_title, _message) {
            var _head = '';
            if( _title.toUpperCase() === 'SUCCESS' ){
                _head = '<h4 class="text-success">' + _title + '</h4>';
                
            } else if( _title.toUpperCase() === 'WARNING' ){
                _head = '<h4 class="text-warning">' + _title + '</h4>';
                
            } else if( _title.toUpperCase() === 'ERROR' ){
                _head = '<h4 class="text-danger">' + _title + '</h4>';
                
            } else{
                _head = '<h4>' + _title + '</h4>';
            }
                
            ngDialog.open({
                template: _head + '\
                <p>' + _message + '</p>\
                <div class="ngdialog-buttons">\
                    <button type="button" class="ngdialog-button ngdialog-button-primary" ng-click="closeThisDialog(0)">Ok</button>\
                </div>',
                plain: true,
                className: 'ngdialog-theme-default'
            });
        },
        confirm: function (_message) {
            var nestedConfirmDialog = ngDialog.openConfirm({
                template:'\
                    <h4>Performing a serious action</h4>\
                    <p>' + _message + '</p>\
                    <div class="ngdialog-buttons">\
                        <button type="button" class="ngdialog-button ngdialog-button-primary" ng-click="confirm(1)">Yes</button>\
                        <button type="button" class="ngdialog-button ngdialog-button-secondary" ng-click="closeThisDialog(0)">No</button>\
                    </div>',
                plain: true
            });
 
            // NOTE: return the promise from openConfirm
            return nestedConfirmDialog;
        },
        show: function(content, title){
            var _title = ( typeof title === 'undefined' ) ? 'eHospital Dialog':title;
            ngDialog.open({
                template: '<h4>' + _title + '</h4>' + content,
                plain: true,
                width: 550,
                className: 'ngdialog-theme-default'
            });
        },
        showProgress:function(){
            
            if( !isLoadingShown ){
                
                log("dialog", "showing");
                isLoadingShown = true;
                
                $mdDialog.show({
                    template:'<md-dialog style="background-color:transparent;box-shadow:none;overflow: hidden !important;">' +
                                '<div layout="row" layout-sm="column" layout-align="center center" aria-label="wait">' +
                                    '<md-progress-circular class="md-hue-2" md-diameter="50px"></md-progress-circular>' +
                                '</div>' +
                            '</md-dialog>',
                    parent: angular.element(document.body),
                    clickOutsideToClose:false,
                    escapeToClose: false,
                    fullscreen: false
                });
                
                log("dialog", "shown");
            }
        },
        hideProgress:function(){
            $mdDialog.cancel();
            log("dialog", "closed");
            isLoadingShown = false;
        }
    };
});


app.factory("encrypt", function () {
    return {
        SHA256: function (data) {

            var rotateRight = function (n, x) {
                return ((x >>> n) | (x << (32 - n)));
            }
            var choice = function (x, y, z) {
                return ((x & y) ^ (~x & z));
            }
            function majority(x, y, z) {
                return ((x & y) ^ (x & z) ^ (y & z));
            }
            function sha256_Sigma0(x) {
                return (rotateRight(2, x) ^ rotateRight(13, x) ^ rotateRight(22, x));
            }
            function sha256_Sigma1(x) {
                return (rotateRight(6, x) ^ rotateRight(11, x) ^ rotateRight(25, x));
            }
            function sha256_sigma0(x) {
                return (rotateRight(7, x) ^ rotateRight(18, x) ^ (x >>> 3));
            }
            function sha256_sigma1(x) {
                return (rotateRight(17, x) ^ rotateRight(19, x) ^ (x >>> 10));
            }
            function sha256_expand(W, j) {
                return (W[j & 0x0f] += sha256_sigma1(W[(j + 14) & 0x0f]) + W[(j + 9) & 0x0f] +
                        sha256_sigma0(W[(j + 1) & 0x0f]));
            }

            /* Hash constant words K: */
            var K256 = new Array(
                    0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5,
                    0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
                    0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3,
                    0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
                    0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc,
                    0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
                    0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7,
                    0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
                    0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13,
                    0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
                    0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3,
                    0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
                    0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5,
                    0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
                    0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208,
                    0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
                    );

            /* global arrays */
            var ihash, count, buffer;
            var sha256_hex_digits = "0123456789abcdef";

            /* Add 32-bit integers with 16-bit operations (bug in some JS-interpreters: 
             overflow) */
            function safe_add(x, y)
            {
                var lsw = (x & 0xffff) + (y & 0xffff);
                var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
                return (msw << 16) | (lsw & 0xffff);
            }

            /* Initialise the SHA256 computation */
            function sha256_init() {
                ihash = new Array(8);
                count = new Array(2);
                buffer = new Array(64);
                count[0] = count[1] = 0;
                ihash[0] = 0x6a09e667;
                ihash[1] = 0xbb67ae85;
                ihash[2] = 0x3c6ef372;
                ihash[3] = 0xa54ff53a;
                ihash[4] = 0x510e527f;
                ihash[5] = 0x9b05688c;
                ihash[6] = 0x1f83d9ab;
                ihash[7] = 0x5be0cd19;
            }

            /* Transform a 512-bit message block */
            function sha256_transform() {
                var a, b, c, d, e, f, g, h, T1, T2;
                var W = new Array(16);

                /* Initialize registers with the previous intermediate value */
                a = ihash[0];
                b = ihash[1];
                c = ihash[2];
                d = ihash[3];
                e = ihash[4];
                f = ihash[5];
                g = ihash[6];
                h = ihash[7];

                /* make 32-bit words */
                for (var i = 0; i < 16; i++)
                    W[i] = ((buffer[(i << 2) + 3]) | (buffer[(i << 2) + 2] << 8) | (buffer[(i << 2) + 1]
                            << 16) | (buffer[i << 2] << 24));

                for (var j = 0; j < 64; j++) {
                    T1 = h + sha256_Sigma1(e) + choice(e, f, g) + K256[j];
                    if (j < 16)
                        T1 += W[j];
                    else
                        T1 += sha256_expand(W, j);
                    T2 = sha256_Sigma0(a) + majority(a, b, c);
                    h = g;
                    g = f;
                    f = e;
                    e = safe_add(d, T1);
                    d = c;
                    c = b;
                    b = a;
                    a = safe_add(T1, T2);
                }

                /* Compute the current intermediate hash value */
                ihash[0] += a;
                ihash[1] += b;
                ihash[2] += c;
                ihash[3] += d;
                ihash[4] += e;
                ihash[5] += f;
                ihash[6] += g;
                ihash[7] += h;
            }

            /* Read the next chunk of data and update the SHA256 computation */
            function sha256_update(data, inputLen) {
                var i, index, curpos = 0;
                /* Compute number of bytes mod 64 */
                index = ((count[0] >> 3) & 0x3f);
                var remainder = (inputLen & 0x3f);

                /* Update number of bits */
                if ((count[0] += (inputLen << 3)) < (inputLen << 3))
                    count[1]++;
                count[1] += (inputLen >> 29);

                /* Transform as many times as possible */
                for (i = 0; i + 63 < inputLen; i += 64) {
                    for (var j = index; j < 64; j++)
                        buffer[j] = data.charCodeAt(curpos++);
                    sha256_transform();
                    index = 0;
                }

                /* Buffer remaining input */
                for (var j = 0; j < remainder; j++)
                    buffer[j] = data.charCodeAt(curpos++);
            }

            /* Finish the computation by operations such as padding */
            function sha256_final() {
                var index = ((count[0] >> 3) & 0x3f);
                buffer[index++] = 0x80;
                if (index <= 56) {
                    for (var i = index; i < 56; i++)
                        buffer[i] = 0;
                } else {
                    for (var i = index; i < 64; i++)
                        buffer[i] = 0;
                    sha256_transform();
                    for (var i = 0; i < 56; i++)
                        buffer[i] = 0;
                }
                buffer[56] = (count[1] >>> 24) & 0xff;
                buffer[57] = (count[1] >>> 16) & 0xff;
                buffer[58] = (count[1] >>> 8) & 0xff;
                buffer[59] = count[1] & 0xff;
                buffer[60] = (count[0] >>> 24) & 0xff;
                buffer[61] = (count[0] >>> 16) & 0xff;
                buffer[62] = (count[0] >>> 8) & 0xff;
                buffer[63] = count[0] & 0xff;
                sha256_transform();
            }

            /* Split the internal hash values into an array of bytes */
            function sha256_encode_bytes() {
                var j = 0;
                var output = new Array(32);
                for (var i = 0; i < 8; i++) {
                    output[j++] = ((ihash[i] >>> 24) & 0xff);
                    output[j++] = ((ihash[i] >>> 16) & 0xff);
                    output[j++] = ((ihash[i] >>> 8) & 0xff);
                    output[j++] = (ihash[i] & 0xff);
                }
                return output;
            }

            /* Get the internal hash as a hex string */
            function sha256_encode_hex() {
                var output = new String();
                for (var i = 0; i < 8; i++) {
                    for (var j = 28; j >= 0; j -= 4)
                        output += sha256_hex_digits.charAt((ihash[i] >>> j) & 0x0f);
                }
                return output;
            }



            /* test if the JS-interpreter is working properly */
            function sha256_self_test()
            {
                return sha256_digest("message digest") ==
                        "f7846f55cf23e14eebeab5b4e1550cad5b509e3348fbc4efa3a1413d393cb650";
            }

            sha256_init();
            sha256_update(data, data.length);
            sha256_final();
            return sha256_encode_hex();

        }

    };

});

//window.open(url, "print", "directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=500,height=450,top=100,left=500");

function printPage(fullHtml) {
    var newWin = window.open('', 'Print-Window');
    newWin.document.open();
    newWin.document.write(fullHtml);
    newWin.document.close();
    setTimeout(function() {
      newWin.close();
    }, 300);
}

function printElement(content) {
    var newWin = window.open('', 'Print-Window');
    newWin.document.open();
    newWin.document.write('<html><body onload="window.print()">' + content + '</body></html>');
    newWin.document.close();
    setTimeout(function() {
      newWin.close();
    }, 300);
}



function log(tag,messaage) {
    console.log(tag,messaage);
}