package To_do_Fullstack.TaiNgonheng.controller;

import To_do_Fullstack.TaiNgonheng.dto.LoginDto;
import To_do_Fullstack.TaiNgonheng.dto.RegisterDto;
import To_do_Fullstack.TaiNgonheng.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    //Build register Rest api
    //http://localhost:8080/api/auth/register
    //follow RegisterDto
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    // login to user account
    // http://localhost:8080/api/auth/login
    // fill by LoginDto
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
