package us.odm.distributedlocks;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/*
Controller based on example in youtube video from Josh Long. (https://www.youtube.com/watch?v=firwCHbC7-c)
Spin up the application twice, with different expiration times. One of the calls will be blocked.
 */
@RestController
public class Controller {

    @Autowired
    private JdbcLockRegistry lockRegistry;

    @Autowired
    private ReservationRepository repository;

    @GetMapping("/reservation/{id}/{name}/{time}")
    @SneakyThrows
    Reservation updateReservation(
            @PathVariable Integer id,
            @PathVariable String name,
            @PathVariable long time) {
        var obtained = lockRegistry.obtain(id.toString());
        if (obtained.tryLock()) {
            try {
                Reservation rv = repository.save(new Reservation(id, name));
                Thread.sleep(time);
                return rv;
            } finally {
                obtained.unlock();
            }
        }
        return null;
    }

    @GetMapping("/reservations")
    List<Reservation> getReservations() {
        List<Reservation> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    @GetMapping("/")
    String hello() {
        return "hello world";
    }
}
