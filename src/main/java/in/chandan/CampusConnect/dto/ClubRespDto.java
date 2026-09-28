package in.chandan.CampusConnect.dto;

import in.chandan.CampusConnect.entity.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubRespDto {

    private String name;

    private String genre;

    private UserRespDto userRespDto;
}
