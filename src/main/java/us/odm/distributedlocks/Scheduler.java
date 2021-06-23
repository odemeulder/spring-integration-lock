package us.odm.distributedlocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.leader.LockRegistryLeaderInitiator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    LockRegistryLeaderInitiator leaderInitiator;

    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTask1(){
        System.out.println("Executing task 1");
    }

    @Scheduled(cron = "5 * * * * ?")
    public void scheduleTask2(){
        // This will only run if this instance is "the leader"
        if (!isLeader()) return;

        System.out.println("Executing task 2");
    }

    private boolean isLeader() {
        return leaderInitiator.getContext().isLeader();
    }

}
