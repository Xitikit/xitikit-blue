app.factory('TestingService', ['$http', '$q', function($http, $q){
    return {
        performSearch: function(searchInput){

            var defer = $q.defer();
            $http({
                url: API_URL + "/search",
                method: "POST",
                data: searchInput,
                headers: {
                    "Content-Type": "application/json"
                }
            }).then(searchSuccess, searchFailure);

            return defer.promise;

            function searchSuccess(response){
                return defer.resolve(response.data);
            }

            function searchFailure(rejection){
                return defer.reject(rejection);
            }
        },

        lookupClient: function(email){

            var defer = $q.defer();
            $http({
                url: API_URL + "/clientLookup",
                method: "POST",
                data: email,
                headers: {
                    "Content-Type": "application/json"
                }
            }).then(lookupClientSuccess, lookupClientFailure);

            return defer.promise;

            function lookupClientSuccess(response){
                return defer.resolve(response.data);
            }

            function lookupClientFailure(rejection){
                return defer.reject(rejection);
            }
        },

        deleteClient: function(webAccountId){

            var defer = $q.defer();
            $http({
                url: API_URL + "/client/" + webAccountId,
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            }).then(deleteClientSuccess, deleteClientFailure);

            return defer.promise;

            function deleteClientSuccess(response){
                return defer.resolve(response.data);
            }

            function deleteClientFailure(rejection){
                return defer.reject(rejection);
            }
        },

        disconnectClient: function(webAccountId){

            var defer = $q.defer();
            $http({
                url: API_URL + "/client/" + webAccountId + "/disconnect",
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                }
            }).then(disconnectClientSuccess, disconnectClientFailure);

            return defer.promise;

            function disconnectClientSuccess(response){
                return defer.resolve(response.data);
            }

            function disconnectClientFailure(rejection){
                return defer.reject(rejection);
            }
        },

        reassignClient: function(webAccountId, selectedPerson){

            var defer = $q.defer();
            $http({
                url: API_URL + "/client/" + webAccountId + "/reassign",
                method: "POST",
                data: selectedPerson,
                headers: {
                    "Content-Type": "application/json"
                }
            }).then(reassignClientSuccess, reassignClientFailure);

            return defer.promise;

            function reassignClientSuccess(response){
                return defer.resolve(response.data);
            }

            function reassignClientFailure(rejection){
                return defer.reject(rejection);
            }
        }
    }
}]);