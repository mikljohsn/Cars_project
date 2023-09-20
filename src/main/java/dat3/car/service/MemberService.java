package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();
        /*List<MemberResponse> response = new ArrayList<>();
       for(Member member: members){ //gammel måde at finde en liste og tilføje til en ny
            MemberResponse mr = new MemberResponse(member,includeAll);
            response.add(mr);
        }
        return response;*/
        return members.stream().map(((member) -> new MemberResponse(member, includeAll))).toList(); //1 line ved brug af streams,
        //hvor en member mappes til en memberresponse med member som parameter og vores includeAll flag.
    }    public List<MemberResponse> getMembersWithReservations(boolean includeAll, boolean includeReservations) { //ny get members til at finde members og deres reservation. Kald den!
        List<Member> members = memberRepository.findAll();
        return members.stream().map(((member) -> new MemberResponse(member, includeAll, includeReservations))).toList();
    }

    public MemberResponse addMember(MemberRequest body) {
        if(memberRepository.existsById(body.getUsername())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"This user already exists");
        }
        Member newMember = MemberRequest.getMemberEntity(body);
        newMember = memberRepository.save(newMember); //save metoden returnerer objektet fra databasen, derfor bruger vi variablen newMember igen, da den nu har "created" på
        return new MemberResponse(newMember, true);
    }
    public ResponseEntity<Boolean> editMember(MemberRequest body, String username) {
        Member member = memberRepository.findById(username).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this username does not exist"));
        if(!body.getUsername().equals(username)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change username");
        }
        member.setPassword(body.getPassword());
        member.setEmail(body.getEmail());
        member.setFirstName(body.getFirstName());
        member.setLastName(body.getLastName());
        member.setStreet(body.getStreet());
        member.setCity(body.getCity());
        member.setZip(body.getZip());
        memberRepository.save(member);
        return ResponseEntity.ok(true);
    }

    public MemberResponse findById(String username) {
        Member member = memberRepository.findById(username).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this username does not exist"));
        return new MemberResponse(member, true);
    }
    public void setRankingForUser(String username, int value) { //TODO kald metoderne i controlleren
        Member member = getMemberByUsername(username);
        member.setRanking(value);
        memberRepository.save(member);
    }


    public void deleteMemberByUsername(String username) {
        Member member = getMemberByUsername(username);
        memberRepository.delete(member);
    }


    private Member getMemberByUsername(String username){ //det er okay at bruge et member her, da den er private og kun bliver brugt i klassen
        return memberRepository.findById(username).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Member with this username does not exist"));
    }

}
