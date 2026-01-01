package com.sportshall.task;

import com.sportshall.SportsHall;
import com.sportshall.model.SpectatorGroup;

import java.util.List;

// Thread task for sports hall entrance - processes groups of spectators
public class EntranceThread extends Thread {
    private final SportsHall sportsHall;
    private final List<SpectatorGroup> groups;
    private final int entranceNumber;
    private int seatedCount = 0;

    public EntranceThread(SportsHall sportsHall, List<SpectatorGroup> groups, int entranceNumber) {
        this.sportsHall = sportsHall;
        this.groups = groups;
        this.entranceNumber = entranceNumber;
    }

    @Override
    public void run() {
        System.out.println("[Entrance " + entranceNumber + "] Opening for " + groups.size() + " groups");
        
        for (SpectatorGroup group : groups) {
            // Simulate time for verification and admission
            try {
                Thread.sleep(100 + (int)(Math.random() * 200));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            
            if (sportsHall.seatGroup(group)) {
                seatedCount += group.getSpectatorCount();
            }
        }
        
        System.out.println("[Entrance " + entranceNumber + "] Closing. Total seated: " + seatedCount + " spectators");
    }

    public int getSeatedCount() {
        return seatedCount;
    }
}
