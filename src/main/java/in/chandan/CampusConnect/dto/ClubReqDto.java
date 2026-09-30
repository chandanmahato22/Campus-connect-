package in.chandan.CampusConnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClubReqDto {

    @NotBlank(message =  "The name can't be empty of null")
    private String name;

    @NotBlank(message =  "The genre can't be empty of null")
    private String genre;

    @NotNull(message = "The udi of user must be present and must not be null")
    private Long user_id;
}
