package in.chandan.CampusConnect.entity;

import in.chandan.CampusConnect.enums.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    private Long uid;

    private String name;

    private String password;

    private LocalDateTime createdAt;

    private String branch;

    @OneToOne
    @Nullable
    @JoinColumn(name = "club_id")
    private Club club;

    @Enumerated(EnumType.STRING)
    private Role role;

}
