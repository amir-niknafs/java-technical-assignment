package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.Arrays;

public class BuyThreeItemsForPriceOfTwo implements Discount {

    private Item item1, item2, item3;

    public BuyThreeItemsForPriceOfTwo(Item item1, Item item2, Item item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    @Override
    public BigDecimal apply() {
        //Assuming the cheapest Item is free
        Item cheapest = item1.price().compareTo(item2.price()) < 0 ? item1 : item2;
        cheapest = cheapest.price().compareTo(item3.price()) < 0 ? cheapest : item3;

        return cheapest.price();
    }
}
