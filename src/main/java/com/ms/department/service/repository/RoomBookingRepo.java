package com.ms.department.service.repository;

import com.ms.department.service.dto.BookingDetails;
import com.ms.department.service.dto.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RoomBookingRepo {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final List<Integer> roomList = Arrays.asList(1, 2, 3, 4, 5, 6);

    public static final String HASH_KEY = "RoomBooking";
    public static final String BOOKING_STATUS = "BOOKED";

    public synchronized BookingDetails save(BookingRequest bookingRequest) {
        BookingDetails bookingDetails = setRoomDetails(bookingRequest);
        if(bookingDetails.getDescription().equalsIgnoreCase(BOOKING_STATUS)) {
            redisTemplate.opsForHash().put(HASH_KEY, bookingDetails.getId(), bookingDetails);
        }
        return bookingDetails;
    }

    private BookingDetails setRoomDetails(BookingRequest bookingRequest) {
        BookingDetails bookingDetails = new BookingDetails();

        int roomNumber = 0;
        for (Integer rmNo : roomList) {
            boolean status = findBookDetailsByGuestAndBookingDate(bookingRequest.getBookingDate(), rmNo.intValue());
            System.out.println("Status >>>>>>>>>> " + status);
            if (!status) {
                roomNumber = rmNo.intValue();
                break;
            }
        }

        if(roomNumber > 0) {
            bookingDetails.setId(UUID.randomUUID().toString());
            bookingDetails.setRoomNumber(roomNumber);
            bookingDetails.setGuestName(bookingRequest.getGuestName());
            bookingDetails.setBookingDate(bookingRequest.getBookingDate());
            bookingDetails.setDescription(BOOKING_STATUS);
        } else{
            bookingDetails.setDescription("No rooms available to proceed with given date, please try with other");
        }
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

    public synchronized boolean findBookDetailsByGuestAndBookingDate(LocalDate localDate, int roomNo) {
        List<BookingDetails> bookingDetailsList = findAll();
        return bookingDetailsList
                .stream()
                .anyMatch(bookingDetails ->
                        bookingDetails.getBookingDate().equals(localDate) &
                                bookingDetails.getRoomNumber() == roomNo);
    }

    public String deleteBookingDetails(int id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);

        return "Booking details deleted";
    }
}
