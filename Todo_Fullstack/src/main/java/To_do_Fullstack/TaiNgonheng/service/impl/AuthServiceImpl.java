package To_do_Fullstack.TaiNgonheng.service.impl;

import To_do_Fullstack.TaiNgonheng.dto.JwtAuthResponse;
import To_do_Fullstack.TaiNgonheng.dto.LoginDto;
import To_do_Fullstack.TaiNgonheng.dto.RegisterDto;
import To_do_Fullstack.TaiNgonheng.entity.Role;
import To_do_Fullstack.TaiNgonheng.entity.User;
import To_do_Fullstack.TaiNgonheng.exception.TodoAPIException;
import To_do_Fullstack.TaiNgonheng.repository.RoleRepository;
import To_do_Fullstack.TaiNgonheng.repository.UserRepository;
import To_do_Fullstack.TaiNgonheng.security.JwtTokenProvider;
import To_do_Fullstack.TaiNgonheng.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {
        // Check username in database
        if (userRepository.existsByUsername(registerDto.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }
        //check email in databases
        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST,"Email Already exists!");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User register successfully.";
    }
    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
       Optional<User> userOptional= userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),loginDto.getUsernameOrEmail());
        String role = null;
       if (userOptional.isPresent()){
           User loggedInUser = userOptional.get();
           Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();
           if (optionalRole.isPresent()){
               Role userRole = optionalRole.get();
               role = userRole.getName();
           }
       }
       JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
       jwtAuthResponse.setRole(role);
       jwtAuthResponse.setAccessToken(token);
        return jwtAuthResponse;
    }
}

