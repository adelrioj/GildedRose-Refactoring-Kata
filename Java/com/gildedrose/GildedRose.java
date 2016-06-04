package com.gildedrose;

class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAX_QUALITY = 50;
    public static final int MINIMUM_QUALITY = 0;
    public static final int QUALITY_VARIATION = 1;
    public static final int SELLIN_VARIATION = 1;
    public static final int BACKSTAGE_PASSES_STARTS_DOUBLING_QUALITY_SELLIN = 10;
    public static final int BACKSTAGE_PASSES_STARTS_TRIPLING_QUALITY_SELLIN = 5;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            boolean isAgedBrie = item.name.equals(AGED_BRIE);
            boolean isBackstagePasses = item.name.equals(BACKSTAGE_PASSES);
            boolean isSulfuras = item.name.equals(SULFURAS);

            if (isSulfuras) {
                //Nothing to do here!
            } else if (isAgedBrie) {
                if (item.quality < MAX_QUALITY)
                    item.quality = item.quality + QUALITY_VARIATION;

                item.sellIn = item.sellIn - SELLIN_VARIATION;

                if (item.sellIn < MINIMUM_QUALITY)
                    if (item.quality < MAX_QUALITY)
                        item.quality = item.quality + QUALITY_VARIATION;

            } else if (isBackstagePasses) {
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

            } else { //any item
                if (item.quality > MINIMUM_QUALITY)
                    item.quality = item.quality - QUALITY_VARIATION;

                item.sellIn = item.sellIn - SELLIN_VARIATION;

                if (item.sellIn < MINIMUM_QUALITY && item.quality > MINIMUM_QUALITY)
                    item.quality = item.quality - QUALITY_VARIATION;
            }
        }
    }
}
