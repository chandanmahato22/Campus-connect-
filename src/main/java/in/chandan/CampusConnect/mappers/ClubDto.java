package in.chandan.CampusConnect.mappers;

import in.chandan.CampusConnect.dto.ClubRespDto;
import in.chandan.CampusConnect.entity.Club;
import org.apache.catalina.User;

public class ClubDto {

    UserDto userDto = new UserDto();

    public ClubRespDto clubDtoMapper(Club club) {
        ClubRespDto resp = new ClubRespDto();

        resp.setUserRespDto(userDto.userDtoMapper(club.getUser()));
        resp.setName(club.getName());
        resp.setGenre(club.getGenre());

        return resp;
    }
}
