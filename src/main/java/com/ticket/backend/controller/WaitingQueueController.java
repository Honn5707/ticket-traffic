package com.ticket.backend.controller;

import com.ticket.backend.service.WaitingQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/queue")
@RequiredArgsConstructor
public class WaitingQueueController
{
    private final WaitingQueueService waitingQueueService;

    //최초호출
    @PostMapping("/join")
    public String joinQueue(){
        String token = waitingQueueService.registerQueue();
        return "토큰: "+ token;
    }

    //내 순서 확인하기API
    @GetMapping("rank")
    public String checkRanK(@RequestParam String token){
        if(waitingQueueService.isAllowedToEnter(token)){

            return "입장차례가 되었습니다 토큰:" + token;
        }
        Long rank = waitingQueueService.getWaitingRank(token);

        if(rank == -1L){
            return "대기열에 존재하지 않는 토큰입니다";
        }
        return "대기순번:"+rank +"입니다";
    }
}