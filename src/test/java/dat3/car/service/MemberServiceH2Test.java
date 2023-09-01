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
        m2 = memberRepository.save(new Member("user2", "pw2", "email2", "fn2", "ln2", "street2", "city2", "zip2"));
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
         * Internally addMember saves a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {
        //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
        MemberRequest m3 = MemberRequest.builder().username("user3").password("pw3").firstName("fn3").lastName("ln3").build();

       MemberResponse response = memberService.addMember(m3);
       assertEquals("user3", response.getUsername());
    }

    @Test
    void testAddMember_UserDoesExist() {
        MemberRequest request = new MemberRequest();
        request.setUsername("user1");
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> memberService.addMember(request));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        request.setFirstName("New First Name");
        request.setLastName("New Last Name");

        memberService.editMember(request, "user1");

        memberRepository.flush();
        MemberResponse response = memberService.findById("user1");

        assertEquals("user1", response.getUsername());
        assertEquals("New First Name", response.getFirstName());
        assertEquals("New Last Name", response.getLastName());
        assertEquals("email1", response.getEmail());
        assertEquals("street1", response.getStreet());
        assertEquals("city1", response.getCity());
        assertEquals("zip1", response.getZip());
    }

    void testEditMemberChangePrimaryKey() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        request.setUsername("New Username");
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> memberService.editMember(request, "user1"));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        assertEquals("Cannot change username", ex.getReason());
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

    //fordi vi i MemberService har getMemberByUsername der thrower en ResponseStatusException, laver vi en assertThrows på den exception og på et brugernavn der ikke findes
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

