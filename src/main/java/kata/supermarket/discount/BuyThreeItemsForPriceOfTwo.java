package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;

public class BuyThreeItemsForPriceOfTwo implements Discount {

    private Item item1, item2, item3;

    public BuyThreeItemsForPriceOfTwo(Item item1, Item item2, Item item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    @Override
    public BigDecimal apply() {
        return null;//TODO
    }
}
