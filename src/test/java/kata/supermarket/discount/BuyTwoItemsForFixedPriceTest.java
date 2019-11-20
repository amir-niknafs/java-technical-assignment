package kata.supermarket.discount;

import kata.supermarket.Item;
import kata.supermarket.Product;
import kata.supermarket.WeighedProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BuyTwoItemsForFixedPriceTest {

    @DisplayName("apply buy two for a fixed amount")
    @ParameterizedTest(name = "{0}")
    @MethodSource
    void buyThreeItemsForPriceOfTwo(String description, Item item1, Item item2, BigDecimal fixedPrice, BigDecimal expectedDiscount) {
        BuyTwoItemsForFixedPrice buyThreeItemsForPriceOfTwo = new BuyTwoItemsForFixedPrice(item1, item2, fixedPrice);
        assertEquals(expectedDiscount, buyThreeItemsForPriceOfTwo.apply());
    }

    static Stream<Arguments> buyThreeItemsForPriceOfTwo() {
        return Stream.of(
                Arguments.of("two apples for 70 pence should have 10 pence discount", getAnApple(), getAnApple(), new BigDecimal("0.70"), new BigDecimal("0.10")),
                Arguments.of("two kilos of potato for 1 pound should have 1 pound discount", kiloOfPotato(), kiloOfPotato(), new BigDecimal("1.00"), new BigDecimal("1.00")),
                Arguments.of("one apple and 1 kilo of potato for 1 pound should have 90 pence discount ", getAnApple(), kiloOfPotato(), new BigDecimal("1.00"), new BigDecimal("0.90"))
        );
    }


    private static Item getAnApple() {
        return new Product(new BigDecimal("0.40")).oneOf();
    }

    private static Item kiloOfPotato() {
        return new WeighedProduct(new BigDecimal("1.50")).weighing(new BigDecimal("1"));
    }
}