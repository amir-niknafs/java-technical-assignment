package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;

public class BuyTwoItemsForFixedPrice implements Discount {

    private final Item item1, item2;
    private final BigDecimal fixedPrice;

    public BuyTwoItemsForFixedPrice(Item item1, Item item2, BigDecimal fixedPrice) {
        this.item1 = item1;
        this.item2 = item2;
        this.fixedPrice = fixedPrice;
    }

    @Override
    public BigDecimal apply() {
        return null;//TODO
    }
}
