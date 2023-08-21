package org.brilloconnetz.pancake_shop.service;

import org.brilloconnetz.pancake_shop.model.PancakeShop;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ConcurrentPancakeShopRunner {
    private final PancakeShop pancakeShop;
    private PancakeShopService pancakeShopService;

    // Constructor with an option to inject a PancakeShopService
    public ConcurrentPancakeShopRunner(PancakeShop pancakeShop, PancakeShopService pancakeShopService) {
        this.pancakeShop = pancakeShop;
        this.pancakeShopService = pancakeShopService;
    }

    // Method to run the concurrent scenario
    public void runConcurrentScenario() {
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = startTime.plusSeconds(30);

        System.out.println("Starting time of the 30 seconds slot: " + startTime);
        System.out.println("Ending time of the 30 seconds slot: " + endTime);

        // CompletableFuture to asynchronously make pancakes in the shop
        CompletableFuture<Integer> shopFuture = CompletableFuture.supplyAsync(pancakeShop::makePancakes);

        List<CompletableFuture<Integer>> userFutures = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            // CompletableFuture to asynchronously generate random user demands
            userFutures.add(CompletableFuture.supplyAsync(pancakeShop::generateRandomUserDemand));
        }

        // CompletableFuture to wait for all user demands to be fulfilled
        CompletableFuture<Void> allUserDemandsFulfilled = CompletableFuture.allOf(userFutures.toArray(new CompletableFuture[0]));

        // Combine all CompletableFuture results and process the slot
        CompletableFuture<Void> combinedFuture = allUserDemandsFulfilled.thenCombine(shopFuture, (ignored, shopPancakes) -> {
            List<Integer> userDemands = new ArrayList<>();
            for (CompletableFuture<Integer> userFuture : userFutures) {
                try {
                    userDemands.add(userFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            // Call the service to process the slot concurrently
            pancakeShopService.processSlotConcurrently(shopPancakes, userDemands);
            return null;
        });

        try {
            // Wait for all CompletableFuture operations to complete
            combinedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}