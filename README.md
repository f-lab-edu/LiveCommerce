### 서비스 설명


LiveCommerce 프로젝트는 오늘의집, 쿠팡과 같은 Modular Monolithic 기반의 E-Commerce 애플리케이션입니다.
사용자가 상품에 대해 주문을 하고 결제가 이루어지면 재고가 차감되는 일반적인 쇼핑몰의 기능을 구현을 했습니다.

팀 프로젝트 기간이 끝나 포크를 하여 따로 개선할 점을 찾아 작업 중 입니다.

---
### 사용 기술 스택


- JAVA 11
- SpringBoot 2.7.2
- JPA
- MyBatis 2.2.2
- MySQL 8.0.28
- Redis 7.0.5
- Flyway 8.5.13
- Docker

---
### 트러블 슈팅 & 기술 선택 과정

[위키에 있는 내용은 더 디테일 하게 작업하여 차근차근 블로그로 링크 첨부 예정]

- [동시성 이슈로 발생할 수 있는 재고의 수량 불일치 문제 해결](https://github.com/dding94/LiveCommerce/wiki/%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-&-%EA%B8%B0%EC%88%A0-%EC%84%A0%ED%83%9D-%EA%B3%BC%EC%A0%95#%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%9D%B4%EC%8A%88%EB%A1%9C-%EB%B0%9C%EC%83%9D%ED%95%A0-%EC%88%98-%EC%9E%88%EB%8A%94-%EC%9E%AC%EA%B3%A0%EC%9D%98-%EC%88%98%EB%9F%89-%EB%B6%88%EC%9D%BC%EC%B9%98-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0)
- [서로의 잠금을 획득하는 순환구조로 인하여 데드락을 발생시키는 문제 해결](https://github.com/dding94/LiveCommerce/wiki/%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-&-%EA%B8%B0%EC%88%A0-%EC%84%A0%ED%83%9D-%EA%B3%BC%EC%A0%95#%EC%84%9C%EB%A1%9C%EC%9D%98-%EC%9E%A0%EA%B8%88%EC%9D%84-%ED%9A%8D%EB%93%9D%ED%95%98%EB%8A%94-%EC%88%9C%ED%99%98%EA%B5%AC%EC%A1%B0%EB%A1%9C-%EC%9D%B8%ED%95%98%EC%97%AC-%EB%8D%B0%EB%93%9C%EB%9D%BD%EC%9D%84-%EB%B0%9C%EC%83%9D%EC%8B%9C%ED%82%A4%EB%8A%94-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0)
- [Flyway의 버전 업데이트로 인해 발생한 문제 해결 [블로그]](https://dding9code.tistory.com/121)
- [중복되는 사용자의 DB 조회로 redis 의 역할을 제대로 하지 못하는 문제 해결](https://github.com/dding94/LiveCommerce/wiki/%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-&-%EA%B8%B0%EC%88%A0-%EC%84%A0%ED%83%9D-%EA%B3%BC%EC%A0%95#%EC%A4%91%EB%B3%B5%EB%90%98%EB%8A%94-%EC%82%AC%EC%9A%A9%EC%9E%90%EC%9D%98-db-%EC%A1%B0%ED%9A%8C%EB%A1%9C-redis-%EC%9D%98-%EC%97%AD%ED%95%A0%EC%9D%84-%EC%A0%9C%EB%8C%80%EB%A1%9C-%ED%95%98%EC%A7%80-%EB%AA%BB%ED%95%98%EB%8A%94-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0)
- [사용자의 관점, 긴 트랜잭션에서 발생할 수 있는 문제점을 생각한 비동기 도입](https://github.com/dding94/LiveCommerce/wiki/%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-&-%EA%B8%B0%EC%88%A0-%EC%84%A0%ED%83%9D-%EA%B3%BC%EC%A0%95#%EC%82%AC%EC%9A%A9%EC%9E%90%EC%9D%98-%EA%B4%80%EC%A0%90-%EA%B8%B4-%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98%EC%97%90%EC%84%9C-%EB%B0%9C%EC%83%9D%ED%95%A0-%EC%88%98-%EC%9E%88%EB%8A%94-%EB%AC%B8%EC%A0%9C%EC%A0%90%EC%9D%84-%EC%83%9D%EA%B0%81%ED%95%9C-%EB%B9%84%EB%8F%99%EA%B8%B0-%EB%8F%84%EC%9E%85)
- [모듈 간의 응집도와 결합도를 고려하여 내부 도메인 이벤트 도입](https://github.com/dding94/LiveCommerce/wiki/%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-&-%EA%B8%B0%EC%88%A0-%EC%84%A0%ED%83%9D-%EA%B3%BC%EC%A0%95#%EB%AA%A8%EB%93%88-%EA%B0%84%EC%9D%98-%EC%9D%91%EC%A7%91%EB%8F%84%EC%99%80-%EA%B2%B0%ED%95%A9%EB%8F%84%EB%A5%BC-%EA%B3%A0%EB%A0%A4%ED%95%98%EC%97%AC-%EB%82%B4%EB%B6%80-%EB%8F%84%EB%A9%94%EC%9D%B8-%EC%9D%B4%EB%B2%A4%ED%8A%B8-%EB%8F%84%EC%9E%85)
- [브루트포스 공격이 불가능한 비밀번호 단방향 암호화 알고리즘 선정](https://github.com/dding94/LiveCommerce/wiki/%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-&-%EA%B8%B0%EC%88%A0-%EC%84%A0%ED%83%9D-%EA%B3%BC%EC%A0%95#%EB%B8%8C%EB%A3%A8%ED%8A%B8%ED%8F%AC%EC%8A%A4-%EA%B3%B5%EA%B2%A9%EC%9D%B4-%EB%B6%88%EA%B0%80%EB%8A%A5%ED%95%9C-%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8-%EB%8B%A8%EB%B0%A9%ED%96%A5-%EC%95%94%ED%98%B8%ED%99%94-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%84%A0%EC%A0%95)
- [복잡한 소프트웨어 시스템을 구조화하기 위한 레이어드 아키텍처 선정 및 적용](https://github.com/dding94/LiveCommerce/wiki/%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-&-%EA%B8%B0%EC%88%A0-%EC%84%A0%ED%83%9D-%EA%B3%BC%EC%A0%95#%EB%B3%B5%EC%9E%A1%ED%95%9C-%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4-%EC%8B%9C%EC%8A%A4%ED%85%9C%EC%9D%84-%EA%B5%AC%EC%A1%B0%ED%99%94%ED%95%98%EA%B8%B0-%EC%9C%84%ED%95%9C-%EB%A0%88%EC%9D%B4%EC%96%B4%EB%93%9C-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EC%84%A0%EC%A0%95-%EB%B0%8F-%EC%A0%81%EC%9A%A9)
- [의존성을 고려하지 않고 편의에 의한 개발을 막기 위한 Multi-Modular 구성](https://github.com/dding94/LiveCommerce/wiki/%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-&-%EA%B8%B0%EC%88%A0-%EC%84%A0%ED%83%9D-%EA%B3%BC%EC%A0%95#%EC%9D%98%EC%A1%B4%EC%84%B1%EC%9D%84-%EA%B3%A0%EB%A0%A4%ED%95%98%EC%A7%80-%EC%95%8A%EA%B3%A0-%ED%8E%B8%EC%9D%98%EC%97%90-%EC%9D%98%ED%95%9C-%EA%B0%9C%EB%B0%9C%EC%9D%84-%EB%A7%89%EA%B8%B0-%EC%9C%84%ED%95%9C-multi-modular-%EA%B5%AC%EC%84%B1)
- [Mybatis 복잡한 연관관계에서 발생한 문제 해결 [블로그]](https://dding9code.tistory.com/122) 


---
### Testing

Unit Test Command
- Application layer 의 비즈니스 로직을 테스트 할 수 있습니다.     
  `$ ./gradlew test`

---
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
|Order-Domain| 실질적인 주문의 정보를 가지며, 주문의 상태를 직접적으로 제어하는 모듈입니다.                      |
|Order-Infrastructure| DIP를 통한 상위 계층을 지원하는 일반화된 기술적 기능을 제공하는 계층 모듈입니다.                  |

--- 
### 데이터베이스 스키마
![데이터베이스스키마](https://user-images.githubusercontent.com/21376853/222174173-7e1aa320-649b-4829-90ff-8ecab7e573a5.png)
<details><summary> 스키마 아이콘의 설명   </summary>

![스크린샷 2023-02-25 오전 12 01 18](https://user-images.githubusercontent.com/21376853/221215776-6939a78e-5b08-4660-a653-3ad438d55a20.png)
</details>


---

### 공통 응답 객체

| 필드                   | 타입      | 필수여부 | 설명                                                            | 
|----------------------|---------|------|---------------------------------------------------------------|
| success              | Boolean | 필수   | API 호출 실행 결과입니다.<br/>[true]:성공 <br/>[false]: 실패               | 
| data                 | Object  | 필수   | API 호출에 따른 결과값입니다.                                            | 
| error{errorCode}     | String  | 필수   | API 호출 오류 상황을 구분하는 코드입니다.<br/>(API 호출 실패시 제공합니다. 호출 성공시 null) | 
| error{errorMessage}  | String  | 필수   | API 호출 오류 상세 설명입니다.<br/>(API 호출 실패시 제공합니다. 호출 성공시 null)        |

---
### Elastic APM Setting

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
