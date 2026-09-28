package in.chandan.CampusConnect.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @JoinColumn(name  = "club_id")
    private Club club;
}
