package util;

import com.senlainc.warsaw.tyurin.entity.Order;

public class Printer {

    public static void printOrder(Order order) {
        StringBuilder craftsmenIdBuilder = new StringBuilder();
        order
                .getCraftsmenId()
                .forEach(craftsmanId -> craftsmenIdBuilder.append(craftsmanId).append(";"));
        craftsmenIdBuilder.delete(craftsmenIdBuilder.length() - 1, craftsmenIdBuilder.length());
        StringBuilder orderBuilder = new StringBuilder();
        orderBuilder
                .append("id = ")
                .append(order.getId())
                .append(", price = ")
                .append(order.getPrice())
                .append(", submission date = ")
                .append(order.getSubmissionDate())
                .append(", start date = ")
                .append(order.getStartDate())
                .append(", completion date = ")
                .append(order.getCompletionDate())
                .append(", order status = ")
                .append(order.getOrderStatus())
                .append(", craftsman id = ")
                .append(craftsmenIdBuilder)
                .append(", garage place id = ")
                .append(order.getGaragePlaceId());
        System.out.println(orderBuilder);
    }
}
