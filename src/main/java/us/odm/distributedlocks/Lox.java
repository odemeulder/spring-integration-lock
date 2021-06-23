package us.odm.distributedlocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.leader.Candidate;
import org.springframework.integration.leader.DefaultCandidate;
import org.springframework.integration.leader.event.DefaultLeaderEventPublisher;
import org.springframework.integration.support.leader.LockRegistryLeaderInitiator;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@SpringBootApplication
@EnableScheduling
public class Lox {

	public static void main(String[] args) {
		SpringApplication.run(Lox.class, args);
	}
}
