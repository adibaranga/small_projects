package com.checkout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Adi Baranga
 * Date: 2/1/14
 * Time: 7:04 PM
 * <p/>
 * Class for adding items and return the total price for them.
 * Decoupling the price computation
 */
public class CheckOut {

    private Map<String, Integer> items;
    private Pricer pricer;

    public CheckOut(Pricer pricer) {
        this.pricer = pricer;
    }

    /**
     * Add an item
     *
     * @param item
     * @throws IllegalArgumentException if the argument is an invalid item
     */
    public void scan(String item) {
        if (item == null || !pricer.isValidItem(item)) {
            throw new IllegalArgumentException();
        }
        if (items == null) {
            items = new HashMap<String, Integer>();
        }
        Integer count = items.get(item);
        if (count == null) {
            items.put(item, 1);
        } else {
            items.put(item, ++count);
        }
    }

    /**
     * Compute the total price for all the added items.
     *
     * @return total price
     */

    public int getTotalPrice() {
        int total = 0;
        if (items != null) {
            for (String item : items.keySet()) {
                total += pricer.getPrice(item, items.get(item));
            }
        }
        System.out.println("Total price: " + total);
        return total;
    }
}
