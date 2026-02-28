package com.ticket.backend.controller;

import com.ticket.backend.domain.Seat;
import com.ticket.backend.domain.SeatRepository;
import com.ticket.backend.dto.ApiResponse;
import com.ticket.backend.facade.RedisLockFacade;
import com.ticket.backend.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ticket.backend.dto.SeatResponse;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;
import com.ticket.backend.service.WaitingQueueService;


import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;
    private final RedisLockFacade redisLockFacade;
    private final WaitingQueueService waitingQueueService;


    @GetMapping
    public ResponseEntity<List<SeatResponse>> getSeats() {

        return ResponseEntity.ok(seatService.findAll());
    }
//        System.out.println("🐌 무거운 DB에서 좌석 목록을 열심히 꺼내오는 중...");
//        return seatService.findAll().stream()
//                .map(SeatResponse::new)
//                .collect(Collectors.toList());
//    }f
    @GetMapping("/{seatId}/reserve")
    public String reserveSeat(@PathVariable Long seatId, @RequestParam String token) {

        // 토큰이 있는 사람이라면
        if(!waitingQueueService.isAllowedToEnter(token)) {
            return "토큰이 없는 접근";
        }
        seatService.reserve(seatId);
        return "좌석 " + seatId + "번 예약 성공!";
    }


    @GetMapping("/init") // 초기화
    public String initSeats(){
        int count = 100;
        long startTime = System.currentTimeMillis(); //시간재기

        seatService.createSeats(count);

        long endTime = System.currentTimeMillis();

        //걸린 시간(ms)을 알려줍니다
        return count + "개 좌석 생성 완료! 걸린시간:" + (endTime - startTime) + "ms)";
    }

    @GetMapping("/{seatId}/reserve-redis")
    public String reserveSeatWithRedis(@PathVariable Long seatId){
        redisLockFacade.reserve(seatId);
        return "reids분산 락으로 "+seatId +"번 예약 성공";
    }

}