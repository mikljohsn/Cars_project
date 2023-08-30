package dat3.car.repository;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class MemberRepositoryTest {
@Autowired
    MemberRepository memberRepository;
    private boolean isInitialzied = false;

   @BeforeEach
    void setUp() {
        if(!isInitialzied) {
            memberRepository.deleteAll();
            memberRepository.save(new Member("john_smith", "smithpass", "john.smith@example.com", "Jane", "Smith", "456 Elm St", "Los Angeles", "90001"));
            memberRepository.save(new Member("micaelJ", "mikepass", "micael.johnson@example.com", "Michael", "Johnson", "789 Oak St", "Chicago", "60601"));
            memberRepository.save(new Member("emilW", "emilypass", "emil.williams@example.com", "Emily", "Williams", "101 Maple St", "Houston", "77001"));
            isInitialzied = true;
        }
    }
    @Test
    public void testAll(){
        Long count = memberRepository.count();
        assertEquals(3,count);
    }
    @Test
    public void deleteAll(){
        memberRepository.deleteAll();
        assertEquals(0,memberRepository.count());
    }

}