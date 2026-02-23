package com.ticket.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity //클래스->테이블
@Getter // 룸북: getter메서드 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 침범 불가 파라미터

public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false) // 반드시 존재해야함
    private int seatNumber; // 좌석 번호

    private boolean isReserved;

    @Version // 락을 위한 관리표
    private Long version;

    public Seat(int seatNumber){
        this.seatNumber = seatNumber;
        this.isReserved = false;
    }

    public void reserve(){
        if(this.isReserved){
            throw  new IllegalArgumentException("이미 예약된 좌석입니다!");
        }
        this.isReserved = true;
    }
}