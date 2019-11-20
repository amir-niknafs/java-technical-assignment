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

class BuyThreeItemsForPriceOfTwoTest {

    @DisplayName("apply Buy three for price of two")
    @ParameterizedTest(name = "{0}")
    @MethodSource
    void buyThreeItemsForPriceOfTwo(String description, Item item1, Item item2, Item item3, BigDecimal expectedDiscount) {
        BuyThreeItemsForPriceOfTwo buyThreeItemsForPriceOfTwo = new BuyThreeItemsForPriceOfTwo(item1, item2, item3);
        assertEquals(expectedDiscount, buyThreeItemsForPriceOfTwo.apply());
    }

    static Stream<Arguments> buyThreeItemsForPriceOfTwo() {
        return Stream.of(
                Arguments.of("three apples should have 40 pence discount", getAnApple(), getAnApple(), getAnApple(), new BigDecimal("0.40")),
                Arguments.of("3 kilos of potato should have 1 pound 50 pence discount", kiloOfPotato(), kiloOfPotato(), kiloOfPotato(), new BigDecimal("1.50")),
                Arguments.of("two apples and 1 kilos of potato should have 40 pence discount", getAnApple(), getAnApple(), kiloOfPotato(), new BigDecimal("0.40")),
                Arguments.of("one apple and two kilos of potato should have 40 pence discount", getAnApple(), getAnApple(), kiloOfPotato(), new BigDecimal("0.40"))
        );
    }


    private static Item getAnApple() {
        return new Product(new BigDecimal("0.40")).oneOf();
    }

    private static Item kiloOfPotato() {
        return new WeighedProduct(new BigDecimal("1.50")).weighing(new BigDecimal("1"));
    }
}