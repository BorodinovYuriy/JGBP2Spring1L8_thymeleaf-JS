angular.module('app',[]).controller('indexController', function($scope, $http){
    const contextPath = 'http://localhost:8080/app';

/*запрос списка продуктов*/
    $scope.fillTable = function() {
        $http.get(contextPath)
            .then(function(response) {
            $scope.ProductList = response.data;
            });
    }
//--------------------------------------------
    /*Пагинация*/
        $scope.pageN = function() {
            $http.get(contextPath + '/inc')
                      .then(function(response) {
                                  location.reload();
                                  });
        }
        $scope.pageP = function() {
            $http.get(contextPath + '/dec')
                       .then(function(response) {
                                   location.reload();
                                   });
        }
//------------------------------------------

/*удаление продукта*/
    $scope.deleteProductById = function(productId){
            $http.post(contextPath + '/delete', productId)
            .then(function(response) {
                $scope.fillTable();
            });
    }
/*добавление продукта*/
    $scope.submitCreateNewProduct = function(){
    /*alert("Отправка!"+ $scope.newProduct);*/
                $http.post(contextPath + '/add', $scope.newProduct)
                    .then(function(response) {
                    $scope.fillTable();
                });
        }
    $scope.fillTable();
});
