package org.brilloconnetz.pancake_shop;

import org.brilloconnetz.pancake_shop.model.PancakeShop;
import org.brilloconnetz.pancake_shop.service.ConcurrentPancakeShopRunner;
import org.brilloconnetz.pancake_shop.service.PancakeShopService;

public class ConcurrentPancakeShopApp {
    public static void main(String[] args) {
        PancakeShop pancakeShop = new PancakeShop();
        PancakeShopService pancakeShopService = new PancakeShopService(); // Create the service instance
        ConcurrentPancakeShopRunner runner = new ConcurrentPancakeShopRunner(pancakeShop, pancakeShopService);
        runner.runConcurrentScenario();
    }
}