package test.authtry1;

public class Item {
    String generatedItemId;
    long itemId;
    String itemName;
    long itemCost;
    String categoryId;
    int status;
    String imageUri;


    public Item(){

    }

    public Item(String generatedItemId, long itemId, String itemName, long itemCost, String categoryId, String imageUri) {
        this.generatedItemId = generatedItemId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.categoryId = categoryId;
        this.status = 1;
        this.imageUri = this.imageUri;
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

    public int getStatus() {
        return status;
    }

    public String getImageUri() {
        return imageUri;
    }
}
