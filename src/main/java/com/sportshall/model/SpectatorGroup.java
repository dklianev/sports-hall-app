package com.sportshall.model;

// Model for a group of spectators
public class SpectatorGroup {
    private final int groupId;
    private final int spectatorCount;
    private final SeatCategory preferredCategory;

    public SpectatorGroup(int groupId, int spectatorCount, SeatCategory preferredCategory) {
        this.groupId = groupId;
        this.spectatorCount = spectatorCount;
        this.preferredCategory = preferredCategory;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getSpectatorCount() {
        return spectatorCount;
    }

    public SeatCategory getPreferredCategory() {
        return preferredCategory;
    }

    @Override
    public String toString() {
        return "Group " + groupId + " (" + spectatorCount + " spectators, category: " + preferredCategory.getName() + ")";
    }
}
