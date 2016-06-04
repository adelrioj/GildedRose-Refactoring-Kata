package com.gildedrose;

import static com.gildedrose.GildedRose.*;

public class ItemQualityUpdaterGeneric implements ItemQualityUpdater {

    @Override
    public Item updateQuality(Item item) {
        if (item.quality > MINIMUM_QUALITY)
            item.quality = item.quality - QUALITY_VARIATION;

        item.sellIn = item.sellIn - SELLIN_VARIATION;

        if (item.sellIn < MINIMUM_QUALITY && item.quality > MINIMUM_QUALITY)
            item.quality = item.quality - QUALITY_VARIATION;

        return item;
    }
}
