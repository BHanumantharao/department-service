package com.ms.department.service.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("RoomBooking")
public class BookingDetails implements Serializable {
    private static final long serialVersionUID = -4439114469417994311L;

    @Id
    private int id;
    public String guestName;
    public int roomNumber;
    public LocalDate bookingDate;

}
