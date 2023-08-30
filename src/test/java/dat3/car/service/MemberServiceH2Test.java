package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email1", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(true);
        assertEquals(2, memberResponses.size(), "Expects two members");
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNotNull(time, "Expects date to be set when TRUE is passed");
    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(false);
        assertEquals(2, memberResponses.size(), "Expects two members");
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNull(time, "Expects date to not be set when FALSE is passed");
    }

    @Test
    void testFindByIdFound() {
        MemberResponse response = memberService.findById("user1");
        assertEquals("user1", response.getUsername());
    }

    @Test
    void testFindByIdNotFound() {
        assertThrows(ResponseStatusException.class, () -> memberService.findById("I don't exist"));
    }

    @Test
        /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
         * Internally addMember savex a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {
        //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
        //TODO
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 409 (BAD_REQUEST)
        //TODO
    }

    @Test
    void testEditMemberWithExistingUsername() {
        //TODO
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        //TODO
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        //TODO
    }

    @Test
    void testSetRankingForUser() {
        memberService.setRankingForUser("user1",1);
        //Member testMember = memberRepository.findById("user1").get();
        assertEquals(1,m1.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
    assertThrows(ResponseStatusException.class, () -> memberService.findById("I don't exist"));

    //fordi vi i MemberService har getMemberByUsernamem der thrower en ResponseStatusException, laver vi en assertThrows på den exception og på et brugernavn der ikke findes
    }
    @Test
    void testDeleteMemberByUsername() {
        memberService.deleteMemberByUsername("user1");
        assertThrows(ResponseStatusException.class, () -> memberService.findById("user1")); //metoden bør throw en execption, da brugernavnet nu íkke eksisterer
        assertEquals(1, memberRepository.count()); //tjekker for count i den nye database

    }

    @Test
    void testDeleteMember_ThatDontExist() {
        assertThrows(ResponseStatusException.class, () -> memberService.findById("I don't exist"));
    }
}

