package com.sportshall.model;

// Enum for seat categories in the sports hall
public enum SeatCategory {
    VIP("VIP", 50),
    STANDARD("Standard", 200),
    ECONOMY("Economy", 300),
    STANDING("Standing", 150);

    private final String name;
    private final int maxCapacity;

    SeatCategory(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
