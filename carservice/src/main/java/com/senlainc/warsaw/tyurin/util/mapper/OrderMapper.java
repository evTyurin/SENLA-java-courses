package com.senlainc.warsaw.tyurin.util.mapper;

import com.senlainc.warsaw.tyurin.dto.OrderDto;
import com.senlainc.warsaw.tyurin.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Validated
public class OrderMapper {

    private final GaragePlaceMapper garagePlaceMapper;

    @Autowired
    public OrderMapper(GaragePlaceMapper garagePlaceMapper) {
        this.garagePlaceMapper = garagePlaceMapper;
    }

    public List<OrderDto> mapToEntity(List<Order> orders) {
        return orders
                .stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }

    public Order mapToEntity(@Valid OrderDto dto) {
        Order order = new Order();
        order.setOrderStatus(dto.getOrderStatus());
        order.setGaragePlace(garagePlaceMapper.mapToEntity(dto.getGaragePlace()));
        order.setCompletionDate(dto.getCompletionDate());
        order.setPrice(dto.getPrice());
        order.setStartDate(dto.getStartDate());
        return order;
    }

    public OrderDto mapToEntity(Order order) {
        OrderDto dto = new OrderDto();
        dto.setCompletionDate(order.getCompletionDate());
        dto.setId(order.getId());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setStartDate(order.getStartDate());
        dto.setSubmissionDate(order.getSubmissionDate());
        dto.setGaragePlace(garagePlaceMapper.mapToEntity(order.getGaragePlace()));
        return dto;
    }
}
