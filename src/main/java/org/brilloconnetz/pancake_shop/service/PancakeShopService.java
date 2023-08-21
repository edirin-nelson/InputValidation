package org.brilloconnetz.pancake_shop.service;

import org.brilloconnetz.pancake_shop.model.PancakeShop;
import org.brilloconnetz.pancake_shop.model.Slot;
import org.brilloconnetz.pancake_shop.model.User;

import java.util.List;

public class PancakeShopService {
    public void processSlotNonConcurrently(PancakeShop pancakeShop, Slot slot, List<User> users) {
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

    public void processSlotConcurrently(int pancakesMade, List<Integer> userDemands) {
        int totalUserDemands = userDemands.stream().mapToInt(Integer::intValue).sum();

        System.out.println("Pancakes made: " + pancakesMade);
        System.out.println("Total user demands: " + totalUserDemands);

        if (pancakesMade >= totalUserDemands) {
            System.out.println("Shopkeeper meets user demands");
        } else {
            System.out.println("Shopkeeper can't meet all user demands");
        }

        int wastedPancakes = Math.max(0, pancakesMade - totalUserDemands);
        System.out.println("Wasted pancakes: " + wastedPancakes);

        if (wastedPancakes > 0) {
            int unmetOrders = Math.max(0, userDemands.size() - (pancakesMade - wastedPancakes));
            System.out.println("Unmet pancake orders: " + unmetOrders);
        }
    }
}