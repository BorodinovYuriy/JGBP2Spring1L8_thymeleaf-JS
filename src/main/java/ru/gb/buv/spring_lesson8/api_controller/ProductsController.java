
package ru.gb.buv.spring_lesson8.api_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.buv.spring_lesson8.entity.Product;
import ru.gb.buv.spring_lesson8.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/app")//-отправная точка!!!
public class ProductsController {
    @Autowired
    ProductService productService;
//*********************************
    //Получение списка продукта
    @GetMapping
    public List<Product> getProductList(){
        return productService.getProductList();
    }
    //Получение сущности по id
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productService.getProductById(id);
        /*throw new UnsupportedOperationException();*/
    }
    //Добавление
    @GetMapping ("/add/{title}/{cost}")
    public List<Product> addNewProduct(@PathVariable String title
            ,@PathVariable Long cost){
        productService.saveProduct(productService.createProduct(title,cost));
        return productService.getProductList();
    }
    //Удаление
    @GetMapping("/delete/{id}")
    public List<Product> deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        return productService.getProductList();
    }

    //Фильтрация
    @GetMapping("filter/cost/asc")
    public List<Product> filterCostAsc(){
        return productService.filterCostAsc();
    }
    @GetMapping("filter/cost/desc")
    public List<Product> filterCostDesc(){
        return productService.filterCostDesc();
    }
    @GetMapping("filter/cost/range/{min}/{max}")
    public List<Product> filterCostRange(@PathVariable Long min, @PathVariable Long max){
        return productService.filterCostRange(min,max);
    }

}
