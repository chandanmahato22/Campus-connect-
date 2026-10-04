package in.chandan.CampusConnect.service;

import in.chandan.CampusConnect.dto.UserLoginDto;
import in.chandan.CampusConnect.dto.UserRegistrationDto;
import in.chandan.CampusConnect.dto.UserRespDto;
import in.chandan.CampusConnect.dto.UserResponseDto;
import in.chandan.CampusConnect.entity.Users;
import in.chandan.CampusConnect.enums.Role;
import in.chandan.CampusConnect.exceptions.ResourceNotFoundException;
import in.chandan.CampusConnect.exceptions.TooManyRequestException;
import in.chandan.CampusConnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RateLimitService rateLimitService;
    public UserService(UserRepository userRepository,
                       RateLimitService rateLimitService,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.rateLimitService = rateLimitService;
    }

    @Transactional
    public UserResponseDto registerUser(UserRegistrationDto userReq){

        Users user = new Users();

        user.setUid(userReq.getUid());
        user.setName(userReq.getName());

        String rawPass = userReq.getPassword();
        String hassPass = passwordEncoder.encode(rawPass);

        user.setPassword(hassPass);
        user.setBranch(userReq.getBranch());
        user.setRole(Role.valueOf(userReq.getRole()));
        user.setCreatedAt(LocalDateTime.now());

        Users resp = userRepository.save(user);

        UserResponseDto respDto = new UserResponseDto();
        respDto.setName(resp.getName());
        return respDto;
    }

    public UserRespDto getUser(Long id){
        Users user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user not found with uid : " + id)
        );

        UserRespDto resp = new UserRespDto();

        resp.setBranch(user.getBranch());
        resp.setName(user.getName());
        resp.setUid(user.getUid());

        return resp;
    }

    public String verifyUser(UserLoginDto req,String ip) {

        Long uid = req.getUid();

        if(rateLimitService.isBlocked(uid,ip)){
            throw new TooManyRequestException("Too many failed login attempts." +
                    "Try again later");
        }
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    req.getUid(),
                                    req.getPassword()
                            )
                    );
            rateLimitService.resetAttempts(uid, ip);

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(req);
            }
        }
        catch(AuthenticationException ex) {
            rateLimitService.recordFailedAttempt(uid, ip);
            throw ex;
        }
        return "";
    }
}
