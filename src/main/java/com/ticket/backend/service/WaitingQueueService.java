package com.ticket.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WaitingQueueService {

    //Redis
    private final RedisTemplate<String, String> redisTemplate;

    //대기 리스트명
    private static final String WAITING_QUEUE_KEY = "waiting:queue";
    private static final String ACTIVE_QUEUE_KEY = "active:queue";

    public String registerQueue(){
        //토큰생성

        String token = UUID.randomUUID().toString();

        long timestamp = System.currentTimeMillis();
        //도착한 시간 계산
        redisTemplate.opsForZSet().add(WAITING_QUEUE_KEY, token, timestamp);

        return token;
    }

    //대기 순번 조회

    public Long getWaitingRank(String token){
        Long rank = redisTemplate.opsForZSet().rank(WAITING_QUEUE_KEY, token);

        return rank != null ? rank +1 : -1L;
    }

    //입장 10sec-3
    @Scheduled(fixedRate = 10000)
    public void letUsersEnter(){
        //대기열에서 인덱스 가지고오기
        Set<String> tokens = redisTemplate.opsForZSet().range(WAITING_QUEUE_KEY, 0, 2);

        if(tokens!=null&&!tokens.isEmpty()){
            for(String token: tokens){
                //대기열 밖으로 빼기
                redisTemplate.opsForSet().add(ACTIVE_QUEUE_KEY, token);

                //기다리는 줄에서 제거
                redisTemplate.opsForZSet().remove(WAITING_QUEUE_KEY, token);
            }
            System.out.println(tokens.size()+"명 입장완료");
        }
    }

    public boolean isAllowedToEnter(String token){
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(ACTIVE_QUEUE_KEY, token));
    }
}
