package in.chandan.CampusConnect.controller;


import in.chandan.CampusConnect.dto.RegistrationReqDto;
import in.chandan.CampusConnect.repository.RegistrationRepository;
import in.chandan.CampusConnect.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    RegistrationService registrationService;
    RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationService registrationService, RegistrationRepository registrationRepository){
        this.registrationService = registrationService;
        this.registrationRepository = registrationRepository;
    }

    @PostMapping("/registerEvent")
    public ResponseEntity<String> registerForEvent(@RequestBody RegistrationReqDto regReq){

        String msg = registrationService.register(regReq);

        return ResponseEntity.ok(msg);
    }

}
