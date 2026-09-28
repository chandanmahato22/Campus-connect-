package in.chandan.CampusConnect.service;


import in.chandan.CampusConnect.controller.ClubController;
import in.chandan.CampusConnect.dto.ClubReqDto;
import in.chandan.CampusConnect.entity.Club;
import in.chandan.CampusConnect.entity.Users;
import in.chandan.CampusConnect.repository.ClubRepository;
import in.chandan.CampusConnect.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ClubService {

    private ClubRepository clubRepository;
    private UserRepository userRepository;

    public ClubService(ClubRepository clubRepository,UserRepository userRepository){
        this.clubRepository = clubRepository;
        this.userRepository= userRepository;
    }

    public String createClub(ClubReqDto clubReq){
        System.out.println("entered clubService");
       Club club = new Club();

       club.setName(clubReq.getName());
       club.setGenre(clubReq.getGenre());

       Users user = userRepository.findById((long)clubReq.getUser_id()).orElseThrow(
               () -> new RuntimeException("User not found")
       );

       club.setUser(user);

       return "Club has been created wth name " + club.getName();
    }


}
