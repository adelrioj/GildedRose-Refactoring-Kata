package com.gildedrose;

class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAX_QUALITY = 50;
    public static final int MINIMUM_QUALITY = 0;
    public static final int QUALITY_VARIATION = 1;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            boolean notAgedBrie = !item.name.equals(AGED_BRIE);
            boolean notBackstagePasses = !item.name.equals(BACKSTAGE_PASSES);
            boolean notRagnaros = !item.name.equals(SULFURAS);
            if (notAgedBrie && notBackstagePasses) {
                if (item.quality > MINIMUM_QUALITY) {
                    if (notRagnaros) {
                        item.quality = item.quality - QUALITY_VARIATION;
                    }
                }
            } else {
                if (item.quality < MAX_QUALITY) {
                    item.quality = item.quality + QUALITY_VARIATION;

                    if (item.name.equals(BACKSTAGE_PASSES)) {
                        if (item.sellIn < 11) {
                            if (item.quality < MAX_QUALITY) {
                                item.quality = item.quality + QUALITY_VARIATION;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < MAX_QUALITY) {
                                item.quality = item.quality + QUALITY_VARIATION;
                            }
                        }
                    }
                }
            }

            if (notRagnaros) {
                item.sellIn = item.sellIn - QUALITY_VARIATION;
            }

            if (item.sellIn < MINIMUM_QUALITY) {
                if (notAgedBrie) {
                    if (notBackstagePasses) {
                        if (item.quality > MINIMUM_QUALITY) {
                            if (notRagnaros) {
                                item.quality = item.quality - QUALITY_VARIATION;
                            }
                        }
                    } else {
                        item.quality = MINIMUM_QUALITY;
                    }
                } else {
                    if (item.quality < MAX_QUALITY) {
                        item.quality = item.quality + QUALITY_VARIATION;
                    }
                }
            }
        }
    }
}
