package in.chandan.CampusConnect.controller;

import in.chandan.CampusConnect.dto.ClubReqDto;
import in.chandan.CampusConnect.entity.Club;
import in.chandan.CampusConnect.repository.ClubRepository;
import in.chandan.CampusConnect.service.ClubService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ClubController {

    private ClubService clubService;

    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }
    @PostMapping("/createClub")
    public ResponseEntity<String> createClub(@RequestBody ClubReqDto clubReq){

        String msg = clubService.createClub(clubReq);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/clubs")
    public ResponseEntity<Set<Club>> getClubs(){
        Set<Club> clubs = clubService.getClubs();
        return ResponseEntity.ok(clubs);
    }

}
