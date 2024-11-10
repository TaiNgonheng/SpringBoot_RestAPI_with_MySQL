package To_do_Fullstack.TaiNgonheng.repository;

import To_do_Fullstack.TaiNgonheng.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
