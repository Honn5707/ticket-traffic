package com.ticket.backend;
import com.ticket.backend.service.OptimisticLockFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
// ⬇️ 이 줄이 반드시 있어야 합니다!
import com.ticket.backend.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

class SeatConcurrencyTest {

    @Autowired
    private OptimisticLockFacade optimisticLockFacade;

    @Test
    @DisplayName("100명이 동시에 한 좌석을 예약할 때, 딱 1명만 성공해야 한다")
    void concurrencyTest() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failureCount = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    // 1번 좌석을 노린다 (DataInitializer에서 만든 1번 좌석)
                    optimisticLockFacade.reserve(1L);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failureCount.incrementAndGet();
                    System.out.println("예약 실패: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        System.out.println("========================================");
        System.out.println("최종 성공 횟수: " + successCount.get());
        System.out.println("최종 실패 횟수: " + failureCount.get());
        System.out.println("========================================");

        assertEquals(1, successCount.get());
    }
}