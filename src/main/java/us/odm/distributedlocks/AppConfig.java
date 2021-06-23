package us.odm.distributedlocks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;
import org.springframework.integration.support.leader.LockRegistryLeaderInitiator;
import org.springframework.integration.support.locks.LockRegistry;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public LockRepository lockRepository(DataSource dataSource) {
        return new DefaultLockRepository(dataSource);
    }

    @Bean
    public JdbcLockRegistry lockRegistry(LockRepository lockRepository) {
        return new JdbcLockRegistry(lockRepository);
    }

    @Bean
    public LockRegistryLeaderInitiator lockRegistryLeaderInitiator(LockRegistry locks) {
        return new LockRegistryLeaderInitiator(locks);
    }

}
