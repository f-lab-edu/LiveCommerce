//package com.flab.livecommerce.jpapractice;
//
//
//import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Slf4j
//public class JpaTest {
//
//    @Autowired
//    private SampleUserRepository userRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @BeforeEach
//    @Rollback(value = false)
//    void before() {
//        SampleUser user = new SampleUser("정명구");
//        userRepository.save(user);
//        entityManager.flush();
//        entityManager.clear();
//    }
//
//    @Test
//    @DisplayName("한 트랜잭션 내에서 findById 두번 호출 하면?")
//    void findById_call_twice() {
//        //act
//        SampleUser user1 = userRepository.findById(1L).get();
//        SampleUser user2 = userRepository.findById(1L).get();
//
//        //Assert
//        assertThat(user1).isEqualTo(user2);
//        assertThat(user1 == user2).isTrue();
//
//        // select query 1번
//        // 두 번째 객체는 1차 캐시에 있는 객체 반환
//    }
//
//    @Test
//    @DisplayName("findById, findByName 을 호출 할 경우에 쿼리는?")
//    void findById_first_findByName_second() {
//        //act
//        SampleUser user1 = userRepository.findById(1L).get();
//        SampleUser user2 = userRepository.findByName("정명구");
//
//        log.info("user1 ={}", user1.toString());
//        log.info("user2 ={}", user2.toString());
//
//        //Assert
//        assertThat(user1).isEqualTo(user2);
//        assertThat(user1 == user2).isTrue();
//
//        // select query 2번
//        // persist context 에는 Id / entity 형태로 저장되어 있다.
//        // 때문에 findByName 은 영속성 컨텍스트에 있는 객체를 가져올 수 없다.
//        // findById, Name 사이 중간에 값이 바껴도 동일한 엔티티를 반환한다.
//    }
//
//    @Test
//    @DisplayName("findByName, findById 를 호출 할 경우에 쿼리는?")
//    void findByName_first_findById_second() {
//        //act
//        SampleUser user1 = userRepository.findByName("정명구");
//        SampleUser user2 = userRepository.findById(1L).get();
//
//        //Assert
//        assertThat(user1).isEqualTo(user2);
//        assertThat(user1 == user2).isTrue();
//
//        // select query 1번
//        // name 으로 호출하여 영속성 컨텍스트에 영속화하고
//        // 두번째는 식별자로 조회하기 때문에 1차 캐시에 있는 엔티티를 반환받는다.
//    }
//
//    @Test
//    @DisplayName("findByName, findByName 을 호출 할 경우에 쿼리는?")
//    void findByName_call_twice() {
//        //act
//        SampleUser user1 = userRepository.findByName("정명구");
//        SampleUser user2 = userRepository.findByName("정명구");
//
//        //Assert
//        assertThat(user1).isEqualTo(user2);
//        assertThat(user1 == user2).isTrue();
//
//        // select query 2번
//        // 식별자를 통하여 호출하지 않기 때문에 db 에서 조회가 발생
//    }
//
//    @Test
//    @DisplayName("findById -> em.clear -> findById 할 경우 같은 엔티티일까?")
//    void findById_EntityManager_clear_findById() {
//        //act
//        SampleUser user1 = userRepository.findById(1L).get();
//        entityManager.clear();
//        SampleUser user2 = userRepository.findById(1L).get();
//
//        log.info(">>>>user1 변경 전 user1={}", user1.toString());
//        log.info(">>>>user1 변경 전 user2={}", user1.toString());
//        user1.setName("변경 완료");
//        log.info(">>>>user1 변경 후 user1={}", user1.toString());
//        log.info(">>>>user1 변경 후 user2={}", user1.toString());
//
//        user2.setName("user2 변경 완료");
//        entityManager.flush();
//        log.info(">>>>user2 변경 후 user1={}", user1.toString());
//        log.info(">>>>user2 변경 후 user2={}", user1.toString());
//        //Assert
//        assertThat(user1).isNotEqualTo(user2);
//        assertThat(user1 == user2).isFalse();
//
//        // 다른 객체를 반환 함
//        // 객체의 주소값이 다름
//    }
//
//
//}
