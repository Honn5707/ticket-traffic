package com.ticket.backend.exception;

public class SeatAlreadyReservedException extends RuntimeException{
    public SeatAlreadyReservedException(String msg){
        super(msg);
    }
}
