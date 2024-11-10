package To_do_Fullstack.TaiNgonheng.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RegisterDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
