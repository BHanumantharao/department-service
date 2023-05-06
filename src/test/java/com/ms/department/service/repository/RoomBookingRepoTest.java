package com.ms.department.service.repository;

import com.ms.department.service.dto.BookingDetails;
import com.ms.department.service.redis.RedisConfigurationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest(classes = TestRedisConfiguration.class)
//@SpringBootTest(classes = RedisConfigurationTest.class)
class RoomBookingRepoTest {

    @Autowired
    private RoomBookingRepo roomBookingRepo;

    @BeforeEach
    void setUp() throws Exception {
        roomBookingRepo = new RoomBookingRepo();
    }

    @Test
    void save() {
        BookingDetails bookingDetails = new BookingDetails(1, "Hanu", 101, LocalDate.now());
        roomBookingRepo.save(bookingDetails);
    }

    @Test
    void findAll() {
    }

    @Test
    void findBookDetailsById() {
    }

    @Test
    void findBookDetailsByGuestName() {
    }

    @Test
    void findBookDetailsByBookingDate() {
    }

    @Test
    void deleteBookingDetails() {
    }

}