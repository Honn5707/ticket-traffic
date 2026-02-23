package com.ticket.backend.init;

import com.ticket.backend.domain.Seat;
import com.ticket.backend.domain.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component // 관리
@RequiredArgsConstructor // 레파지토리 자동연결
public class DataInitializer implements CommandLineRunner {

    //레파지토리 가져오기
    private final SeatRepository seatRepository;

    @Override

    public void run(String... args){
        //4.좌석 10개를 반복문으로 생성해서 DB에 저장합니다.
        for(int i = 1; i<=10; i++){
            Seat seat = new Seat(i); //i 좌석 객체 생성
            seatRepository.save(seat); // 리모콘의 save버튼 클릭
        }

        System.out.println("좌석 10개 생성");
    }
}