package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor @Getter @Setter
@Entity
@Table(name = "member")
public class Member {
    @Id
    private int id;
    @Column(length = 12, nullable = false, unique = true)
    private String username;
    @Column(length = 25, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(length = 50)
    private String street;
    @Column(length = 50)
    private String city;
    @Column(length = 25)
    private String zip;
    private boolean approved;
    private int ranking;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime lastEdited;

    public Member(String user, String password, String email, String firstName,
                  String lastName, String street, String city, String zip) {
        this.username = user;
        this.password= password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }


}
