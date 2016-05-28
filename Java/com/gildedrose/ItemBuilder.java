package com.gildedrose;

/**
 * @author adelrioj.
 */
public class ItemBuilder {

    private Item item;

    public ItemBuilder(Item item) {
        this.item = item;
    }

    public static ItemBuilder anItem(String itemName) {
        return new ItemBuilder(new Item(itemName, 0, 0));
    }

    public Item build() {
        return this.item;
    }

    public ItemBuilder withQuality(int quality) {
        this.item.quality = quality;
        return this;
    }

    public ItemBuilder withSellIn(int sellIn) {
        this.item.sellIn = sellIn;
        return this;
    }
}
