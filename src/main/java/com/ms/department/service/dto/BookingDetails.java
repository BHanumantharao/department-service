package com.ms.department.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include. NON_NULL)
@RedisHash("RoomBooking")
public class BookingDetails implements Serializable {
    private static final long serialVersionUID = -4439114469417994311L;

    @Id
    private String id;
    public String guestName;
    public int roomNumber;
    public LocalDate bookingDate;
    public String description;

}
