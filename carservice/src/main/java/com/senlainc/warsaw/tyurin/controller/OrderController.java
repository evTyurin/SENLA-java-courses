package com.senlainc.warsaw.tyurin.controller;

import com.senlainc.warsaw.tyurin.dto.OrderDto;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.exception.CriteriaNotFoundException;
import com.senlainc.warsaw.tyurin.service.IOrderService;
import com.senlainc.warsaw.tyurin.util.builder.OrderBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public OrderDto find(@PathVariable long id) {
        return orderBuilder.build(orderService.getOrderById(id));
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        orderService.removeOrder(id);
    }

    @ResponseBody
    @GetMapping("archived/sort/{sort}")
    public List<OrderDto> getArchivedOrdersSortByCriteria(@PathVariable String criteria) throws CriteriaNotFoundException {
        if (criteria.equals("price")) {
            return orderBuilder.build(orderService
                    .getArchivedOrdersSortedByPrice());
        } else if (criteria.equals("completion-date")) {
            return orderBuilder.build(orderService
                    .getArchivedOrdersSortedByCompletionDate());
        } else if (criteria.equals("submission-date")) {
            return orderBuilder.build(orderService
                    .getArchivedOrdersSortedBySubmissionDate());
        }
        throw new CriteriaNotFoundException("update by " + criteria + " not available", 405);
    }

    @ResponseBody
    @GetMapping("currently-executed/sort/{sort}")
    public List<OrderDto> getCurrentlyExecutedOrdersSortByCriteria(@PathVariable String criteria) throws CriteriaNotFoundException {
        if (criteria.equals("price")) {
            return orderBuilder.build(orderService
                    .getCurrentlyExecutedOrdersSortedByPrice());
        } else if (criteria.equals("completion-date")) {
            return orderBuilder.build(orderService
                    .getArchivedOrdersSortedByCompletionDate());
        } else if (criteria.equals("submission-date")) {
            return orderBuilder.build(orderService
                    .getCurrentlyExecutedOrdersSortedBySubmissionDate());
        }
        throw new CriteriaNotFoundException("sort by " + criteria + " not available", 405);
    }

    @ResponseBody
    @GetMapping("sort/{sort}")
    public List<OrderDto> getOrdersSortByCriteria(@PathVariable String criteria) throws CriteriaNotFoundException {
        if (criteria.equals("price")) {
            return orderBuilder.build(orderService.getSortedByPrice());
        } else if (criteria.equals("completion-date")) {
            return orderBuilder.build(orderService.getSortedByCompletionDate());
        } else if (criteria.equals("submission-date")) {
            return orderBuilder.build(orderService.getSortedBySubmissionDate());
        } else if (criteria.equals("start-date")) {
            return orderBuilder.build(orderService.getSortedByStartDate());
        }
        throw new CriteriaNotFoundException("sort by " + criteria + " not available", 405);
    }

    @ResponseBody
    @PatchMapping()
    public void updateOrderParameter(@Valid @RequestBody OrderDto orderDto) throws CriteriaNotFoundException {
        if (orderDto.getId() > 0) {
            if (orderDto.getOrderStatus() != null) {
                orderService.changeStatus(orderDto.getId(), orderDto.getOrderStatus());
            }
            if (orderDto.getCompletionDate() != null) {
                orderService.shiftCompletionDateTime(orderDto.getId(), orderDto.getCompletionDate());
            }
        } else {
            throw new CriteriaNotFoundException("bad id", 405);
        }
    }

}
