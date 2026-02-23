import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
vus: 100, // 가상 유저 100명
duration: '10s', // 10초 동안 공격
        };

export default function () {
    const host = 'http://localhost:8080';

        // 1. 번호표 발급 (POST 요청)
        let joinRes = http.post(`${host}/api/queue/join`);
        let token = joinRes.body.split("토큰: ")[1]; // 응답에서 토큰 추출

        // 2. 대기 순번 조회 (GET 요청)
        let rankRes = http.get(`${host}/api/queue/rank?token=${token}`);

        // 3. 1초 쉬고 (사용자가 기다리는 시간)
        sleep(1);

        check(joinRes, { 'is status 200': (r) => r.status === 200 });
}