package org.brilloconnetz.pancake_shop.entity;

import java.util.List;

public class PancakeShop {
    private int pancakeCount = 0;
    private final int maxPancakesPerSlot = 12;

    public int makePancakes() {
        pancakeCount += maxPancakesPerSlot; // Increment the pancake count by the maximum for the slot
        return maxPancakesPerSlot; // Return the number of pancakes made
    }

    public boolean canMeetUserDemands(List<Integer> userDemands) {
        int totalDemand = userDemands.stream().mapToInt(Integer::intValue).sum(); // Calculate the total user demand
        return pancakeCount >= totalDemand; // Check if available pancakes are sufficient to meet demand
    }
}