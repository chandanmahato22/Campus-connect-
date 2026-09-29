package in.chandan.CampusConnect.service;

import in.chandan.CampusConnect.dto.UserLoginDto;
import in.chandan.CampusConnect.dto.UserRegistrationDto;
import in.chandan.CampusConnect.dto.UserRespDto;
import in.chandan.CampusConnect.dto.UserResponseDto;
import in.chandan.CampusConnect.entity.Club;
import in.chandan.CampusConnect.entity.Users;
import in.chandan.CampusConnect.repository.ClubRepository;
import in.chandan.CampusConnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {


    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ClubRepository clubRepository;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,
                       ClubRepository clubRepository,AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.clubRepository = clubRepository;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public UserResponseDto registerUser(UserRegistrationDto userReq){
        System.out.println("ENTERED auth service ");
        Users user = new Users();

        user.setUid(userReq.getUid());
        user.setName(userReq.getName());
        String rawPass = userReq.getPassword();
        String hassPass = passwordEncoder.encode(rawPass);
        user.setPassword(hassPass);
        user.setBranch(userReq.getBranch());

        Club club = clubRepository.findByName(userReq.getClub_name()).orElse(null);
        user.setClub(club);
        user.setCreatedAt(LocalDateTime.now());
        Users resp = userRepository.save(user);

        UserResponseDto respDto = new UserResponseDto();
        respDto.setName(resp.getName());
        return respDto;
    }

    public UserRespDto getUser(Long id){
        Users user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("user not found")
        );

        UserRespDto resp = new UserRespDto();

        resp.setBranch(user.getBranch());
        resp.setName(user.getName());
        resp.setUid(user.getUid());

        return resp;
    }

    public boolean verifyUser(UserLoginDto req){
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(req.getUid(),req.getPassword()));
        return authentication.isAuthenticated();
    }
}
