package com.gildedrose;

import org.junit.Test;

import static com.gildedrose.GildedRose.*;
import static com.gildedrose.ItemBuilder.anItem;
import static org.junit.Assert.assertEquals;

public class GildedRoseTest {


    private static final String ANY_ITEM_NAME = "anyItem";
    private static final int ITEM_QUALITY = 15;
    private static final int ITEM_SELLIN = 15;


    @Test
    public void anyItemUpdatesQualityCorrectly() {
        checkQualityUpdate(anItem(ANY_ITEM_NAME).withQuality(ITEM_QUALITY).withSellIn(ITEM_SELLIN).build(), ITEM_QUALITY - QUALITY_VARIATION);
    }

    @Test
    public void qualityDegradesTwiceAsFastWhenSellDateHasPassed() {
        checkQualityUpdate(anItem(ANY_ITEM_NAME).withQuality(ITEM_QUALITY).withSellIn(0).build(), ITEM_QUALITY - 2 * QUALITY_VARIATION);
    }

    @Test
    public void qualityIsNeverNegative() {
        checkQualityUpdate(anItem(ANY_ITEM_NAME).withQuality(MINIMUM_QUALITY).withSellIn(ITEM_SELLIN).build(), MINIMUM_QUALITY);
    }

    @Test
    public void agedBrieIncreasesQualityWhileAged() {
        checkQualityUpdate(anItem(AGED_BRIE).withQuality(ITEM_QUALITY).withSellIn(ITEM_SELLIN).build(), ITEM_QUALITY + QUALITY_VARIATION);
    }

    @Test
    public void qualityIsNeverAvobeMaxQuality() {
        checkQualityUpdate(anItem(AGED_BRIE).withQuality(MAX_QUALITY).withSellIn(ITEM_SELLIN).build(), MAX_QUALITY);
    }

    @Test
    public void sulfurasNeverDecreasesQuality() {
        checkQualityUpdate(anItem(SULFURAS).withQuality(MAX_QUALITY).withSellIn(ITEM_SELLIN).build(), MAX_QUALITY);
    }

    @Test
    public void backstagePassesIncreasesQualityAtNormalRateWithSellInAboveBACKSTAGE_PASSES_STARTS_DOUBLING_QUALITY_SELLIN() {
        checkQualityUpdate(anItem(BACKSTAGE_PASSES).withQuality(ITEM_QUALITY).withSellIn(BACKSTAGE_PASSES_STARTS_DOUBLING_QUALITY_SELLIN + 1).build(), ITEM_QUALITY + QUALITY_VARIATION);
    }

    @Test
    public void backstagePassesIncreasesQualityAtTwiceNormalRateWithSellInBetweenBACKSTAGE_PASSES_STARTS_DOUBLING_QUALITY_SELLINAndBACKSTAGE_PASSES_STARTS_TRIPLING_QUALITY_SELLIN() {
        checkQualityUpdate(anItem(BACKSTAGE_PASSES).withQuality(ITEM_QUALITY).withSellIn(
                BACKSTAGE_PASSES_STARTS_DOUBLING_QUALITY_SELLIN - BACKSTAGE_PASSES_STARTS_TRIPLING_QUALITY_SELLIN + 1).build(), ITEM_QUALITY + 2 * QUALITY_VARIATION);
    }

    @Test
    public void backstagePassesIncreasesQualityAtThreeTimesNormalRateWithSellInBelowBACKSTAGE_PASSES_STARTS_TRIPLING_QUALITY_SELLIN() {
        checkQualityUpdate(anItem(BACKSTAGE_PASSES).withQuality(ITEM_QUALITY).withSellIn(BACKSTAGE_PASSES_STARTS_TRIPLING_QUALITY_SELLIN - 1).build(), ITEM_QUALITY + 3 * QUALITY_VARIATION);
    }

    private void checkQualityUpdate(Item anyItem, int expectedQuality) {
        Item[] items = new Item[]{anyItem};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedQuality, app.items[0].quality);
    }
}
