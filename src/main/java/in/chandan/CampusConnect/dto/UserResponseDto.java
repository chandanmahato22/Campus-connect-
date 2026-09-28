package in.chandan.CampusConnect.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private String name;

    private String msg = "user successfully created !";
}
