package com.gildedrose;

import static com.gildedrose.GildedRose.*;

public class ItemQualityUpdaterFactory {

    public static ItemQualityUpdater getItemUpdater(String itemName) {
        if (SULFURAS.equals(itemName))
            return new ItemQualityUpdaterSulfuras();
        if (AGED_BRIE.equals(itemName))
            return new ItemQualityUpdaterAgedBrie();
        if (BACKSTAGE_PASSES.equals(itemName))
            return new ItemQualityUpdaterBackstagePasses();

        return new ItemQualityUpdaterGeneric();
    }
}
