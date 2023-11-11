package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.EntityBuilder;
import com.senlainc.warsaw.tyurin.util.dbConnection.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
public class OrderDAO implements IOrderDAO{

    @DependencyComponent
    private DBConnector dbConnector;
    @DependencyComponent
    private ICraftsmanDAO craftsmanDAO;
    private static OrderDAO INSTANCE;
    private List<Order> orders;

    public OrderDAO() {
        orders = new ArrayList<>();
    }

    public static OrderDAO getInstance() {
        return INSTANCE;
    }

    @DependencyInitMethod
    public void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void addOrder(Order order) throws Exception {
        long orderStatusId = getOrderStatusId(order.getOrderStatus().toString());
        try(Connection connection = dbConnector.createConnection();
            PreparedStatement ordersInsert = connection.prepareStatement("insert into orders (price, " +
                    "submission_date, start_date, completion_date, garage_place_id, order_status_id) " +
                    "values (?, ?, ?, ?, ?, ?);");
            PreparedStatement orderCraftsmanInsert = connection.prepareStatement("insert into order_craftsman (order_id, craftsman_id) " +
                    "values (?, ?);")){
            connection.setAutoCommit(false);
            ordersInsert.setDouble(1, order.getPrice());
            ordersInsert.setTimestamp(2, Timestamp.valueOf(order.getSubmissionDate()));
            ordersInsert.setTimestamp(3, Timestamp.valueOf(order.getStartDate()));
            ordersInsert.setTimestamp(4, Timestamp.valueOf(order.getCompletionDate()));
            ordersInsert.setLong(5, order.getGaragePlaceId());
            ordersInsert.setLong(6, orderStatusId);
            ordersInsert.executeUpdate();

            order.getCraftsmenId().forEach(craftsmanId -> {
                try {
                    orderCraftsmanInsert.setLong(1, order.getId());
                    orderCraftsmanInsert.setLong(2, craftsmanId);
                    orderCraftsmanInsert.executeUpdate();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            });
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception exception) {
            throw new Exception("Can't add order");
        }
    }

    @Override
    public void deleteOrder(long id) throws Exception {
        try(Connection connection = dbConnector.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from orders where id=?;")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("Can't delete order");
        }
    }

    @Override
    public List<Order> getOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                    "orders.price, orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                    "join order_status on order_status.id=orders.order_status_id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get orders");
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersPriceSorted() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                    "orders.price, orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                    "join order_status on order_status.id=orders.order_status_id" +
                    "order by orders.price;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get orders sorted by price");
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersSubmissionDateSorted() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, orders.price, " +
                    "orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, " +
                    "order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id" +
                    " join order_status on order_status.id=orders.order_status_id " +
                    "order by orders.submission_date;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get orders sorted by submission date");
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersCompletionDateSorted() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, orders.price, " +
                    "orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, " +
                    "order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id" +
                    " join order_status on order_status.id=orders.order_status_id " +
                    "order by orders.completion_date;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get orders sorted by completion date");
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersStartDateSorted() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, orders.price, " +
                    "orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, " +
                    "order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id" +
                    " join order_status on order_status.id=orders.order_status_id " +
                    "order by orders.start_date;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get orders sorted by start date");
        }
        return orders;
    }

    @Override
    public Order getOrder(long id) throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                     "orders.price, orders.submission_date, " +
                     "orders.start_date, orders.completion_date, " +
                     "orders.garage_place_id, order_status.order_status " +
                     "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                     "join order_status on order_status.id=orders.order_status_id " +
                     "where order_craftsman.order_id =?;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            preparedStatement.setLong(1, id);
            if (resultSet.next()) {
                Order order = EntityBuilder.buildOrder(resultSet);
                order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
            }
        } catch (Exception exception) {
            throw new Exception("Can't get order by id");
        }
        return null;
    }

    @Override
    public List<Order> getNotCanceledOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                    "orders.price, orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                    "join order_status on order_status.id=orders.order_status_id " +
                    "where order_status.id != 4;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get non canceled orders");
        }
        return orders;
    }

    @Override
    public List<Order> getInProgressOrdersCompletionDateSorted() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                    "orders.price, orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                    "join order_status on order_status.id=orders.order_status_id " +
                    "where order_status.id = 3 " +
                    "order by orders.completion_date;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get orders in progress sorted by completion date");
        }
        return orders;
    }

    @Override
    public List<Order> getInProgressOrdersSubmissionDateSorted() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                    "orders.price, orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                    "join order_status on order_status.id=orders.order_status_id " +
                    "where order_status.id = 3 " +
                    "order by orders.submission_date;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get orders in progress sorted by submission date");
        }
        return orders;
    }

    @Override
    public List<Order> getInProgressOrdersStartDateSorted() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                    "orders.price, orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                    "join order_status on order_status.id=orders.order_status_id " +
                    "where order_status.id = 3 " +
                    "order by orders.start_date;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get orders in progress sorted by start date");
        }
        return orders;
    }

    @Override
    public List<Order> getInProgressOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                    "orders.price, orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                    "join order_status on order_status.id=orders.order_status_id " +
                    "where order_status.id = 3;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get orders in progress");
        }
        return orders;
    }

    @Override
    public List<Order> getArchivedOrdersPriceSorted() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                    "orders.price, orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                    "join order_status on order_status.id=orders.order_status_id " +
                    "where order_status.id != 3 and order_status.id != 1" +
                    "order by orders.start_date;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get archives orders sorted by price");
        }
        return orders;
    }

    @Override
    public List<Order> getArchivedOrdersCompletionDateSorted() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                    "orders.price, orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                    "join order_status on order_status.id=orders.order_status_id " +
                    "where order_status.id != 3 and order_status.id != 1" +
                    "order by orders.completion_date;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get archives orders sorted by completion date");
        }
        return orders;
    }

    @Override
    public List<Order> getArchivedOrdersSubmissionDateSorted() throws Exception {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                    "orders.price, orders.submission_date, orders.start_date, " +
                    "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                    "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                    "join order_status on order_status.id=orders.order_status_id " +
                    "where order_status.id != 3 and order_status.id != 1" +
                    "order by orders.submission_date;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }
            orders.forEach(order -> {
                try {
                    order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            throw new Exception("Can't get archives orders sorted by submission date");
        }
        return orders;
    }

    @Override
    public Order getOrderByCraftsmen(long craftsmanId) throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.order_id, " +
                     "orders.price, orders.submission_date, orders.start_date, " +
                     "orders.completion_date, orders.garage_place_id, order_status.order_status " +
                     "from orders join order_craftsman on orders.id=order_craftsman.order_id " +
                     "join order_status on order_status.id=orders.order_status_id " +
                     "where orders.order_status_id=3 and order_craftsman.craftsman_id=?;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            preparedStatement.setLong(1, craftsmanId);
            if (resultSet.next()) {
                Order order = EntityBuilder.buildOrder(resultSet);
                order.setCraftsmen(craftsmanDAO.getCraftsmenIdByOrder(order.getId()));
            }
        } catch (Exception exception) {
            throw new Exception("Can't get order by craftsman id");
        }
        return null;
    }

    @Override
    public Long getOrderStatusId(String orderStatus) throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select id " +
                     "from order_status where order_status=?;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            preparedStatement.setString(1, orderStatus);
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (Exception exception) {
            throw new Exception("Can't get order status id");
        }
        return null;
    }
}