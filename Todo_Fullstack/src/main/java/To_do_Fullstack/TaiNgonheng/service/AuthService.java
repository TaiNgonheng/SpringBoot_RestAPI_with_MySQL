package To_do_Fullstack.TaiNgonheng.service;

import To_do_Fullstack.TaiNgonheng.dto.LoginDto;
import To_do_Fullstack.TaiNgonheng.dto.RegisterDto;

public interface AuthService{
    String register(RegisterDto registerDto);


    String login(LoginDto loginDto);
}
