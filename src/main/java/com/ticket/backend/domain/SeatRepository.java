package com.ticket.backend.domain;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
//seat테이블을 관리하는 규칙

public  interface SeatRepository extends JpaRepository<Seat, Long>{

    //비관적 락

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select  s from Seat s where s.id = :id")
    Optional<Seat> findByIdWithPessimisticLock(Long id);
}