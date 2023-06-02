package com.vsu.repository;

import com.vsu.entity.Car;
import com.vsu.exception.DBException;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepo {
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM car WHERE id_car = ?";
    private static final String SELECT_ALL_BY_USER_ID_QUERY = "SELECT * FROM car WHERE id_profile = ?";
    private static final String INSERT_QUERY = "INSERT INTO car(brand, model, plate_number, id_profile) VALUES (?, ?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM car WHERE id_car = ?";
    private static final String UPDATE_QUERY = "UPDATE car SET brand=?, model=?, plate_number=?, id_profile=? " + "WHERE id_car = ?";
    private final ConnectionFactory connectionFactory;

    public CarRepo(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Car selectById(Long id) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getCar(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<Car> selectAllByUserId(Long idUser) {
        try (Connection connection = connectionFactory.getConnection()) {
            List<Car> carList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_USER_ID_QUERY);
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                carList.add(getCar(resultSet));
            }
            return carList;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public int insert(Car car) {
        int countUpdate;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            setCarParamsToStatement(car, statement);
            countUpdate = statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new DBException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int deleteById(Long id) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, id);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int updateByID(Car car) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            setCarParamsToStatement(car, statement);
            statement.setLong(5, car.getId());
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    private void setCarParamsToStatement(Car car, PreparedStatement statement) throws SQLException {
        statement.setString(1, car.getBrand());
        statement.setString(2, car.getModel());
        statement.setString(3, car.getPlateNum());
        statement.setLong(4, car.getIdUser());
    }

    @NotNull
    private Car getCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong("id_car"));
        car.setBrand(resultSet.getString("brand"));
        car.setModel(resultSet.getString("model"));
        car.setPlateNum(resultSet.getString("plate_number"));
        car.setIdUser(resultSet.getLong("id_profile"));
        return car;
    }
}
