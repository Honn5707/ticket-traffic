package com.ticket.backend.service;

import com.ticket.backend.domain.Seat;
import com.ticket.backend.domain.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service// 1.나는 비지니스 로직을 담당하는 주방장이야!
@RequiredArgsConstructor
public class SeatService {
    @Transactional(readOnly = true)
    public List<Seat> findAll() { return seatRepository.findAll(); }
    private final SeatRepository seatRepository;


    @Transactional() // 2.이 안의 과정은 하나로 묶여야해!
    @CacheEvict(cacheNames = "seatsList", allEntries = true)
    public void reserve(Long seatID){
        //3. DB에서 해당 좌석을 꺼내온다 없으면 에러
        Seat seat = seatRepository.findById(seatID).orElseThrow(()->new IllegalArgumentException(("존재하지않은 좌석")));

        //4.예약
        seat.reserve();

        //5. save()를 명시적으 안해도 @Transactional이 끝나면 자동으로 DB에 반영됩니다. (더티 체킹)
    }

    @Transactional
    public void reserveWithPessimisticLock(Long seatId){
        Seat seat = seatRepository.findByIdWithPessimisticLock(seatId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지않는 좌석입니다"));
        seat.reserve();
    }

    @CacheEvict(cacheNames = "seatsList", allEntries = true)
    @Transactional
    public void createSeats(int count){
        List<Seat> seats = new ArrayList<>();

        for(int i = 1; i<= count; i++){
            //seat객체 생성
            //ex: new Seat(좌석번호, 예약여부false, 버전0)
            Seat seat = new Seat(i);
            seats.add(seat);
        }

        seatRepository.saveAll(seats);
    }

}

