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

class BuyOneGetOneFreeTest {

    @DisplayName("apply buy one get one free")
    @ParameterizedTest(name = "{0}")
    @MethodSource
    void buyOneItemGetOneFree(String description, Item item1, BigDecimal expectedDiscount) {
        BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree(item1);
        assertEquals(expectedDiscount, buyOneGetOneFree.apply());
    }

    static Stream<Arguments> buyOneItemGetOneFree() {
        return Stream.of(
                Arguments.of("two apples should have 40 pence discount", getAnApple(), new BigDecimal("0.40")),
                Arguments.of("two kilos of potato should have 1 pound and 50 pence discount", kiloOfPotato(), new BigDecimal("1.50"))
        );
    }


    private static Item getAnApple() {
        return new Product(new BigDecimal("0.40")).oneOf();
    }

    private static Item kiloOfPotato() {
        return new WeighedProduct(new BigDecimal("1.50")).weighing(new BigDecimal("1"));
    }

}