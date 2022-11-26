
package ru.gb.buv.spring_lesson8.entity;

import lombok.Data;
import javax.persistence.*;
/*    1. Создать сущность «Товар» (id, название, стоимость) и соответствующую таблицу в БД.
            Заполнить таблицу тестовыми данными (20 записей).*/

//@Builder//-билдер (***.builder()***.build();) -> там, где @NoArgsConstructor -билдер не поставишь
@Data
@Entity//-сущность
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//-последовательно генерит значения
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Long cost;
}
