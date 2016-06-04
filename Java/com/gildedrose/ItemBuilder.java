package com.gildedrose;

/**
 * Implements Builder pattern to item creation.
 * <br />
 * E.G: anItem("itemName").withQuality(quality).withSellIn(sellin).build()
 * @author adelrioj.
 */
public class ItemBuilder {

    private Item item;

    private ItemBuilder(Item item) {
        this.item = item;
    }

    public static ItemBuilder anItem(String itemName) {
        return new ItemBuilder(new Item(itemName, 0, 0));
    }

    public ItemBuilder withQuality(int quality) {
        this.item.quality = quality;
        return this;
    }

    public ItemBuilder withSellIn(int sellIn) {
        this.item.sellIn = sellIn;
        return this;
    }

    public Item build() {
        return this.item;
    }
}
