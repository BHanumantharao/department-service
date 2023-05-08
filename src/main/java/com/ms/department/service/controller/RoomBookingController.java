package com.ms.department.service.controller;

import com.ms.department.service.dto.BookingDetails;
import com.ms.department.service.repository.RoomBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/room-booking")
public class RoomBookingController {

    @Autowired
    private RoomBookingRepo roomBookingRepo;

    @PostMapping
    public BookingDetails save(@RequestBody BookingDetails bookingDetails) {
        return roomBookingRepo.save(bookingDetails);
    }

    @GetMapping("/getAllBookingDetails")
    public List<BookingDetails> getAllBookingDetails() {
        return roomBookingRepo.findAll();
    }

    @GetMapping("/findByGuest/{guestName}")
    public List<BookingDetails> findBookingDetailsByGuestName(@PathVariable String guestName) {
        return roomBookingRepo.findBookDetailsByGuestName(guestName);
    }

    @GetMapping("/findByDate")
    public List<BookingDetails> findBookingDetailsByBookingDate(@RequestParam("bookingDate")
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return roomBookingRepo.findBookDetailsByBookingDate(localDate);
    }
}
