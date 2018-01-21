app.controller('TestingController', ['$scope', '$log', 'usSpinnerService', 'TestingService', function($scope, $log, usSpinnerService, TestingService){
    $scope.emailAddress = '';
    $scope.searchInput = {};
    $scope.currentUserName = '';
    $scope.currentWebAccountId = '';
    $scope.goodMessage = '';
    $scope.badMessage = '';
    $scope.foundWebAccount = false;
    $scope.searchResults = [];
    $scope.selectedSearchResult = false;
    $scope.orderByField = 'firstName';
    $scope.reverseSort = false;

    $scope.onSearchRowSelect = function(searchResult){
        for(var i = 0; i < $scope.searchResults.length; i++){
            $scope.searchResults[i].checked = false;
        }

        $scope.selectedSearchResult = true;
        searchResult.checked = true;
    };

    $scope.onSearch = function(){
        usSpinnerService.spin('loading-spinner');
        TestingService.performSearch($scope.searchInput).then(performSearchSuccess, performSearchFailure);

        function performSearchSuccess(data){
            usSpinnerService.stop('loading-spinner');
            $scope.searchResults = data;
            $scope.selectedSearchResult = false;
        }

        function performSearchFailure(rejection){
            usSpinnerService.stop('loading-spinner');
            $log.warn(rejection);
            setBadMessage('Failed to perform the search.');
        }
    };

    $scope.onReassign = function(){
        if($scope.selectedSearchResult){
            var selectedPerson;
            for(var i = 0; i < $scope.searchResults.length; i++){
                if($scope.searchResults[i].checked){
                    selectedPerson = $scope.searchResults[i];
                }
            }

            TestingService.reassignClient($scope.currentWebAccountId, selectedPerson).then(onReassignSuccess, onReassignFailure);
            usSpinnerService.spin('loading-spinner');

            function onReassignSuccess(data){
                usSpinnerService.stop('loading-spinner');
                if(data.successful){
                    setGoodMessage('Successfully reassigned web account.');
                }
                else{
                    setBadMessage('Failed to reassign web account with this message: ' + data.message);
                }
            }

            function onReassignFailure(rejection){
                usSpinnerService.stop('loading-spinner');
                $log.warn(rejection);
                setBadMessage('Failed to reassign web account.');
            }
        }
    };

    $scope.onClientLookup = function(){
        if(!validateEmail($scope.emailAddress)){
            setBadMessage('Invalid email address.');
            $scope.currentUserName = '';
            $scope.foundWebAccount = false;
            $scope.currentWebAccountId = '';
            return;
        }

        usSpinnerService.spin('loading-spinner');
        TestingService.lookupClient($scope.emailAddress).then(clientLookupSuccess, clientLookupFailure);

        function clientLookupSuccess(data){
            usSpinnerService.stop('loading-spinner');
            if(data.foundAccount){
                $scope.currentUserName = data.userName;
                $scope.foundWebAccount = true;
                $scope.currentWebAccountId = data.webAccountId;
                if(data.accountLinked){
                    setGoodMessage("Found web account.");
                }
                else{
                    setGoodMessage("Not Found web account.");
                }
            }
            else{
                $scope.currentUserName = '';
                setBadMessage('Unable to find web account.');
                $scope.foundWebAccount = false;
                $scope.currentWebAccountId = '';
            }
        }

        function clientLookupFailure(rejection){
            usSpinnerService.stop('loading-spinner');
            $log.warn(rejection);
            setBadMessage('An error occurred while looking up web account.');
        }
    };

    $scope.onClientDelete = function(){
        usSpinnerService.spin('loading-spinner');
        TestingService.deleteClient($scope.currentWebAccountId).then(clientDeleteSuccess, clientDeleteFailure);

        function clientDeleteSuccess(){
            usSpinnerService.stop('loading-spinner');
            setGoodMessage('Successfully deleted web account.');
            $scope.foundWebAccount = false;
            $scope.currentWebAccountId = '';
            $scope.currentUserName = '';
        }

        function clientDeleteFailure(rejection){
            usSpinnerService.stop('loading-spinner');
            $log.warn(rejection);
            setBadMessage('Failed to delete web account.');
        }
    };

    $scope.onClientDisconnect = function(){
        usSpinnerService.spin('loading-spinner');
        TestingService.disconnectClient($scope.currentWebAccountId).then(clientDisconnectSuccess, clientDisconnectFailure);

        function clientDisconnectSuccess(){
            usSpinnerService.stop('loading-spinner');
            setGoodMessage('Successfully disconnected web account.');
        }

        function clientDisconnectFailure(rejection){
            usSpinnerService.stop('loading-spinner');
            $log.warn(rejection);
            setBadMessage('Failed to disconnect web account.');
        }
    };

    function setGoodMessage(message){
        $scope.goodMessage = message;
        $scope.badMessage = '';
    }

    function setBadMessage(message){
        $scope.goodMessage = '';
        $scope.badMessage = message;
    }

    function validateEmail(email){
        var re = /\S+@\S+\.\S+/;
        return re.test(email);
    }
}]);