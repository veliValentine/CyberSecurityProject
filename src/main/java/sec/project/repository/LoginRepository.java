package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

}
