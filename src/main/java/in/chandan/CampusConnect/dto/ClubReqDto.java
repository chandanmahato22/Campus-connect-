package in.chandan.CampusConnect.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClubReqDto {
    private String name;

    private String genre;

    private int user_id;
}
