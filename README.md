### 서비스 설명

---
LiveCommerce 프로젝트는 오늘의집, 쿠팡과 같은 Modular Monolithic 기반의 E-Commerce 애플리케이션입니다.

### 사용 기술 스택

---
- JAVA 11
- SpringBoot 2.7.2
- JPA
- MyBats 2.2.2
- MySQL 8.0.28
- Redis 7.0.5
- Flyway 8.5.13
- Docker

### 트러블 슈팅 & 기술 선택 과정

---
[블로그 링크 첨부 예정]

- 동시성 이슈로 발생할 수 있는 재고의 수량 불일치 문제 해결
- 서로의 잠금을 획득하는 순환구조로 인하여 데드락을 발생시키는 문제 해결
- Flyway 의 버전업데이트로 인해 발생한 문제 해결
- 중복되는 사용자의 DB 조회로 redis 의 역할을 제대로 하지 못하는 문제 해결
- 사용자의 관점, 긴 트랜잭션에서 발생할 수 있는 문제점을 생각한 비동기 도입
- 모듈간의 응집도와 결합도를 고려하여 내부 도메인 이벤트 도입
- 브루트포스 공격이 불가능한 비밀번호 단반향 암호화 알고리즘 선정
- 복잡한 소프트웨어 시스템을 구조화 하기 위한 레이어드 아키텍처 선정 및 적용
- 의존성을 고려하지 않고 편의에 의한 개발을 막기 위한 Multi-Modular 구성


### Testing

---
Unit Test Command   
- Application layer 의 비즈니스 로직을 테스트 할 수 있습니다.     
`$ ./gradlew test`

### 아키텍처

---
<img src="https://user-images.githubusercontent.com/21376853/218402641-33a6e95e-fa9e-4dc6-a340-d72c7234ea09.png" width="1200" height="800"/>

Modular Monolithic 아키텍처이므로, 다음과 같은 모듈들로 구성되어 있습니다.

| Modules       | Description                                  |
|---------------|----------------------------------------------|
| Server        | 각 도메인 별 모듈을 통합하고 실행하는 Main 애플리케이션이 있는 모듈입니다. |
| Common        | 모든 모듈에서 전역적으로 사용하는 공통 개체가 담긴 모듈입니다.          |
| User-Api      | 사용자 관리 기능을 담당하는 모듈입니다.                       |
| Seller-Api    | 판매자 관리 기능을 담당하는 모듈입니다.                       |
| Order-Api     | 주문을 생성하고, 주문을 관리 및 처리하는 기능을 담당하는 모듈입니다.      |
| Payment-Api   | 주문한 상품에 대한 결제 처리를 담당하는 모듈입니다. (현재 Fake로 구현)  |
| Item-Api      | 상품을 관리하는 기능을 담당하는 모듈입니다.                     |
| Inventory-Api | 재고를 관리하는 기능을 담당하는 모듈입니다.                     |

각 도메인 모듈은 하위에 아래와 같이 Layered Architecture 형식으로 분리되어 구성됩니다.

예시)

|Build Artifacts| Description                                                      |
|------|------------------------------------------------------------------|
|Order-Presentation| 주문 HTTP Api에 대한 요청 및 응답을 제공하는 모듈입니다.                             |
|Order-Application| 주문 로직에 대한 비즈니스 로직을 정의하며 정상적으로 수행될 수 있도록 도메인, 인프라 계층과 협력하는 모듈입니다. |
|Order-Domain| 실직적인 주문의 정보를 가지며, 주문의 상태를 직접적으로 제어하는 모듈입니다.                      |
|Order-Infrastructure| DIP를 통한 상위 계층을 지원하는 일반화된 기술적 기능을 제공하는 계층 모듈입니다.                  |

### Elastic APM Setting

--- 
**apm-server**

```dockerfile
docker-compose -f apm-docker-compose.yml up
```

**apm-agent options**   
```
-javaagent:./agent/elastic-apm-agent-1.34.0.jar
-Delastic.apm.service_name=Livecommerce
-Delastic.apm.server_url=http://localhost:8200
-Delastic.apm.application_packages=com.flab.*
-Delastic.apm.transaction_sample_rate=1
-Delastic.apm.enable_log_correlation=true
-Delastic.apm.span_frames_min_duration=1ms
-Delastic.apm.span_min_duration=0ms
-Delastic.apm.trace_methods_duration_threshold=1ms
-Delastic.apm.trace_methods=com.flab.*
```