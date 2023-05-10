package com.ms.department.service.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    public String guestName;
    public LocalDate bookingDate;
}
