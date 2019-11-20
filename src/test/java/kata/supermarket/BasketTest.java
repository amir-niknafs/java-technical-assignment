package kata.supermarket;

import kata.supermarket.discount.BuyOneGetOneFree;
import kata.supermarket.discount.BuyThreeItemsForPriceOfTwo;
import kata.supermarket.discount.Discount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    @DisplayName("basket provides its total value when containing products with discounts...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValueWithDiscounts(String description, String expectedTotal, Iterable<Item> items, Iterable<Discount> discounts) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        discounts.forEach(basket::addDiscount);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValueWithDiscounts() {
        return Stream.of(
                multipleItemsWithADiscount(), multipleItemsWithABuyThreeForPriceOfTwo()
        );
    }

    private static Arguments multipleItemsWithADiscount() {
        return Arguments.of("two unit items with buy one get one free discount", "0.49",
                Arrays.asList(aPintOfMilk(), aPintOfMilk()),
                Collections.singleton(new BuyOneGetOneFree(aPintOfMilk())));
    }

    private static Arguments multipleItemsWithABuyThreeForPriceOfTwo() {
        return Arguments.of("Buy three items with buy three for price of two discount should reduce the total by the price of the cheapest item", "1.74",
                Arrays.asList(aPintOfMilk(), aPintOfMilk(), twoFiftyGramsOfAmericanSweets()),
                Collections.singleton(new BuyThreeItemsForPriceOfTwo(aPintOfMilk(), aPintOfMilk(), twoFiftyGramsOfAmericanSweets())));
    }


    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}