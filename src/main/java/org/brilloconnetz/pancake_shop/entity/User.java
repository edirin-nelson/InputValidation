package org.brilloconnetz.pancake_shop.entity;

public class User {
    private final String name;
    private final int maxPancakesToEat = 5;
    private int pancakesEaten = 0;

    public User(String name) {
        this.name = name;
    }

    public void eatPancakes(int pancakeCount) {
        pancakesEaten += pancakeCount; // Increment the count of pancakes eaten by the user
    }

    public int getMaxPancakesToEat() {
        return maxPancakesToEat;
    }

    public String getName() {
        return name;
    }
}