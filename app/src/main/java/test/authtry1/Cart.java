package test.authtry1;

/**
 * Created by User on 12-04-2018.
 */

public class Cart {

    Order cartInstance;

    public Cart(){
        cartInstance = new Order();
    }

    public void setUserId(String userId){
        cartInstance.userId = userId;
    }

    public void setTimeOfOrder(long timeOfOrder){
        cartInstance.timeOfOrder = timeOfOrder;
    }

    public void calculateTotalAmount(){

        cartInstance.totalAmount = 0;
        //for loop for all items
        for(int i=0;i<cartInstance.items.size();i++){
            cartInstance.totalAmount = cartInstance.totalAmount + cartInstance.items.get(i).getItemCost();
        }

    }










    public void setStatus(boolean status){
        cartInstance.status = status;
    }

    public void addItemtoCart(long itemId, int quantity){

        //get item name and cost from item id
        String itemName = "";
        long itemCost = 0;
        cartInstance.items.add(new ItemInOrder(itemId,itemName,quantity,itemCost));
        calculateTotalAmount();
    }

}
