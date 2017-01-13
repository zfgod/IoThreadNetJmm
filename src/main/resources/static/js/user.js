/**
 * Created by Administrator on 2016/12/27.
 */
spApp.controller('UserCtrl',['$http','$scope',
    function($http,$scope){
        var listUrl = baseUrl+dataUrl.users.list;
        $http.post(listUrl,null,configForm)
            .success(function(data){
             $scope.uList = data.list;
        });
    }
]);