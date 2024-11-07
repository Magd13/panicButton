package alarm.community.panicbutton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alarm.community.panicbutton.model.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    
}