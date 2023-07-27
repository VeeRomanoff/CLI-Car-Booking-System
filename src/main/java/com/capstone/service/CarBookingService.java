package com.capstone.service;

import com.capstone.dataStore.CarBookingCLIDAO;
import com.capstone.model.Booking;
import com.capstone.model.Car;
import com.capstone.model.User;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class CarBookingService {

    private final CarBookingCLIDAO cliDAO;
    private final Registration registration;

    public CarBookingService(CarBookingCLIDAO cliDAO, Registration registration) {
        this.cliDAO = cliDAO;
        this.registration = registration;
    }

    public void message() {
        while (true) {
            System.out.println(
                    "" +
                            "1️⃣ - Book Car\n" +
                            "2️⃣ - View All User Booked Cars\n" +
                            "3️⃣ - View All Bookings\n" +
                            "4️⃣ - View Available Cars\n" +
                            "5️⃣ - View Available Electric Cars\n" +
                            "6️⃣ - View all users\n" +
                            "7️⃣ - Register a new User\n" +
                            "8️⃣ - Register a new Car\n" +
                            "9️⃣ - Register 20 random Users\n" +
                            "🔟 - Exit"
            );

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookCar();
                    break;
                case 2:
                    viewUserBookedCars();
                    break;
                case 3:
                    cliDAO.getAllBookings();
                    break;
                case 4:
                    cliDAO.getCars();
                    break;
                case 5:
                    if (Objects.requireNonNull(cliDAO.getElectricCars()).isEmpty()) {
                        System.out.println("No electric cars available");
                        break;
                    }
                    cliDAO.getElectricCars();
                    break;
                case 6:
                    cliDAO.getUsers();
                    break;
                case 7:
                    registration.registerUser();
                    break;
                case 8:
                    registration.registerCar();
                    break;
                case 9:
                    cliDAO.registerFakerUsers();
                    System.out.println("🎉 20 random users were successfully generated\n " +
                            "You can press 6️⃣ to view them all");
                    System.out.println();
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private void viewUserBookedCars() {
        cliDAO.getUsers();
        UUID userId = null;


        try {
            Scanner idInput = new Scanner(System.in);
            System.out.println("👉select user id");
            userId = UUID.fromString(idInput.next());
        } catch (IllegalArgumentException exception) {
            System.out.println("❌ There's no user with such id");
            return;
        }

        Booking booking = cliDAO.getBookingByUserId(userId).orElse(null);
        if (booking != null) {
            User user = booking.getUser();
            Car car = booking.getCar();
            if (user != null && car != null) {
                System.out.println("User " + user + " has booked this car " + booking.getCar());
            } else {
                System.out.println("User " + user + " has no cars booked");
            }
        }
    }

    private void bookCar() {

        Integer chosenRegNumber;
        UUID chosenUserId;

        try {
            chosenRegNumber = selectCar();
            chosenUserId = selectUser();
        } catch (IllegalArgumentException exception) {
            System.out.println("❌ Please register cars / users");
            return;
        } catch (InputMismatchException exception) {
            System.out.println("❌ Wrong input");
            return;
        }

        Car carViaRegNum = cliDAO.getCarWithRegNum(chosenRegNumber);
        User userViaId = cliDAO.getUserWithId(chosenUserId);

        if (userViaId != null && Objects.equals(cliDAO.getUserFromBookingById(userViaId.getId()), userViaId)) {
            System.out.println("❌ This person has already booked a car");
            System.out.println();
        }

        if (carViaRegNum != null && userViaId != null) {

            Booking booking = new Booking(
                    UUID.randomUUID(),
                    userViaId,
                    carViaRegNum,
                    LocalDateTime.now(),
                    false

            );
            cliDAO.addBooking(booking);
            System.out.println("🎉 Successfully booked car with reg number " + carViaRegNum.getRegNumber() +
                    "for user " + userViaId);
            System.out.println();
            cliDAO.removeCarFromListAfterSuccessfulBooking(carViaRegNum);
        } else {
            System.out.println("Error: Car with registration number " + chosenRegNumber + " not found");
            System.out.println();
        }
    }

    private Integer selectCar() throws IllegalArgumentException {
        cliDAO.getCars();
        System.out.println("👉Select car reg number");
        Scanner carRegNumberInput = new Scanner(System.in);
        return carRegNumberInput.nextInt();
    }

    private UUID selectUser() throws IllegalArgumentException {
        cliDAO.getUsers();
        System.out.println("👉Select user id");
        Scanner userIdInput = new Scanner(System.in);
        return UUID.fromString(userIdInput.next());
    }
};
