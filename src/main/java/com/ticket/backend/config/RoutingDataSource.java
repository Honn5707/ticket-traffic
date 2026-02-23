package com.ticket.backend.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        //ReadOnly인지 확인
        boolean isReadyOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        if (isReadyOnly) {
            System.out.println("읽기요청(Slave(3307))");
            return "slave";
        } else {
            System.out.println("쓰기요청(Master(3306))");
            return "master";
        }
    }
}

