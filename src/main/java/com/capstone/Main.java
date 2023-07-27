package com.capstone;

import com.capstone.dataStore.CarBookingCLIDAO;
import com.capstone.dataStore.CarBookingCLIDataAccessService;
import com.capstone.service.CarBookingService;
import com.capstone.service.Registration;

public class Main {
    public static void main(String[] args) {
        CarBookingCLIDAO cliDAO = new CarBookingCLIDataAccessService();
        Registration registration = new Registration(cliDAO);
        CarBookingService carBookingService =
                new CarBookingService(
                        cliDAO,
                        registration
                );

        carBookingService.message();
    }
}
