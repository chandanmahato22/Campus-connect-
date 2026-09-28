package in.chandan.CampusConnect.service;


import in.chandan.CampusConnect.controller.ClubController;
import in.chandan.CampusConnect.dto.ClubReqDto;
import in.chandan.CampusConnect.dto.ClubRespDto;
import in.chandan.CampusConnect.entity.Club;
import in.chandan.CampusConnect.entity.Users;
import in.chandan.CampusConnect.mappers.ClubDto;
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

    public ClubService(ClubRepository clubRepository,UserRepository userRepository){
        this.clubRepository = clubRepository;
        this.userRepository= userRepository;
    }
    @Transactional
    public String createClub(ClubReqDto clubReq){
        System.out.println("entered clubService");
       Club club = new Club();

       club.setName(clubReq.getName());
       club.setGenre(clubReq.getGenre());

       Users user = userRepository.findById((long)clubReq.getUser_id()).orElseThrow(
               () -> new RuntimeException("User not found")
       );

       club.setUser(user);
       Club clubRep = clubRepository.save(club);
       user.setClub(clubRep);
       return "Club has been created wth name " + club.getName();
    }


    public List<ClubRespDto> getClubs(){
        return clubRepository.findAll().stream().map(clubDto :: clubDtoMapper
        ).toList();
    }

}
