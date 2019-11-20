package kata.supermarket.discount;

import kata.supermarket.Item;

import java.math.BigDecimal;

public class BuyOneGetOneFree implements Discount {

    private final Item item;

    public BuyOneGetOneFree(Item item) {
        this.item = item;
    }

    @Override
    public BigDecimal apply() {
        return item.price();
    }
}
