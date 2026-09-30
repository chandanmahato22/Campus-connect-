package in.chandan.CampusConnect.controller;

import in.chandan.CampusConnect.dto.UserRespDto;
import in.chandan.CampusConnect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserRespDto> getUser(@Valid  @PathVariable Long id){
        UserRespDto resp = userService.getUser(id);
        return ResponseEntity.ok(resp);
    }
    //update

    //registrations
}
