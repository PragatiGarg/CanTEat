package test.authtry1;

/**
 * Created by User on 20-03-2018.
 */

public class ItemInOrder {
    String itemId;
    String itemName;
    int quantity;

    public ItemInOrder(){

    }

    public ItemInOrder(String itemId, String itemName, int quantity) {

        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }
}
