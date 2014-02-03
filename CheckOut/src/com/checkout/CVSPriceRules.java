package com.checkout;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: Adi Baranga
 * Date: 2/1/14
 * Time: 7:36 PM
 * <p/>
 * Singleton class for loading the price rules
 */
public class CVSPriceRules implements PriceRules {
    private static CVSPriceRules instance = new CVSPriceRules();

    private static final String CSV_SEPARATOR = ",";
    private Map<String, NavigableMap<Integer, Integer>> itemPricesMap = new HashMap<String, NavigableMap<Integer, Integer>>();

    private CVSPriceRules() {
        loadRules();
    }

    public static CVSPriceRules getInstance() {
        return instance;
    }

    @Override
    public Map<String, NavigableMap<Integer, Integer>> getItemPricesMap() {
        return itemPricesMap;
    }

    /**
     * Load the prices rules from a CVS file
     * Set the map with items and prices (per unit or per best offer)
     */
    private void loadRules() {
        BufferedReader bufferedReader = null;
        try {
            InputStream is = this.getClass().getResourceAsStream("rules.csv");
            bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(CSV_SEPARATOR);
                if (array != null && array.length == 3) {
                    String item = array[0];
                    Integer unit = Integer.parseInt(array[1]);
                    Integer price = Integer.parseInt(array[2]);
                    NavigableMap<Integer, Integer> unitPricesMap = itemPricesMap.get(item);
                    if (unitPricesMap == null) {
                        unitPricesMap = new TreeMap<Integer, Integer>();
                        itemPricesMap.put(item, unitPricesMap);
                    }
                    unitPricesMap.put(unit, price);
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //logging
            ex.printStackTrace();
        } catch (IOException ex) {
            //logging
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    //logging
                    ex.printStackTrace();
                }
            }
        }
    }
}
