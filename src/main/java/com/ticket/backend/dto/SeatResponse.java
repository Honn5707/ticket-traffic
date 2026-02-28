package com.ticket.backend.dto;

import com.ticket.backend.domain.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable; // 객체를 바이트로 허락

@Getter
@NoArgsConstructor
public class SeatResponse implements Serializable{
    private Long id;
    private int seatNumber;
    private boolean isReserved;



    //엔티티를 DTO로
    public  SeatResponse (Seat seat) {

        this.id = seat.getId();
        this.seatNumber = seat.getSeatNumber();
        this.isReserved = seat.isReserved();
    }

    public static SeatResponse from(Seat seat) {
        return new SeatResponse(
                seat
        );
    }
}
