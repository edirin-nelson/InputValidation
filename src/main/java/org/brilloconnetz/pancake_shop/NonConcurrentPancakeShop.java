package org.brilloconnetz.pancake_shop;

import org.brilloconnetz.pancake_shop.model.PancakeShop;
import org.brilloconnetz.pancake_shop.model.Slot;
import org.brilloconnetz.pancake_shop.model.User;
import org.brilloconnetz.pancake_shop.service.PancakeShopService;

import java.util.Arrays;

public class NonConcurrentPancakeShop {
    public static void main(String[] args) {
        PancakeShop pancakeShop = new PancakeShop();

        PancakeShopService pancakeShopService = new PancakeShopService();

        // Define the time slots
        Slot slot1 = new Slot("09:00:00", "09:30:00");
        Slot slot2 = new Slot("09:30:00", "10:00:00");

        // Define the users
        User user1 = new User("User 1");
        User user2 = new User("User 2");
        User user3 = new User("User 3");

        // Assign random user demands for each slot
        slot1.addUserDemand(pancakeShop.generateRandomUserDemand());
        slot1.addUserDemand(pancakeShop.generateRandomUserDemand());
        slot1.addUserDemand(pancakeShop.generateRandomUserDemand());

        slot2.addUserDemand(pancakeShop.generateRandomUserDemand());
        slot2.addUserDemand(pancakeShop.generateRandomUserDemand());
        slot2.addUserDemand(pancakeShop.generateRandomUserDemand());

        // Simulate the pancake shop process for each slot
        pancakeShopService.processSlotNonConcurrently(pancakeShop, slot1, Arrays.asList(user1, user2, user3));
        pancakeShopService.processSlotNonConcurrently(pancakeShop, slot2, Arrays.asList(user1, user2, user3));
    }
}
