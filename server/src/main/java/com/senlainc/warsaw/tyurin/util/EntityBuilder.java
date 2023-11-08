package com.senlainc.warsaw.tyurin.util;

import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityBuilder {

    public static Craftsman buildCraftsman(ResultSet resultSet) throws SQLException {
        Craftsman craftsman = new Craftsman();

        craftsman.setId(resultSet.getLong("id"));
        craftsman.setName(resultSet.getString("name"));
        craftsman.setSurname(resultSet.getString("surname"));

        return craftsman;
    }

    public static GaragePlace buildGaragePlace(ResultSet resultSet) throws SQLException {
        GaragePlace garagePlace = new GaragePlace();

        garagePlace.setId(resultSet.getLong("id"));
        garagePlace.setNumber(resultSet.getInt("number"));
        garagePlace.setSpace(resultSet.getDouble("space"));

        return garagePlace;
    }

    public static Order buildOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();

        order.setId(resultSet.getLong("order_id"));
        order.setSubmissionDate(resultSet.getTimestamp("submission_date").toLocalDateTime());
        order.setStartDate(resultSet.getTimestamp("start_date").toLocalDateTime());
        order.setCompletionDate(resultSet.getTimestamp("completion_date").toLocalDateTime());
        order.setPrice(resultSet.getDouble("price"));
        order.setGaragePlaceId(resultSet.getLong("garage_place_id"));
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("order_status")));

        return order;
    }
}
