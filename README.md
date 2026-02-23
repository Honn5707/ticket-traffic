# 티켓 예매 서비스 (Ticket-Service)

### 🚀 Tech Stack
- **Backend:** Spring Boot, Spring Data JPA
- **Database:** MySQL 8.0 (Replication: Master-Slave)
- **Infra:** Docker, Docker-Compose
- **Test:** k6 (Load Testing)

### 🏗️ Architecture: DB Replication
- **Master (Write):** 모든 CUD 요청 처리 및 Binary Log 생성
- **Slave (Read):** Master의 로그를 복제하여 읽기 전용 요청 분산 처리
- **Routing:** `AbstractRoutingDataSource`를 이용한 트래픽 분기 구현

### 📊 Performance Test (k6)
- **VUs:** 100
- **Duration:** 10s
- **Success Rate:** 100% (Error 0.00%)
- **Avg Response Time:** 13.5ms
