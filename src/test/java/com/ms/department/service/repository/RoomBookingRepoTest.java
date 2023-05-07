package com.ms.department.service.repository;

import com.ms.department.service.dto.BookingDetails;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import redis.clients.jedis.JedisShardInfo;
import redis.embedded.RedisServer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Ignore
class RoomBookingRepoTest {

    private RoomBookingRepo roomBookingRepo;

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    private RedisServer redisServer;
    private JedisConnectionFactory connectionFactory;

    @BeforeEach
    void setUp() {
        roomBookingRepo = new RoomBookingRepo();
        try {
            redisServer = RedisServer.builder().port(6370).build();
            redisServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JedisShardInfo shardInfo = new JedisShardInfo("localhost", 6379);
        connectionFactory = new JedisConnectionFactory();
        connectionFactory.setShardInfo(shardInfo);

        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();

        ReflectionTestUtils.setField(roomBookingRepo, "redisTemplate", redisTemplate);
    }

    @Test
    public void roomBookingTest() {
        // given
        redisTemplate.opsForValue().set("Booking", "room");

        // when
        String result = redisTemplate.opsForValue().get("Booking");

        // then
        assertEquals("room", result);
    }

    @Ignore
    void save() {
        BookingDetails bookingDetails = new BookingDetails(1, "Hanu", 101, LocalDate.now());
        BookingDetails bookingDetails1 = roomBookingRepo.save(bookingDetails);
        assertNotNull(bookingDetails1);
        assertEquals(bookingDetails.getGuestName(), bookingDetails1.getGuestName());
    }

   /*

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
    */

    @After("")
    public void tearDown() {
        redisServer.stop();
    }

}