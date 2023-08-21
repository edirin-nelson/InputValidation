package org.brilloconnetz.pancake_shop.model;

import java.util.List;
import java.util.Random;

public class PancakeShop {
    private static final int MAX_PANCAKES_SHOPKEEPER = 12;
    private static final int MAX_PANCAKES_USER = 5;
    private static final Random random = new Random();

    public int makePancakes() {
        return random.nextInt(MAX_PANCAKES_SHOPKEEPER + 1);
    }

    public int generateRandomUserDemand() {
        return random.nextInt(MAX_PANCAKES_USER + 1);
    }

    public boolean canMeetUserDemands(List<Integer> userDemands) {
        int totalUserDemand = userDemands.stream().mapToInt(Integer::intValue).sum();
        return totalUserDemand <= MAX_PANCAKES_SHOPKEEPER;
    }
}