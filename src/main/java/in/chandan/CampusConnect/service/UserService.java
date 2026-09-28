package in.chandan.CampusConnect.service;

import in.chandan.CampusConnect.dto.UserRegistrationDto;
import in.chandan.CampusConnect.dto.UserResponseDto;
import in.chandan.CampusConnect.entity.Club;
import in.chandan.CampusConnect.entity.Users;
import in.chandan.CampusConnect.repository.ClubRepository;
import in.chandan.CampusConnect.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ClubRepository clubRepository;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,ClubRepository clubRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.clubRepository = clubRepository;
    }


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
}
