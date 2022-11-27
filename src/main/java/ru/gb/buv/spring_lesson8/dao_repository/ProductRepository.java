
package ru.gb.buv.spring_lesson8.dao_repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import ru.gb.buv.spring_lesson8.entity.Product;

import org.springframework.data.domain.Pageable;
import java.util.List;

/*@Repository//-не обязательная, но наглядненько*/
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostBetween(Long min, Long max);

}
