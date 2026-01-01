package com.sportshall;

import com.sportshall.model.SeatCategory;
import com.sportshall.model.SpectatorGroup;

// Class representing the sports hall with seats by category
public class SportsHall {
    private String name;
    private int vipSeats;
    private int standardSeats;
    private int economySeats;
    private int standingSeats;

    public SportsHall(String name) {
        this.name = name;
        this.vipSeats = 50;
        this.standardSeats = 200;
        this.economySeats = 300;
        this.standingSeats = 150;
    }

    // Attempts to seat a group of spectators in their preferred category
    synchronized public boolean seatGroup(SpectatorGroup group) {
        SeatCategory category = group.getPreferredCategory();
        int requested = group.getSpectatorCount();
        
        int available = getAvailableSeats(category);
        
        if (available >= requested) {
            setSeats(category, available - requested);
            System.out.println("[" + Thread.currentThread().getName() + "] " + 
                group + " -> Seated successfully! Available: " + getAvailableSeats(category));
            return true;
        } else {
            System.out.println("[" + Thread.currentThread().getName() + "] " + 
                group + " -> FAILED! Not enough seats (available: " + available + ", requested: " + requested + ")");
            return false;
        }
    }
    
    private int getAvailableSeats(SeatCategory category) {
        switch (category) {
            case VIP: return vipSeats;
            case STANDARD: return standardSeats;
            case ECONOMY: return economySeats;
            case STANDING: return standingSeats;
            default: return 0;
        }
    }
    
    private void setSeats(SeatCategory category, int value) {
        switch (category) {
            case VIP: vipSeats = value; break;
            case STANDARD: standardSeats = value; break;
            case ECONOMY: economySeats = value; break;
            case STANDING: standingSeats = value; break;
        }
    }

    // Prints statistics for the hall
    synchronized public void printStatistics() {
        System.out.println("------------------------------------------------------------");
        System.out.println("SPORTS HALL: " + name);
        System.out.println("------------------------------------------------------------");
        System.out.println("VIP: " + (50 - vipSeats) + "/50 occupied");
        System.out.println("Standard: " + (200 - standardSeats) + "/200 occupied");
        System.out.println("Economy: " + (300 - economySeats) + "/300 occupied");
        System.out.println("Standing: " + (150 - standingSeats) + "/150 occupied");
        int totalOccupied = (50 - vipSeats) + (200 - standardSeats) + (300 - economySeats) + (150 - standingSeats);
        System.out.println("TOTAL: " + totalOccupied + "/700 occupied");
        System.out.println("------------------------------------------------------------");
    }

    public String getName() {
        return name;
    }
}
