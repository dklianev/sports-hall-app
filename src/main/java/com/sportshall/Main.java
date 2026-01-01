package com.sportshall;

import com.sportshall.model.SeatCategory;
import com.sportshall.model.SpectatorGroup;
import com.sportshall.task.EntranceThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Main class for the sports hall spectator seating application
public class Main {
    
    public static void main(String[] args) {
        System.out.println("SPORTS HALL SPECTATOR SEATING SYSTEM");
        System.out.println();
        
        // Create the sports hall
        SportsHall sportsHall = new SportsHall("Arena Armeec");
        
        // Show initial state
        System.out.println("Initial hall state:");
        sportsHall.printStatistics();
        System.out.println();
        
        // Generate spectator groups (12 groups)
        List<SpectatorGroup> allGroups = new ArrayList<>();
        Random random = new Random();
        SeatCategory[] categories = SeatCategory.values();
        
        for (int i = 1; i <= 12; i++) {
            int spectatorCount = 5 + random.nextInt(26);
            SeatCategory category = categories[random.nextInt(categories.length)];
            allGroups.add(new SpectatorGroup(i, spectatorCount, category));
        }
        
        System.out.println("Generated 12 spectator groups:");
        for (SpectatorGroup g : allGroups) {
            System.out.println("  - " + g);
        }
        System.out.println();
        
        // Distribute groups to 4 entrances
        List<SpectatorGroup> groups1 = new ArrayList<>();
        List<SpectatorGroup> groups2 = new ArrayList<>();
        List<SpectatorGroup> groups3 = new ArrayList<>();
        List<SpectatorGroup> groups4 = new ArrayList<>();
        
        for (int i = 0; i < allGroups.size(); i++) {
            if (i % 4 == 0) groups1.add(allGroups.get(i));
            else if (i % 4 == 1) groups2.add(allGroups.get(i));
            else if (i % 4 == 2) groups3.add(allGroups.get(i));
            else groups4.add(allGroups.get(i));
        }
        
        System.out.println("STARTING SPECTATOR ADMISSION...");
        System.out.println();
        
        // Create entrance threads
        EntranceThread entrance1 = new EntranceThread(sportsHall, groups1, 1);
        EntranceThread entrance2 = new EntranceThread(sportsHall, groups2, 2);
        EntranceThread entrance3 = new EntranceThread(sportsHall, groups3, 3);
        EntranceThread entrance4 = new EntranceThread(sportsHall, groups4, 4);
        
        // Start all threads
        entrance1.start();
        entrance2.start();
        entrance3.start();
        entrance4.start();
        
        // Wait for all threads to complete
        try {
            entrance1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            entrance2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            entrance3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            entrance4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        // Calculate total seated
        int totalSeated = entrance1.getSeatedCount() + entrance2.getSeatedCount() + 
                          entrance3.getSeatedCount() + entrance4.getSeatedCount();
        
        // Show final results
        System.out.println();
        System.out.println("ADMISSION COMPLETED!");
        System.out.println("Total spectators seated: " + totalSeated);
        
        // Show final hall state
        sportsHall.printStatistics();
    }
}
