package com.capstone.dataStore;

import com.capstone.model.Booking;
import com.capstone.model.Car;
import com.capstone.model.User;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CarBookingCLIDataAccessService implements CarBookingCLIDAO {

    private static final Faker FAKER = new Faker();
    private static final List<User> USERLIST = new ArrayList<>();
    private static final List<Booking> BOOKINGS = new ArrayList<>();
    private static final List<Car> CARLIST = new ArrayList<>();

    @Override
    public List<User> getUsers() {
        for (int i = 0; i < USERLIST.size(); i++) {
            System.out.println(USERLIST.get(i));
        }
        return USERLIST;
    }

    @Override
    public void addUser(User user) {
        USERLIST.add(user);
    }

    @Override
    public User getUserWithId(UUID uuid) {
        for (User user : USERLIST) {
            if (user.getId().equals(uuid)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean existUserWithId(UUID uuid) {
        for (User user : USERLIST) {
            if (uuid.equals(user.getId())) {
                return true;
            }
        }
        return false;
    }


    public void addCar(Car car) {
        CARLIST.add(car);
    }

    public List<Car> getCars() {
        for (int i = 0; i < CARLIST.size(); i++) {
            System.out.println(CARLIST.get(i));
        }
        return CARLIST;
    }

    public List<Car> getElectricCars() {
        List<Car> electricCars = new ArrayList<>();
        for (Car car : CARLIST) {
            if (car.isElectric()) {
                electricCars.add(car);
            }
        }
        for (int i = 0; i < electricCars.size(); i++) {
            System.out.println(electricCars.get(i));
        }
        return electricCars;
    }

    public Car getCarWithRegNum(int regNum) {
        for (Car car : CARLIST) {
            if (existCarWithRegNum(regNum) || existElectricCarWithRegNum(regNum)) {
                return car;
            }
        }
        return null;
    }

    public boolean existCarWithRegNum(int regNum) {
        for (Car car : CARLIST) {
            if (regNum == car.getRegNumber() && !car.isElectric()) {
                return true;
            }
        }
        return false;
    }

    public boolean existElectricCarWithRegNum(int regNum) {
        for (Car car : CARLIST) {
            if (regNum == car.getRegNumber() && car.isElectric()) {
                return true;
            }
        }
        return false;
    }

    private List<Car> getCarsFromBooking() {
        List<Car> cars = new ArrayList<>();
        for (Booking booking : BOOKINGS) {
            Car car = booking.getCar();
            cars.add(car);
        }
        return cars;
    }

    public void removeCarFromListAfterSuccessfulBooking(Car car) {
        CARLIST.remove(car);
    }

    @Override
    public List<Booking> getAllBookings() {
        for (int i = 0; i < BOOKINGS.size(); i++) {
            System.out.println(BOOKINGS.get(i));
        }
        return BOOKINGS;
    }

    @Override
    public void addBooking(Booking booking) {
        BOOKINGS.add(booking);
    }

    @Override
    public List<User> getAllUsersFromBookings() {
        List<User> users = new ArrayList<>();
        for (Booking booking : BOOKINGS) {
            User user = booking.getUser();
            users.add(user);
        }
        return users;
    }

    @Override
    public User getUserFromBookingById(UUID userId) {
        for (User user : getAllUsersFromBookings()) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Optional<Booking> getBookingByUserId(UUID userId) {
        for (Booking booking : BOOKINGS) {
            if (booking.getUser().getId().equals(userId)) {
                return Optional.of(booking);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> registerFakerUsers() {
        for (int i = 0; i <= 20; i++) {
            USERLIST.add(new User(UUID.randomUUID(), FAKER.name().firstName()));
        }

        return USERLIST;
    }
}
