package in.chandan.CampusConnect.controller;


import in.chandan.CampusConnect.dto.UserLoginDto;
import in.chandan.CampusConnect.dto.UserRegistrationDto;
import in.chandan.CampusConnect.dto.UserResponseDto;
import in.chandan.CampusConnect.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
        private UserService userService;

        public AuthController(UserService userService){
            this.userService = userService;
        }
   // register
    @PostMapping("/register")
   public  ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegistrationDto userReq){
        System.out.println("ENTERED auth controller ");
        UserResponseDto resp = userService.registerUser(userReq);

        return ResponseEntity.ok(resp);

    }
//    //login
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto){
//
//    }

}
