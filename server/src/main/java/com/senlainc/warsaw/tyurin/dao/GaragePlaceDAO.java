package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.util.EntityBuilder;
import com.senlainc.warsaw.tyurin.util.dbConnection.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DependencyClass
public class GaragePlaceDAO implements IGaragePlaceDAO{

    @DependencyComponent
    private DBConnector dbConnector;
    private static GaragePlaceDAO INSTANCE;
    private List<GaragePlace> garagePlaces;

    public GaragePlaceDAO() {
        garagePlaces = new ArrayList<>();
    }

    public static GaragePlaceDAO getInstance() {
        return INSTANCE;
    }

    @DependencyInitMethod
    public void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void addGaragePlace(GaragePlace garagePlace) throws Exception {
        try(Connection connection = dbConnector.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into garage_place (number, space) " +
                    "values (?, ?);")){
            preparedStatement.setInt(1, garagePlace.getNumber());
            preparedStatement.setDouble(2, garagePlace.getSpace());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("Can't add garage place");
        }
    }

    @Override
    public void deleteGaragePlace(long id) throws Exception {
        try(Connection connection = dbConnector.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from garage_place where id=?;")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("Can't delete garage place");
        }
    }

    @Override
    public List<GaragePlace> getGaragePlaces() throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * " +
                     "from garage_place;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            List<GaragePlace> garagePlaces = new ArrayList<>();
            while (resultSet.next()) {
                garagePlaces.add(EntityBuilder.buildGaragePlace(resultSet));
            }
            return garagePlaces;
        } catch (Exception exception) {
            throw new Exception("Can't get garage places");
        }
    }

    @Override
    public GaragePlace getGaragePlace(long id) throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * " +
                     "from garage_place where id =?;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            preparedStatement.setLong(1, id);
            if (resultSet.next()) {
                return EntityBuilder.buildGaragePlace(resultSet);
            }
        } catch (Exception exception) {
            throw new Exception("Can't get garage place by id");
        }
        return null;
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces() throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select distinct garage_place.id, " +
                     "garage_place.number, garage_place.space " +
                     "from garage_place join orders on garage_place.id=orders.garage_place_id " +
                     "where orders.order_status_id!=3;");
             ResultSet resultSet = preparedStatement.executeQuery()){
            List<GaragePlace> garagePlaces = new ArrayList<>();
            while (resultSet.next()) {
                garagePlaces.add(EntityBuilder.buildGaragePlace(resultSet));
            }
            return garagePlaces;
        } catch (Exception exception) {
            throw new Exception("Can't available garage places");
        }
    }

    @Override
    public long getAvailablePlacesAmount(LocalDateTime localDateTime) throws Exception {
        try (Connection connection = dbConnector.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select LEAST(count(order_craftsman.order_id), count(order_craftsman.craftsman_id)) as amount\n" +
                     "from orders join garage_place on garage_place.id=orders.garage_place_id \n" +
                     "join order_craftsman on order_craftsman.order_id=orders.id\n" +
                     "where (completion_date<? or start_date>?) and order_status_id!=4 and order_craftsman.craftsman_id not in (select distinct craftsman_id\n" +
                     "from orders join garage_place on garage_place.id=orders.garage_place_id \n" +
                     "join order_craftsman on order_craftsman.order_id=orders.id\n" +
                     "where start_date < ? and completion_date > ? and order_status_id!=4);");
             ResultSet resultSet = preparedStatement.executeQuery()){
            preparedStatement.setTimestamp(1, Timestamp.valueOf(localDateTime));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(localDateTime));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(localDateTime));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(localDateTime));
            if (resultSet.next()) {
                return resultSet.getLong("amount");
            }
        } catch (Exception exception) {
            throw new Exception("Can't available garage places amount");
        }
        return 0;
    }
}
