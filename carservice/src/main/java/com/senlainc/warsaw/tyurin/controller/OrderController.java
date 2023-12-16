package com.senlainc.warsaw.tyurin.controller;

import com.senlainc.warsaw.tyurin.dto.OrderDto;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.service.IOrderService;
import com.senlainc.warsaw.tyurin.util.builder.OrderBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("orders")
public class OrderController {

    private final OrderBuilder orderBuilder;
    private IOrderService orderService;

    public OrderController(OrderBuilder orderBuilder,
                           IOrderService orderService) {
        this.orderService = orderService;
        this.orderBuilder = orderBuilder;
    }

    @ResponseBody
    @PostMapping()
    public void create(@Valid @RequestBody OrderDto orderDto) {
        Order order = orderBuilder.build(orderDto);
        orderService.addOrder(order);
    }

    @ResponseBody
    @GetMapping("{id}")
    public Order find(@PathVariable long id) {
        return orderService.getOrderById(id);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        orderService.removeOrder(id);
    }
}
