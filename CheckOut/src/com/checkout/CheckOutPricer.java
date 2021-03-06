package com.checkout;

import java.util.Map;
import java.util.NavigableMap;

/**
 * Created with IntelliJ IDEA.
 * User: Adi Baranga
 * Date: 2/1/14
 * Time: 7:16 PM
 * <p/>
 * Calculates price for a number of items of the same type
 */

public class CheckOutPricer implements Pricer {

    private PriceRules priceRules;

    public CheckOutPricer(PriceRules priceRules) {
        this.priceRules = priceRules;
    }

    @Override
    public int getPrice(String item, int size) {
        int total = 0;
        if (item != null) {

            while (size > 0) {
                Map.Entry<Integer, Integer> bestOffer = getBestOffer(item, size);
                if (bestOffer == null) {
                    return total;
                }
                int bestOfferNumber = size / bestOffer.getKey();
                total += bestOfferNumber * bestOffer.getValue();
                size -= bestOfferNumber * bestOffer.getKey();
            }
        }

        return total;
    }

    @Override
    public boolean isValidItem(String item) {
        return priceRules.getItemPricesMap().containsKey(item);
    }

    private Map.Entry<Integer, Integer> getBestOffer(String item, int size) {
        NavigableMap<Integer, Integer> unitPricesMap = priceRules.getItemPricesMap().get(item);
        if (unitPricesMap != null) {
            return unitPricesMap.floorEntry(size);
        } else {
            return null;
        }
    }
}
