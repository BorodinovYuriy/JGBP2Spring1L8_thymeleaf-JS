
package ru.gb.buv.spring_lesson8.api_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.buv.spring_lesson8.entity.Product;
import ru.gb.buv.spring_lesson8.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/app") // http://localhost:8080/ - отправная точка (index.html)!!!
public class ProductsController {
    Integer offset = 0;
    Integer limit = 10;
    @Autowired
    ProductService productService;
//*********************************
    //Получение списка продукта(index.html)
/*----------------
@GetMapping
public List<Product> onStartApp(){
    return productService.getProductList();
}
    @GetMapping("/{ProductPage}")
    public List<Product> getProductList(@PathVariable(required = false) Long productPage){
        *//*Long range;
        if(productPage == null){
            range = 0L;
        }else {
            range = productPage;
        }*//*

       *//* Long count = productService.getCount() / countRange;
        System.out.println(count);*//*

        *//*return productService.getRangedList(range, countRange);*//*
        return productService.getProductList();
    }-------------------*/

    //Пагинация
    @GetMapping
    public List<Product> getPartList(
            /*@RequestParam*//*(value = "offset", defaultValue = "0")*//* Integer offset,
            @RequestParam*//*(value = "limit", defaultValue = "10")*//* Integer limit*/){
        List<Product> partList = productService.getPage(offset,limit).stream().toList();
        return partList;
    }
    @GetMapping("/inc")
    public Integer increment(){
        offset++;
        return offset;
    }
    @GetMapping("/dec")
    public Integer decrement(){
        if(offset - 1 >= 0){
            offset--;
        }else{
            offset = 0;
        }
        return offset;
    }

    //Получение сущности по id
    @GetMapping("/get/{id}")
    public Product getById(@PathVariable Long id){
        return productService.getProductById(id);
        /*throw new UnsupportedOperationException();*/
    }
    //Добавление
    //Из JS приходит post, в теле которого Product newProduct для добавления
    @PostMapping("/add")
    public void addNewProduct(@RequestBody Product newProduct){
        productService.saveProduct(newProduct);
        productService.getProductList();
    }
    //Удаление
    //Из JS приходит post, в теле которого productId для удаления
    @PostMapping("/delete")
    public List<Product> deleteProductById(@RequestBody Long productId){
        productService.deleteProductById(productId);
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
