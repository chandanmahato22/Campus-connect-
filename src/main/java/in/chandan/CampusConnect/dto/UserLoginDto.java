package in.chandan.CampusConnect.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {

    @NotNull(message = "Uid must not be null or empty")
    private Long uid;

    @NotEmpty(message = "Password must not be null or empty")
    String password;
}
