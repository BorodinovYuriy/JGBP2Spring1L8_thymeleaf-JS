
package ru.gb.buv.spring_lesson8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.buv.spring_lesson8.dao_repository.ProductRepository;
import ru.gb.buv.spring_lesson8.entity.Product;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    //*****************************************
    public List<Product> getProductList() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        //только таким можно запросом вернуть (не как на уроке), так как есть вариант с null...
        return productRepository.findById(id).get();
    }
    public Product createProduct(String title, Long cost){
        Product product = new Product();
        product.setTitle(title);
        product.setCost(cost);
        return product;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> filterCostAsc() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,"cost"));
    }

    public List<Product> filterCostDesc() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC,"cost"));
    }


    public List<Product> filterCostRange(Long min, Long max) {
        return productRepository.findAllByCostBetween(min,max);

    }
    /*1. Создать сущность «Товар» (id, название, стоимость) и соответствующую таблицу в БД.
    Заполнить таблицу тестовыми данными (20 записей).*/
    //*********Код начальной инициализации***********
    @Transactional//-по логике - надо...
    @EventListener(ApplicationReadyEvent.class)
    private void fillDataBaseAfterStartApplication(){
        for (int i = 0; i < 100; i++) {
            productRepository.save(createProduct("prod-"+i,100L+i+i));
        }
    }
    //Save method
    public void saveProduct(Product product){
        productRepository.save(product);
    }
}
