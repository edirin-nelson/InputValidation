package org.brilloconnetz.pancake_shop;

import org.brilloconnetz.pancake_shop.entity.PancakeShop;
import org.brilloconnetz.pancake_shop.entity.Slot;
import org.brilloconnetz.pancake_shop.entity.User;

import java.util.Arrays;
import java.util.List;

public class NonConcurrentPancakeShop {
    public static void main(String[] args) {
        // Create a PancakeShop instance
        PancakeShop pancakeShop = new PancakeShop();

        // Define the time slots
        Slot slot1 = new Slot("09:00:00", "09:30:00");
        Slot slot2 = new Slot("09:30:00", "10:00:00");

        // Define the users
        User user1 = new User("User 1");
        User user2 = new User("User 2");
        User user3 = new User("User 3");

        // Assign user demands for each slot
        slot1.addUserDemand(3);
        slot1.addUserDemand(4);
        slot1.addUserDemand(2);

        slot2.addUserDemand(1);
        slot2.addUserDemand(5);
        slot2.addUserDemand(2);

        // Simulate the pancake shop process for each slot
        processSlot(pancakeShop, slot1, Arrays.asList(user1, user2, user3));
        processSlot(pancakeShop, slot2, Arrays.asList(user1, user2, user3));
    }

    private static void processSlot(PancakeShop pancakeShop, Slot slot, List<User> users) {
        System.out.println("Slot " + slot.getStartTime() + " - " + slot.getEndTime());

        // Shopkeeper makes pancakes
        int pancakesMade = pancakeShop.makePancakes();
        System.out.println("Pancakes made by Shopkeeper: " + pancakesMade);

        // Get user demands for the slot
        List<Integer> userDemands = slot.getUserDemands();

        // Check if shopkeeper can meet user demands
        if (pancakeShop.canMeetUserDemands(userDemands)) {
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                int demand = userDemands.get(i);

                if (demand > user.getMaxPancakesToEat()) {
                    System.out.println(user.getName() + " cannot eat more than " + user.getMaxPancakesToEat() + " pancakes.");
                } else {
                    user.eatPancakes(demand);
                    System.out.println(user.getName() + " ate " + demand + " pancakes.");
                }
            }

            System.out.println("Shopkeeper met the needs of all users.");
        } else {
            System.out.println("Shopkeeper could not meet the needs of all users.");
        }

        int pancakesWasted = pancakesMade - userDemands.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Pancakes wasted: " + pancakesWasted);
        System.out.println();
    }
}
