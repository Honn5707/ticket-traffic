package com.ticket.backend.exception; // 패키지명 확인!

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice // 📢 저는 이 구역의 민원 담당 매니저입니다!
public class GlobalExceptionHandler {
    //이미 예약된 좌석
    @ExceptionHandler(IllegalArgumentException.class)
    public  ResponseEntity<String> handleSoldOut(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("죄송합니다 " + e.getMessage());
    }

    //동시성 처리
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<String> handleSoldOut(ObjectOptimisticLockingFailureException e){
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).
                body("접속자가 많아 처리가 지연되고있슴다");
    }

    // 그 외 예상치 못한 모든 에러 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e){
        // 📢 1. 인텔리제이 콘솔창에 진짜 빨간줄 에러를 뱉어내게 합니다!
        e.printStackTrace();

        // 📢 2. 브라우저 화면에도 진짜 이유를 슬쩍 보여주도록 바꿉니다.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("서버에 문제가 발생하였습니다. 진짜 이유: " + e.getMessage());
    }

}