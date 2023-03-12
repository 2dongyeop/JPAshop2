package jpabook2.jpashop2.repository;

import jpabook2.jpashop2.domain.OrderStatus;
import lombok.Getter;

@Getter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
