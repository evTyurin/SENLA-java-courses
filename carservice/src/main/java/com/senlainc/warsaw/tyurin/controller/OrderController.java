package com.senlainc.warsaw.tyurin.controller;

import com.senlainc.warsaw.tyurin.dto.OrderDto;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.exception.ExpectationFailedException;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.service.IOrderService;
import com.senlainc.warsaw.tyurin.util.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderMapper orderMapper;
    private IOrderService orderService;

    public OrderController(OrderMapper orderMapper,
                           IOrderService orderService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping()
    public void create(@Valid @RequestBody OrderDto orderDto) throws NotFoundException {
        Order order = orderMapper.mapToEntity(orderDto);
        orderService.addOrder(order);
    }

    @GetMapping("{id}")
    public OrderDto find(@PathVariable long id) throws NotFoundException {
        return orderMapper.mapToEntity(orderService.getOrderById(id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) throws NotFoundException {
        orderService.removeOrder(id);
    }

    @GetMapping("archived/sort/{criteria}")
    public List<OrderDto> getArchivedOrdersSortByCriteria(@PathVariable String criteria) throws ExpectationFailedException {
        return orderMapper.mapToEntity(orderService.getArchivedOrdersSortByCriteria(criteria));
    }

    @GetMapping("currently-executed/sort/{criteria}")
    public List<OrderDto> getCurrentlyExecutedOrdersSortedByCriteria(@PathVariable String criteria) throws ExpectationFailedException {
        return orderMapper.mapToEntity(orderService.getCurrentlyExecutedOrdersSortedByCriteria(criteria));
    }

    @GetMapping("sort/{criteria}")
    public List<OrderDto> getAllOrdersSortedByCriteria(@PathVariable String criteria) throws ExpectationFailedException {
        return orderMapper.mapToEntity(orderService.getSortedAllOrdersByCriteria(criteria));
    }

    @PatchMapping()
    public void updateOrderParameter(@Valid @RequestBody OrderDto orderDto) throws NotFoundException {
        orderService.updateOrder(orderMapper.mapToEntity(orderDto));
    }
}
