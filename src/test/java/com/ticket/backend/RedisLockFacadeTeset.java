package com.ticket.backend;

import com.ticket.backend.domain.Seat;
import com.ticket.backend.domain.SeatRepository;
import com.ticket.backend.facade.RedisLockFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest // 📢 "스프링아, 진짜 서버처럼 똑같이 세팅해서 테스트해 줘!"
class RedisLockFacadeTest {

    @Autowired
    private RedisLockFacade redisLockFacade;

    @Autowired
    private SeatRepository seatRepository;

    @Test
    @DisplayName("100명이 동시에 1번 좌석 예약을 요청한다 (Redis 분산 락)")
    void testRedisLock() throws InterruptedException {
        // 1. 테스트용 1번 좌석을 DB에 몰래 하나 만듭니다.
        Seat seat = new Seat(1);
        seatRepository.save(seat);
        Long seatId = seat.getId();

        // 2. 100명의 스레드(사람)를 준비합니다.
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32); // 일꾼 32명 준비
        CountDownLatch latch = new CountDownLatch(threadCount); // 100명이 다 끝날 때까지 기다리는 타이머

        // 성공한 사람과 실패한 사람 수를 세는 안전한 카운터
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        // 3. 100명이 동시에 출발! (다다다닥 예약 버튼 클릭)
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    redisLockFacade.reserve(seatId);
                    successCount.incrementAndGet(); // 성공하면 카운트 +1
                } catch (Exception e) {
                    failCount.incrementAndGet(); // 자리가 없거나 실패하면 카운트 +1
                } finally {
                    latch.countDown(); // 한 명이 끝날 때마다 타이머 1씩 감소
                }
            });
        }

        latch.await(); // 100명이 다 끝날 때까지 메인 스레드는 대기

        // 4. 결과 발표!
        System.out.println("===============================");
        System.out.println("🎉 예약 성공 인원: " + successCount.get() + "명");
        System.out.println("😭 예약 실패 인원: " + failCount.get() + "명");
        System.out.println("===============================");

        // 5. 검증: "성공한 사람은 무조건 1명이어야만 해!"
        Seat reservedSeat = seatRepository.findById(seatId).get();
        assertEquals(true, reservedSeat.isReserved());
        assertEquals(1, successCount.get()); // 성공 인원이 1명인지 단호하게 체크!
    }
}