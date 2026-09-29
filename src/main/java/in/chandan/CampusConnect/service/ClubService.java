package in.chandan.CampusConnect.service;


import in.chandan.CampusConnect.controller.ClubController;
import in.chandan.CampusConnect.dto.ClubReqDto;
import in.chandan.CampusConnect.dto.ClubRespDto;
import in.chandan.CampusConnect.dto.UserRespDto;
import in.chandan.CampusConnect.entity.Club;
import in.chandan.CampusConnect.entity.Users;
import in.chandan.CampusConnect.mappers.ClubDto;
import in.chandan.CampusConnect.mappers.UserDto;
import in.chandan.CampusConnect.repository.ClubRepository;
import in.chandan.CampusConnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ClubService {
    private ClubDto clubDto = new ClubDto();
    private ClubRepository clubRepository;
    private UserRepository userRepository;
    private UserDto userDto;

    public ClubService(ClubRepository clubRepository,UserRepository userRepository){
        this.clubRepository = clubRepository;
        this.userRepository= userRepository;
    }

    @Transactional
    public String createClub(ClubReqDto clubReq){
       Club club = new Club();

       club.setName(clubReq.getName());
       club.setGenre(clubReq.getGenre());

       Users user = userRepository.findById((long)clubReq.getUser_id()).orElseThrow(
               () -> new RuntimeException("User not found")
       );

       club.setUser(user);
       Club clubRep = clubRepository.save(club);
//       user.setClub(clubRep);
       return "Club has been created wth name " + club.getName();
    }


    public List<ClubRespDto> getClubs(){
        return clubRepository.findAll().stream().map(clubDto :: clubDtoMapper
        ).toList();
    }

    public ClubRespDto getCLub(long id) {
        Club clubResp = clubRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Club not found"));

        ClubRespDto resp = new ClubRespDto();
        resp.setGenre(clubResp.getGenre());
        resp.setName(clubResp.getName());

        return resp;

    }

    public void deleteClub(long id) {
        Club clubResp = clubRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Club not found"));

        clubRepository.delete(clubResp);
    }

    @Transactional
    public ClubRespDto updateClub(long id,ClubReqDto req) {
        Club clubResp = clubRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Club not found"));

        clubResp.setName(req.getName());
        clubResp.setGenre(clubResp.getGenre());

        Users user = userRepository.findById(req.getUser_id()).orElseThrow(
                () -> new RuntimeException("Club not found"));

        clubResp.setUser(user);

        ClubRespDto clubRespDto = new ClubRespDto();
        clubRespDto.setGenre(req.getGenre());
        clubRespDto.setName(req.getName());

        clubRespDto.setUserRespDto(userDto.userDtoMapper(user));

        return clubRespDto;
    }
}
