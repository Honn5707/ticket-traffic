package com.ticket.backend.facade;

import com.ticket.backend.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
@RequiredArgsConstructor
public class RedisLockFacade {

    private final RedissonClient redissonClient;
    private final SeatService seatService;

    public void reserve(Long seatId){
        //1. "seat:1" 같은 이름표로 1번방 전용 자물 대기줄
        RLock lock = redissonClient.getLock("seat: "+seatId);

        try{
            //redis에 요청, 5초기대, 3초사용
            boolean available = lock.tryLock(5,3,TimeUnit.SECONDS);

            if(!available){
                //5초대기가 지남
                System.out.println("대기줄이 너무 길어 락 획득 실패(seatId" + seatId +")");
                throw new RuntimeException("현재 접속자가 많아 예약할 수 없습니다. 잠시 후 다시 시도해주세요.");
            }

            seatService.reserve(seatId);
        }catch (InterruptedException e){
            //예외처리
            throw new RuntimeException("예약 대기중 에러가 발생하였습니다");
        }finally {
            //예약이 끝났어 반드시 락을반납
            if(lock.isLocked() && lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
}
