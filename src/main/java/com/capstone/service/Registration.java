package com.capstone.service;

import com.capstone.dataStore.CarBookingCLIDAO;
import com.capstone.model.Car;
import com.capstone.model.User;

import java.util.Scanner;
import java.util.UUID;

public class Registration {

    private final CarBookingCLIDAO clidao;

    public Registration(CarBookingCLIDAO clidao) {
        this.clidao = clidao;
    }


    public void registerCar() {
        System.out.println("Enter brand of car");
        Scanner carBrandScan = new Scanner(System.in);
        String carBrand = carBrandScan.nextLine();
        System.out.println("Enter registration number of car");
        Scanner regNumberScan = new Scanner(System.in);
        Integer regNumber = regNumberScan.nextInt();
        System.out.println("Is car electric? (yes or no)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (clidao.existCarWithRegNum(regNumber)) {
            System.out.println("Car with this reg num already exists");
            System.out.println();
        }

        if (answer.equalsIgnoreCase("no")) {
            Car car = new Car(
                    UUID.randomUUID(),
                    carBrand,
                    regNumber,
                    false
            );

            clidao.addCar(car);
            System.out.println("Successfully registered a new car with reg number " + car.getRegNumber());
            System.out.println();
        } else if (answer.equalsIgnoreCase("yes")) {
            Car car = new Car(
                    UUID.randomUUID(),
                    carBrand,
                    regNumber,
                    true
            );

            clidao.addCar(car);
            System.out.println("Successfully registered a new car with reg number " + car.getRegNumber());
            System.out.println();
        } else {
            System.out.println("❌ wrong input");
            System.out.println();
        }
    }

    public void registerUser() {
        System.out.println("Enter name of user");
        Scanner userName = new Scanner(System.in);
        User user = new User(
                UUID.randomUUID(),
                userName.nextLine()
        );

        clidao.addUser(user);
        System.out.println("Successfully registered a new user with id " + user.getId());
        System.out.println();
    }
}
