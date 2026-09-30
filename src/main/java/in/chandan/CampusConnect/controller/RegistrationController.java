package in.chandan.CampusConnect.controller;


import in.chandan.CampusConnect.dto.RegistrationReqDto;
import in.chandan.CampusConnect.repository.RegistrationRepository;
import in.chandan.CampusConnect.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService, RegistrationRepository registrationRepository){
        this.registrationService = registrationService;
    }

    @PostMapping("/registerEvent")
    public ResponseEntity<String> registerForEvent(@Valid  @RequestBody RegistrationReqDto regReq,
                                                   Authentication authentication){

        String msg = registrationService.register(regReq,authentication);

        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/cancelRegistration")
    public ResponseEntity<String> cancelRegistration(Authentication authentication){
        registrationService.deleteRegistration(authentication);
        return ResponseEntity.ok("registration cancelled");
    }


}
