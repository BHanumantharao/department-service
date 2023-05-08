package com.ms.department.service.repository;

import com.ms.department.service.dto.BookingDetails;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@Ignore
public class RoomBookingRepoMockTest {

    private RoomBookingRepo roomBookingRepo;
    private RedisTemplate<String, Object> redisTemplate;
    public static final String HASH_KEY = "RoomBooking";

    @BeforeEach
    void setUp() throws Exception {
        roomBookingRepo = new RoomBookingRepo();
        redisTemplate = Mockito.mock(RedisTemplate.class);

        ReflectionTestUtils.setField(roomBookingRepo, "redisTemplate", redisTemplate);
    }

    @Test
    void save() {
        BookingDetails bookingDetails = new BookingDetails(1, "Hanu", 101, LocalDate.now());
        doNothing().when(redisTemplate).opsForHash().put(any(String.class), any(Object.class), any(Object.class));
        BookingDetails bookingDetails1 = roomBookingRepo.save(bookingDetails);
        assertNotNull(bookingDetails1);
        assertEquals(bookingDetails.getGuestName(), bookingDetails1.getGuestName());
    }

}
