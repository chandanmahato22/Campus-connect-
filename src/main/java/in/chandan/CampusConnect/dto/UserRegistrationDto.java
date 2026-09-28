package in.chandan.CampusConnect.dto;

import in.chandan.CampusConnect.entity.Club;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {
    private Long uid;

    private String name;

    private String password;

    private String branch;

    private String club_name;
}
