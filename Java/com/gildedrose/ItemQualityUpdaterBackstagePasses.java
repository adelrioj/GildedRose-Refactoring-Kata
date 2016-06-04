package com.gildedrose;

import static com.gildedrose.GildedRose.*;

public class ItemQualityUpdaterBackstagePasses implements ItemQualityUpdater {

    @Override
    public Item updateQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + QUALITY_VARIATION;
            if (item.sellIn <= BACKSTAGE_PASSES_STARTS_DOUBLING_QUALITY_SELLIN && item.quality < MAX_QUALITY)
                item.quality = item.quality + QUALITY_VARIATION;
            if (item.sellIn <= BACKSTAGE_PASSES_STARTS_TRIPLING_QUALITY_SELLIN && item.quality < MAX_QUALITY)
                item.quality = item.quality + QUALITY_VARIATION;
        }

        item.sellIn = item.sellIn - SELLIN_VARIATION;

        if (item.sellIn < MINIMUM_QUALITY)
            item.quality = MINIMUM_QUALITY;

        return item;
    }
}
