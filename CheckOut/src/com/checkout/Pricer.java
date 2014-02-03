package com.checkout;

/**
 * Created with IntelliJ IDEA.
 * User: Adi Baranga
 * Date: 2/1/14
 * Time: 7:10 PM
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

    /**
     * Check if this item belongs to the store
     * @param item
     * @return  true if item belong to the store, false otherwise
     */
    public boolean isValidItem(String item);
}
