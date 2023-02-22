package jpabook2.jpashop2.domain.item;

import jakarta.persistence.*;
import jpabook2.jpashop2.domain.Category;
import jpabook2.jpashop2.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //comment: 비즈니스 로직
    /**
     * stock 감소
     */
    public void reduceStock(int stockQuantity) {
        int restStock = this.stockQuantity - stockQuantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("restStock is smaller than 0");
        }
        this.stockQuantity = restStock;
    }

    /**
     * stock 증가
     */
    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }
}