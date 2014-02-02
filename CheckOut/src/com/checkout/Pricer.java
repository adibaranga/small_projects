package com.checkout;

/**
 * Created with IntelliJ IDEA.
 * User: Adi Baranga
 * Date: 2/1/14
 * Time: 2:10 PM
 */
public interface Pricer {

    /**
     * Compute the price for a number of items of the same type
     *
     * @param item - type of item
     * @param size - number of items
     * @return price of a number of items of the same type
     */
    public int getPrice(String item, int size);
}
