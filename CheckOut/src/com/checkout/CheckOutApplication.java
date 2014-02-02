package com.checkout;

/**
 * Created with IntelliJ IDEA.
 * User: Adi Baranga
 * Date: 2/1/14
 * Time: 2:57 PM
 * <p/>
 * Test class
 */
public class CheckOutApplication {

    static {
        boolean assertsEnabled = false;
        assert assertsEnabled = true; // Intentional side effect!
        if (!assertsEnabled) {
            throw new RuntimeException("Asserts must be enabled for CheckOutApplication!!!");
        }
    }

    public static void main(String[] args) {
        testTotals();
        testIncremental();
    }

    private static void testTotals() {
        assert (0 == price("")) : "Oops!Price not properly computed!!!";
        assert (50 == price("A")) : "Oops!Price not properly computed!!!";
        assert (80 == price("AB")) : "Oops!Price not properly computed!!!";
        assert (115 == price("CDBA")) : "Oops!Price not properly computed!!!";

        assert (100 == price("AA")) : "Oops!Price not properly computed!!!";
        assert (130 == price("AAA")) : "Oops!Price not properly computed!!!";
        assert (180 == price("AAAA")) : "Oops!Price not properly computed!!!";
        assert (230 == price("AAAAA")) : "Oops!Price not properly computed!!!";
        assert (260 == price("AAAAAA")) : "Oops!Price not properly computed!!!";

        assert (160 == price("AAAB")) : "Oops!Price not properly computed!!!";
        assert (175 == price("AAABB")) : "Oops!Price not properly computed!!!";
        assert (190 == price("AAABBD")) : "Oops!Price not properly computed!!!";
        assert (190 == price("DABABA")) : "Oops!Price not properly computed!!!";

        //extra test
        assert (425 == price("EAEAEAEBEB")) : "Oops!Price not properly computed!!!";
        System.out.println("CheckOutApplication.testTotals() passed!");
    }

    private static void testIncremental() {
        CheckOut checkOut = createCheckOut();

        assert (0 == checkOut.getTotalPrice()) : "Oops!Price not properly computed!!!";
        checkOut.scan("A");
        assert (50 == checkOut.getTotalPrice()) : "Oops!Price not properly computed!!!";
        checkOut.scan("B");
        assert (80 == checkOut.getTotalPrice()) : "Oops!Price not properly computed!!!";
        checkOut.scan("A");
        assert (130 == checkOut.getTotalPrice()) : "Oops!Price not properly computed!!!";
        checkOut.scan("A");
        assert (160 == checkOut.getTotalPrice()) : "Oops!Price not properly computed!!!";
        checkOut.scan("B");
        assert (175 == checkOut.getTotalPrice()) : "Oops!Price not properly computed!!!";
        System.out.println("CheckOutApplication.testIncremental() passed!");
    }

    private static int price(String goods) {
        if (goods == null) throw new IllegalArgumentException();

        CheckOut checkOut = createCheckOut();

        for (int i = 0; i < goods.length(); i++) {
            checkOut.scan(goods.substring(i, i + 1));
        }

        return checkOut.getTotalPrice();
    }

    private static CheckOut createCheckOut() {
        PriceRules priceRules = CVSPriceRules.getInstance();
        return new CheckOut(new CheckOutPricer(priceRules));
    }


}
