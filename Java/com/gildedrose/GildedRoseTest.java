package com.gildedrose;

import org.junit.Test;

import static com.gildedrose.GildedRose.*;
import static com.gildedrose.ItemBuilder.anItem;
import static org.junit.Assert.assertEquals;

public class GildedRoseTest {


    private static final String ANY_ITEM_NAME = "anyItem";
    private static final int ITEM_QUALITY = 10;
    private static final int ITEM_SELL_IN = 10;


    @Test
    public void anyItemUpdatesQualityCorrectly() {
        checkQualityUpdate(anItem(ANY_ITEM_NAME).withQuality(ITEM_QUALITY).withSellIn(ITEM_SELL_IN).build(), ITEM_QUALITY - QUALITY_VARIATION);
    }

    @Test
    public void qualityDegradesTwiceAsFastWhenSellDateHasPassed() {
        checkQualityUpdate(anItem(ANY_ITEM_NAME).withQuality(ITEM_QUALITY).withSellIn(0).build(), ITEM_QUALITY - 2 * QUALITY_VARIATION);
    }

    @Test
    public void qualityIsNeverNegative() {
        checkQualityUpdate(anItem(ANY_ITEM_NAME).withQuality(MINIMUM_QUALITY).withSellIn(ITEM_SELL_IN).build(), MINIMUM_QUALITY);
    }

    @Test
    public void agedBrieIncreasesQualityWhileAged() {
        checkQualityUpdate(anItem(AGED_BRIE).withQuality(ITEM_QUALITY).withSellIn(ITEM_SELL_IN).build(), ITEM_QUALITY + QUALITY_VARIATION);
    }

    @Test
    public void qualityIsNeverAvobeMaxQuality() {
        checkQualityUpdate(anItem(AGED_BRIE).withQuality(MAX_QUALITY).withSellIn(ITEM_SELL_IN).build(), MAX_QUALITY);
    }

    @Test
    public void sulfurasNeverDecreasesQuality() {
        checkQualityUpdate(anItem(SULFURAS).withQuality(MAX_QUALITY).withSellIn(ITEM_SELL_IN).build(), MAX_QUALITY);
    }

    @Test
    public void backstagePassesIncreasesQualityAtNormalRateWithSellInAvobe10() {
        checkQualityUpdate(anItem(BACKSTAGE_PASSES).withQuality(ITEM_QUALITY).withSellIn(15).build(), ITEM_QUALITY + QUALITY_VARIATION);
    }

    @Test
    public void backstagePassesIncreasesQualityAtTwiceNormalRateWithSellInBetween5And10() {
        checkQualityUpdate(anItem(BACKSTAGE_PASSES).withQuality(ITEM_QUALITY).withSellIn(7).build(), ITEM_QUALITY + 2 * QUALITY_VARIATION);
    }

    @Test
    public void backstagePassesIncreasesQualityAtThreeTimesNormalRateWithSellInBelow5() {
        checkQualityUpdate(anItem(BACKSTAGE_PASSES).withQuality(ITEM_QUALITY).withSellIn(3).build(), ITEM_QUALITY + 3 * QUALITY_VARIATION);
    }

    private void checkQualityUpdate(Item anyItem, int expectedQuality) {
        Item[] items = new Item[]{anyItem};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedQuality, app.items[0].quality);
    }
}
