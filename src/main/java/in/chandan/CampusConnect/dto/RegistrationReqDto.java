package in.chandan.CampusConnect.dto;


import in.chandan.CampusConnect.entity.Event;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationReqDto {

    @NotBlank(message = "The name of the event must not be null or empty")
    private String event_name;

}
