/**
 * Created by Administrator on 2016/12/27.
 */

spApp.controller('IndexCtrl',['$scope','$http',
    function($scope,$http){
        var url = baseUrl + dataUrl.customer.show;
        $scope.dataSize = 0;
        var query = '';
        $http.post(url, query, configJson)
            .success(function (data) {
                $scope.customer = data;
            });
    }
]);