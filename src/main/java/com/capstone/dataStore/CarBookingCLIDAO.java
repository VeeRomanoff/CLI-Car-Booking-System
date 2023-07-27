package com.capstone.dataStore;

import com.capstone.model.Booking;
import com.capstone.model.Car;
import com.capstone.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarBookingCLIDAO {
    void addUser(User user);
    List<User> getUsers();
    User getUserWithId(UUID uuid);
    boolean existUserWithId(UUID uuid);
    void addCar(Car car);
    List<Car> getCars();
    List<Car> getElectricCars();
    Car getCarWithRegNum(int regNum);
    boolean existCarWithRegNum(int regNum);
    boolean existElectricCarWithRegNum(int regNum);
    void removeCarFromListAfterSuccessfulBooking(Car car);
    List<Booking> getAllBookings();
    void addBooking(Booking booking);
    List<User> getAllUsersFromBookings();
    User getUserFromBookingById(UUID userId);
    Optional<Booking> getBookingByUserId(UUID userId);
    List<User> registerFakerUsers();
}
