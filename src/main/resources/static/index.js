angular.module('app',[]).controller('indexController', function($scope, $http){
    const contextPath = 'http://localhost:8080/app';
    $scope.offsetPage = 0;

/*запрос списка продуктов НАЧАЛЬНЫЙ*/
    $scope.fillTable = function() {
        $http.get(contextPath)
            .then(function(response) {
            $scope.ProductList = response.data;
            });
    }
/*запрос списка СТРАНИЦЫ*/
        $scope.change_page = function(pageVar, limit) {
        //Без проверок и алертов, главное - работает:)))
            $scope.offsetPage = $scope.offsetPage + pageVar;
                $http({
                    url: contextPath +'/change_page',
                    method: 'GET',
                    params: {
                        offset: $scope.offsetPage,
                        limit: limit
                    }
                }).then(function(response) {
                                  $scope.ProductList = response.data;
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
