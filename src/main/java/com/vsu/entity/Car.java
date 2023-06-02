package com.vsu.entity;

import java.util.Objects;

public class Car {
    private Long id;
    private String brand;
    private String model;
    private String plateNum;
    private Long idUser;

    public Car() {
    }

    public Car(String brand, String model, String plateNum, Long idUser) {
        this.brand = brand;
        this.model = model;
        this.plateNum = plateNum;
        this.idUser = idUser;
    }

    public Car(Long id, String brand, String model, String plateNum, Long idUser) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plateNum = plateNum;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(getId(), car.getId()) && Objects.equals(getBrand(), car.getBrand()) && Objects.equals(getModel(), car.getModel()) && Objects.equals(getPlateNum(), car.getPlateNum()) && Objects.equals(getIdUser(), car.getIdUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBrand(), getModel(), getPlateNum(), getIdUser());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", plateNum='" + plateNum + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
