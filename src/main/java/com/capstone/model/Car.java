package com.capstone.model;

import java.util.UUID;

public class Car {
    private UUID id;
    private String brand;
    private Integer regNumber;
    private boolean isElectric;

    public Car(UUID id, String brand, Integer regNumber, boolean isElectric) {
        this.id = id;
        this.brand = brand;
        this.regNumber = regNumber;
        this.isElectric = isElectric;
    }

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", regNumber=" + regNumber +
                ", isElectric=" + isElectric +
                '}';
    }
}
