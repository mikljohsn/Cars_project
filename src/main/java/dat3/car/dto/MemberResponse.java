package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //A must for @Builder
@Builder  //I will demo its purpose in the class
//It's really IMPORTANT that you understand the purpose of this annotation
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    String username; //Remember this is the primary key
    //Observe password is obviously not included
    String email;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;
    //@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;
    Integer ranking; //Brug wrapper-klasser da de godt kan være NULL
    Boolean approved;


    // Reservation list
    List<ReservationResponse> reservations;

    //Convert Member Entity to Member DTO
    public MemberResponse(Member member, boolean includeAll) {
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.street = member.getStreet();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.city = member.getCity();
        this.zip = member.getZip();
        if(includeAll){
            this.created = member.getCreated();
            this.edited = member.getEdited();
            this.approved = member.isApproved();
            this.ranking = member.getRanking();
        }
     /*   if (member.getReservations() != null && !member.getReservations().isEmpty()) { //hvis member har en reservation tilføjes de til listen gennem streams
            this.reservations = member.getReservations().stream()
                    .map(ReservationResponse::new)
                    .collect(Collectors.toList()); metode til at tilføje en liste af reservationer gennem constructoren
        }*/
    }
    public MemberResponse(Member member, boolean includeAll, boolean includeReservations){ //ny constructor der peger på den gamle, og bruger et flag til at få listen med
        this(member, false);
        if(includeReservations){
            this.reservations = member.getReservations().stream().map(response -> new ReservationResponse(response))
                    .collect(Collectors.toList());
        }
    }
}


