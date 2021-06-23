package us.odm.distributedlocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import javax.sql.DataSource;

@SpringBootApplication
public class Lox {

	public static void main(String[] args) {
		SpringApplication.run(Lox.class, args);
	}

	@Bean
	public LockRepository lockRepository(DataSource dataSource) {
		return new DefaultLockRepository(dataSource);
	}

	@Bean
	public JdbcLockRegistry lockRegistry(LockRepository lockRepository) {
		return new JdbcLockRegistry(lockRepository);
	}

}
