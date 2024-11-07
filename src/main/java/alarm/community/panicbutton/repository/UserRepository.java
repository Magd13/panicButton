package alarm.community.panicbutton.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alarm.community.panicbutton.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCedula(String cedula);
}
