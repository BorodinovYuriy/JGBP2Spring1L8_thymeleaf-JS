
package ru.gb.buv.spring_lesson8.dao_repository;

import org.springframework.data.jpa.repository.*;
import ru.gb.buv.spring_lesson8.entity.Product;

import java.util.List;

/*@Repository//-не обязательная, но наглядненько*/
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostBetween(Long min, Long max);


}
