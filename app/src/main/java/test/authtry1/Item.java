package test.authtry1;

public class Item {
    String generatedItemId;
    long itemId;
    String itemName;
    long itemCost;
    String categoryId;


    public Item(){

    }

    public Item(String generatedItemId, long itemId, String itemName, long itemCost, String categoryId) {
        this.generatedItemId = generatedItemId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.categoryId = categoryId;
    }

    public String getGeneratedItemId() {
        return generatedItemId;
    }

    public long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public long getItemCost() {
        return itemCost;
    }

    public String getCategoryId() {
        return categoryId;
    }
}
