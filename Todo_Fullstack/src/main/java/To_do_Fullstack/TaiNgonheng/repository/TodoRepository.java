package To_do_Fullstack.TaiNgonheng.repository;

import To_do_Fullstack.TaiNgonheng.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
