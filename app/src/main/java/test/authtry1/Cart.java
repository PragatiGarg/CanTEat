package test.authtry1;

import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by User on 12-04-2018.
 */

public class Cart {

    Order cartInstance;

    private static Cart instance;
    private Cart(){
        cartInstance = new Order();
        cartInstance.status = true;
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
            cartInstance.totalAmount = cartInstance.totalAmount + cartInstance.items.get(i).getItemCost()*cartInstance.items.get(i).getQuantity();
        }

    }

    public void setStatus(boolean status){
        cartInstance.status = status;
    }

    public void addItemtoCart(long itemId, int quantity){

        //get item name and cost from item id
        String itemName = "Unknown";
        long itemCost = 0;
        if(cartInstance.items == null) {
            cartInstance.items = new ArrayList<>();
        }
        cartInstance.items.add(new ItemInOrder(itemId,itemName,quantity,itemCost));


        calculateTotalAmount();
    }

    public void addItemtoCart(long itemId, String itemName, int quantity,long itemCost){

        int flag =0;
        if(cartInstance.items == null){
            cartInstance.items =  new ArrayList<>();
        }
        for(ItemInOrder i:cartInstance.items){
            if(i.itemId == itemId){
                i.quantity++;
                flag = 1;
            }
        }
        if(flag == 0){
            cartInstance.items.add(new ItemInOrder(itemId,itemName,quantity,itemCost));
        }


        calculateTotalAmount();
    }

    public void subItemFromCart(final long itemId){
        for(ItemInOrder i:cartInstance.items){
            if(i.itemId == itemId){
                i.quantity--;
                if(i.quantity == 0){
                    cartInstance.items.remove(cartInstance.items.indexOf(i));
                }
            }
        }
    }

    public static synchronized Cart getInstance(){
        if(instance==null){
            instance=new Cart();
        }
        return instance;
    }


    public Order getCartInstance() {
        return cartInstance;
    }

    public void setOrderId(String orderId){
        cartInstance.orderId= orderId;
    }
}
