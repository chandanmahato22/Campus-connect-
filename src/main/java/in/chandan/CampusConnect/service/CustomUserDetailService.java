package in.chandan.CampusConnect.service;

import in.chandan.CampusConnect.entity.PrincipalUser;
import in.chandan.CampusConnect.entity.Users;
import in.chandan.CampusConnect.exceptions.ResourceNotFoundException;
import in.chandan.CampusConnect.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findById(Long.parseLong(username)).orElseThrow(()
        -> new ResourceNotFoundException("User not found with uid :" + username));

        return new PrincipalUser(user);
    }
}

