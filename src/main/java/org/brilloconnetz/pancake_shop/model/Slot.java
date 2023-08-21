package org.brilloconnetz.pancake_shop.model;

import java.util.ArrayList;
import java.util.List;

public class Slot {
    private final String startTime;
    private final String endTime;
    private final List<Integer> userDemands = new ArrayList<>(); // Initialize a list to store user demands

    public Slot(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addUserDemand(int demand) {
        userDemands.add(demand); // Add a user's pancake demand to the list
    }

    public List<Integer> getUserDemands() {
        return userDemands; // Get the list of user demands for the slot
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}