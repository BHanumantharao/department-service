package com.ms.department.service.repository;

import com.ms.department.service.dto.BookingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoomBookingRepo {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String HASH_KEY = "RoomBooking";

    public synchronized BookingDetails save(BookingDetails bookingDetails) {
        redisTemplate.opsForHash().put(HASH_KEY, bookingDetails.getId(), bookingDetails);
        return bookingDetails;
    }

    public synchronized List<BookingDetails> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public synchronized BookingDetails findBookDetailsById(int id) {
        return (BookingDetails) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public synchronized List<BookingDetails> findBookDetailsByGuestName(String guestName) {
        List<BookingDetails> bookingDetailsList = findAll();
        return bookingDetailsList
                .stream()
                .filter(bookingDetails -> bookingDetails.getGuestName().equalsIgnoreCase(guestName))
                .collect(Collectors.toList());
    }

    public synchronized List<BookingDetails> findBookDetailsByBookingDate(LocalDate localDate) {
        List<BookingDetails> bookingDetailsList = findAll();

        return bookingDetailsList
                .stream()
                .filter(bookingDetails -> bookingDetails.getBookingDate().equals(localDate))
                .collect(Collectors.toList());
    }

    public String deleteBookingDetails(int id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);

        return "Booking details deleted";
    }
}
