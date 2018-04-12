package test.authtry1;

/**
 * Created by User on 20-03-2018.
 */

public class ItemInOrder {
    long itemId;
    String itemName;
    int quantity;
    long itemCost;

    public ItemInOrder(){

    }

    public ItemInOrder(long itemId, int quantity) {

        this.itemId = itemId;
        this.quantity = quantity;
    }

    public ItemInOrder(long itemId, String itemName, int quantity, long itemCost) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemCost = itemCost;
    }

    public long getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public long getItemCost() {
        return itemCost;
    }
}
