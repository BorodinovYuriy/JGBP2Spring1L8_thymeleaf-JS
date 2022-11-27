
package ru.gb.buv.spring_lesson8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Long getCount() {
        return productRepository.count();
    }

    public Page<Product> getPage(Integer offset, Integer limit) {
        return productRepository.findAll(PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id")));

    }
    /*public List<Product> getRangedList(Long partitionIndex, Long countRange){
        Long startPosition = partitionIndex * countRange;
        return  productRepository.findAllByIdLimitAndOffset(countRange,startPosition);

    }*/

}
