package com.ms.department.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ms.department.service.dto.BookingDetails;
import com.ms.department.service.repository.RoomBookingRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomBookingController.class)
class RoomBookingControllerTest {

    @MockBean
    private RoomBookingRepo roomBookingRepo;
    private RoomBookingController roomBookingController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        roomBookingController = new RoomBookingController();
        roomBookingRepo = Mockito.mock(RoomBookingRepo.class);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        ReflectionTestUtils.setField(roomBookingController, "roomBookingRepo", roomBookingRepo);
    }

    @Test
    void save() throws Exception {
        BookingDetails bookingDetails = new BookingDetails(1, "Hanu", 101, LocalDate.now());
        when(roomBookingRepo.save(bookingDetails)).thenReturn(bookingDetails);
        mockMvc.perform(post("/room-booking")
                        .content(objectMapper.writeValueAsString(bookingDetails))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void getAllBookingDetails() throws Exception {
        List<BookingDetails> bookingDetailsList = new ArrayList<>();
        BookingDetails bookingDetails = new BookingDetails(1, "Hanu", 101, LocalDate.now());
        BookingDetails bookingDetails1 = new BookingDetails(2, "Hanu", 102, LocalDate.now());
        BookingDetails bookingDetails2 = new BookingDetails(3, "Hanu", 103, LocalDate.now());
        bookingDetailsList.add(bookingDetails);
        bookingDetailsList.add(bookingDetails1);
        bookingDetailsList.add(bookingDetails2);
        when(roomBookingRepo.findAll()).thenReturn(bookingDetailsList);
        mockMvc.perform(get("/room-booking/getAllBookingDetails")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    void findBookingDetails() throws Exception {
        List<BookingDetails> bookingDetailsList = new ArrayList<>();
        BookingDetails bookingDetails = new BookingDetails(1, "Hanu", 101, LocalDate.now());
        bookingDetailsList.add(bookingDetails);
        when(roomBookingRepo.findBookDetailsByGuestName("Hanu")).thenReturn(bookingDetailsList);
        mockMvc.perform(get("/room-booking/findByGuest/Hanu")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void testFindBookingDetails() throws Exception {
        List<BookingDetails> bookingDetailsList = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        BookingDetails bookingDetails = new BookingDetails(1, "Hanu", 101, localDate);
        bookingDetailsList.add(bookingDetails);
        when(roomBookingRepo.findBookDetailsByBookingDate(localDate)).thenReturn(bookingDetailsList);
        mockMvc.perform(get("/room-booking/findByDate?bookingDate=2023-05-08")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }
}