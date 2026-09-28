package in.chandan.CampusConnect.dto;


import in.chandan.CampusConnect.entity.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationReqDto {

    private String event_name;

    private long uid;
}
