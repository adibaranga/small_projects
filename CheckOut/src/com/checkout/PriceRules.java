package com.checkout;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: Adi Baranga
 * Date: 2/1/14
 * Time: 2:12 PM
 *
 */
public interface PriceRules {

    /**
     * Return the map with items and prices (per unit or per best offers)
     *
     * @return map
     */
    public Map<String, TreeMap<Integer, Integer>> getItemPricesMap();
}
