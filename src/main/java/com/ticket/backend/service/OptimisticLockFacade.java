package com.ticket.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException; // 이 import 꼭 확인!
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptimisticLockFacade {

    private final SeatService seatService;

    public void reserve(Long id) throws InterruptedException {
        int attempt = 1; // 몇 번째 시도인지 세어봅시다

        while (true) {
            try {
                seatService.reserve(id);
                // 📢 성공 로그
                System.out.println("🎉 예약 성공! (시도 횟수: " + attempt + "회)");
                break;

            } catch (ObjectOptimisticLockingFailureException e) {
                // 📢 충돌(버전 불일치) 로그 -> 재시도 해야 함
                System.out.println("💥 충돌 발생! " + attempt + "번째 재시도 중...");
                Thread.sleep(50); // 0.05초 대기
                attempt++;

            } catch (Exception e) {
                // 📢 매진(이미 예약됨) 로그 -> 포기해야 함
                System.out.println("⛔ 포기합니다: " + e.getMessage());
                throw e;
            }
        }
    }
}