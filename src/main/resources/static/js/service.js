/*app.service("myAjax", function($q,$http) {


    var ajax = function(method, params, data) {

		var url = "http://localhost:8200/ticketingsystem/utente/";
        var deferred = $q.defer();
        var request = {
            method: method,
            url: url,
            headers: {
                'Content-Type': 'application/json'
            }
        };
	
        if (method === "POST") {
            request.data = data;
        } else if (method === "PUT") {
            request.data = data; 
            request.params = params; 
		} else {
			request.params = params; 
		}
	
        $http(request).then(function(response){
            deferred.resolve(response.data);
        },function(response, status){
            deferred.reject({data: response, status: status});
        });
        return deferred.promise;
    };

	

    this.get = function(params) {
        return ajax("GET", params, null);
    };
	
    this.post = function(data) {
        return ajax("POST", null, data);
    };

	this.put = function(data) {
		return ajax("PUT", params, data);
	};

	this.delete = function(params) {
		return ajax("DELETE", params, null);
	};	
});




app.service("simpleAjax", function($http) {

	var ajax = function(method,url,data) {
		var request = {
			method: method,
			url: url,
			headers: {
				'Content-Type': 'application/json'
			}
        };
		return $http(request);
	};


	this.get = function(params) {
		return ajax("GET", "http://localhost:8200/ticketingsystem/persona/3", params);
	};
	
    	this.post = function(data) {
        	return ajax("POST", "/ticketingsystem/persona", data);
    	};
});
*/

app.service('utenteService', function ($http) {
    //**********----Get All Record----***************
    var urlGet = '';
    this.getAll = function (apiRoute) {
        urlGet = apiRoute;
        return $http.get(urlGet);
    }
});
