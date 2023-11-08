package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.util.EntityBuilder;
import com.senlainc.warsaw.tyurin.util.dbConnection.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
public class CraftsmanDAO implements ICraftsmanDAO{

    @DependencyComponent
    private DBConnector dbConnector;
    private static CraftsmanDAO INSTANCE;
    private List<Craftsman> craftsmen;

    public CraftsmanDAO() {
        craftsmen = new ArrayList<>();
    }

    public static CraftsmanDAO getInstance() {
        return INSTANCE;
    }

    @DependencyInitMethod
    public void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void addCraftsman(Craftsman craftsman) throws Exception {

        try(Connection connection = dbConnector.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into craftsman (name, surname) " +
                    "values (?, ?);")){
            preparedStatement.setString(1, craftsman.getName());
            preparedStatement.setString(2, craftsman.getSurname());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("Can't add craftsman");
        }
    }

    @Override
    public void deleteCraftsman(long id) throws Exception {

        try(Connection connection = dbConnector.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from craftsman where id=?;")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("Can't delete craftsman");
        }
    }

    @Override
    public List<Craftsman> getCraftsmen() throws Exception {

        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from craftsman;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            List<Craftsman> craftsmen = new ArrayList<>();
            while (resultSet.next()) {
                craftsmen.add(EntityBuilder.buildCraftsman(resultSet));
            }
            return craftsmen;
        } catch (Exception exception) {
            throw new Exception("Can't get craftsmen");
        }
    }

    @Override
    public Craftsman getCraftsman(long id) throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * " +
                     "from craftsman where id =?;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            preparedStatement.setLong(1, id);
            if (resultSet.next()) {
                return EntityBuilder.buildCraftsman(resultSet);
            }
        } catch (Exception exception) {
            throw new Exception("Can't get craftsman by id");
        }
        return null;
    }

    @Override
    public List<Craftsman> getCraftsmenByOrder(long id) throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select craftsman.id, " +
                     "craftsman.name, craftsman.surname " +
                     "from craftsman join craftsman_order on craftsman_order.craftsman_id=craftsman.id " +
                     "where craftsman_order.order_id=?;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            preparedStatement.setLong(1, id);
            List<Craftsman> craftsmen = new ArrayList<>();
            while (resultSet.next()) {
                craftsmen.add(EntityBuilder.buildCraftsman(resultSet));
            }
            return craftsmen;
        } catch (Exception exception) {
            throw new Exception("Can't get craftsman by order id");
        }
    }

    @Override
    public List<Long> getCraftsmenIdByOrder(long id) throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select craftsman_id " +
                     "from order_craftsman where order_id=?;")){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Long> craftsmen = new ArrayList<>();
            while (resultSet.next()) {
                craftsmen.add(resultSet.getLong("craftsman_id"));
            }
            return craftsmen;
        } catch (Exception exception) {
            throw new Exception("Can't get craftsmen id by order id");
        }
    }

    @Override
    public List<Craftsman> getSortedAlphabetically() throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from craftsman " +
                     "order by surname, name;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            List<Craftsman> craftsmen = new ArrayList<>();
            while (resultSet.next()) {
                craftsmen.add(EntityBuilder.buildCraftsman(resultSet));
            }
            return craftsmen;
        } catch (Exception exception) {
            throw new Exception("Can't get craftsmen sorted alphabetically");
        }
    }

    @Override
    public List<Craftsman> getSortedByBusyness() throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select order_craftsman.craftsman_id as id, craftsman.name, craftsman.surname\n" +
                     "from orders join order_craftsman on orders.id=order_craftsman.order_id \n" +
                     "join order_status on order_status.id=orders.order_status_id \n" +
                     "join craftsman on craftsman.id=order_craftsman.craftsman_id\n" +
                     "where order_status.id != 4\n" +
                     "group by order_craftsman.craftsman_id\n" +
                     "order by count(order_craftsman.craftsman_id) desc;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            List<Craftsman> craftsmen = new ArrayList<>();
            while (resultSet.next()) {
                craftsmen.add(EntityBuilder.buildCraftsman(resultSet));
            }
            return craftsmen;
        } catch (Exception exception) {
            throw new Exception("Can't get craftsmen sorted by business");
        }
    }
}

