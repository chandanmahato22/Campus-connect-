package in.chandan.CampusConnect.mappers;

import in.chandan.CampusConnect.dto.UserRespDto;
import in.chandan.CampusConnect.entity.Users;

public class UserDto {

    public UserRespDto userDtoMapper(Users user){

        UserRespDto resp = new UserRespDto();

        resp.setUid(user.getUid());
        resp.setBranch(user.getBranch());
        resp.setName(user.getName());

        return resp;
    }
}
