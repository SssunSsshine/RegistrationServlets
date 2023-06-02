package com.vsu.service;

import com.vsu.entity.Car;
import com.vsu.repository.CarRepo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarService {
    private static final int MIN_COUNT_UPDATE = 1;
    private final CarRepo carRepo;
    private static final Logger LOGGER = Logger.getLogger(CarService.class.getName());

    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Car insertCar(Car car) {
        if (carRepo.insert(car) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "User with id {0} is not inserted", car.getId());
            return null;
        } else {
            return car;
        }
    }

    public void deleteCar(Long id) {
        if (carRepo.deleteById(id) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "User with id {0} is not deleted", id);
        }
    }

    public Car selectById(String id) {
        Long idL = Long.parseLong(id);
        return carRepo.selectById(idL);
    }

    public List<Car> selectAllByUserId(Long id) {
        return carRepo.selectAllByUserId(id);
    }

    public void updateByID(Car car) {
        if (carRepo.updateByID(car) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "User with id {} is not updated", car.getId());
        }
    }
}
