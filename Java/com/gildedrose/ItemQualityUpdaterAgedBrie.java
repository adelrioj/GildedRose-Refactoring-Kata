package com.gildedrose;

import static com.gildedrose.GildedRose.*;

public class ItemQualityUpdaterAgedBrie implements ItemQualityUpdater {

    @Override
    public Item updateQuality(Item item) {
        if (item.quality < MAX_QUALITY)
            item.quality = item.quality + QUALITY_VARIATION;

        item.sellIn = item.sellIn - SELLIN_VARIATION;

        if (item.sellIn < MINIMUM_QUALITY)
            if (item.quality < MAX_QUALITY)
                item.quality = item.quality + QUALITY_VARIATION;

        return item;
    }
}
