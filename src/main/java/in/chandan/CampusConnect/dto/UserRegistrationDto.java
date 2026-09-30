package in.chandan.CampusConnect.dto;

import in.chandan.CampusConnect.entity.Club;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {
    @NotNull(message = "Uid must not be null or empty")
    private Long uid;

    @NotBlank(message =  "The name can't be empty of null")
    private String name;

    @NotBlank(message =  "The password can't be empty of null")
    private String password;

    @NotBlank(message =  "The branch can't be empty of null")
    private String branch;

    @NotBlank(message =  "The role can't be empty of null")
    private String role;

}
