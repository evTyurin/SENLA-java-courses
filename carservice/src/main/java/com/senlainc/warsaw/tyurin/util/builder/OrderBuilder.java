package com.senlainc.warsaw.tyurin.util.builder;

import com.senlainc.warsaw.tyurin.dto.OrderDto;
import com.senlainc.warsaw.tyurin.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public class OrderBuilder {

    private final CraftsmanBuilder craftsmanBuilder;
    private final GaragePlaceBuilder garagePlaceBuilder;

    @Autowired
    public OrderBuilder(CraftsmanBuilder craftsmanBuilder,
                        GaragePlaceBuilder garagePlaceBuilder) {
        this.craftsmanBuilder = craftsmanBuilder;
        this.garagePlaceBuilder = garagePlaceBuilder;
    }

    public Order build(@Valid OrderDto dto) {
        Order order = new Order();
        order.setOrderStatus(dto.getOrderStatus());
        order.setGaragePlace(garagePlaceBuilder.build(dto.getGaragePlace()));
        order.setCompletionDate(dto.getCompletionDate());
        order.setPrice(dto.getPrice());
        order.setStartDate(dto.getStartDate());
        return order;
    }

    public OrderDto build(Order order) {
        OrderDto dto = new OrderDto();
        dto.setCompletionDate(order.getCompletionDate());
        dto.setId(order.getId());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setStartDate(order.getStartDate());
        dto.setSubmissionDate(order.getSubmissionDate());
        dto.setGaragePlace(garagePlaceBuilder.build(order.getGaragePlace()));
        return dto;
    }
}
