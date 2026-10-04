package in.chandan.CampusConnect.controller;

import in.chandan.CampusConnect.dto.ClubReqDto;
import in.chandan.CampusConnect.dto.ClubRespDto;
import in.chandan.CampusConnect.entity.Club;
import in.chandan.CampusConnect.repository.ClubRepository;
import in.chandan.CampusConnect.service.ClubService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private ClubService clubService;
    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }

    //creating club
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createClub( @Valid  @RequestBody ClubReqDto clubReq){

        String msg = clubService.createClub(clubReq);
        return ResponseEntity.ok(msg);
    }

    //printing all clubs
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClubRespDto>> getClubs(){
        List<ClubRespDto> clubs = clubService.getClubs();
        return ResponseEntity.ok(clubs);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ClubRespDto> getClub(@Valid @PathVariable long id){
        ClubRespDto resp = clubService.getCLub(id);
        return ResponseEntity.ok(resp);
    }

    //deleting a club
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClub(@Valid @PathVariable long id){
        clubService.deleteClub(id);
        return ResponseEntity.ok("club deleted successfully");
    }

    //update
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ClubRespDto>  updateClub(@Valid @PathVariable long id,
                          @RequestBody ClubReqDto req){
        ClubRespDto resp = clubService.updateClub(id,req);
        return ResponseEntity.ok(resp);
    }

}
