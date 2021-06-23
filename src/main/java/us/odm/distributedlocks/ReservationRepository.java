package us.odm.distributedlocks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
