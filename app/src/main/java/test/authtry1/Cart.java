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
        long itemCost = 30;
        if(cartInstance.items != null){
            cartInstance.items.add(new ItemInOrder(itemId,itemName,quantity,itemCost));
        } else{
            cartInstance.items =  new ArrayList<>();
//            cartInstance.items = new List<ItemInOrder>() {
//                @Override
//                public int size() {
//                    return 0;
//                }
//
//                @Override
//                public boolean isEmpty() {
//                    return false;
//                }
//
//                @Override
//                public boolean contains(Object o) {
//                    return false;
//                }
//
//                @NonNull
//                @Override
//                public Iterator<ItemInOrder> iterator() {
//                    return null;
//                }
//
//                @NonNull
//                @Override
//                public Object[] toArray() {
//                    return new Object[0];
//                }
//
//                @NonNull
//                @Override
//                public <T> T[] toArray(@NonNull T[] a) {
//                    return null;
//                }
//
//                @Override
//                public boolean add(ItemInOrder itemInOrder) {
//                    super(itemInOrder);
//                    return false;
//                }
//
//                @Override
//                public boolean remove(Object o) {
//                    return false;
//                }
//
//                @Override
//                public boolean containsAll(@NonNull Collection<?> c) {
//                    return false;
//                }
//
//                @Override
//                public boolean addAll(@NonNull Collection<? extends ItemInOrder> c) {
//                    return false;
//                }
//
//                @Override
//                public boolean addAll(int index, @NonNull Collection<? extends ItemInOrder> c) {
//                    return false;
//                }
//
//                @Override
//                public boolean removeAll(@NonNull Collection<?> c) {
//                    return false;
//                }
//
//                @Override
//                public boolean retainAll(@NonNull Collection<?> c) {
//                    return false;
//                }
//
//                @Override
//                public void clear() {
//
//                }
//
//                @Override
//                public ItemInOrder get(int index) {
//                    return null;
//                }
//
//                @Override
//                public ItemInOrder set(int index, ItemInOrder element) {
//                    return null;
//                }
//
//                @Override
//                public void add(int index, ItemInOrder element) {
//
//                }
//
//                @Override
//                public ItemInOrder remove(int index) {
//                    return null;
//                }
//
//                @Override
//                public int indexOf(Object o) {
//                    return 0;
//                }
//
//                @Override
//                public int lastIndexOf(Object o) {
//                    return 0;
//                }
//
//                @NonNull
//                @Override
//                public ListIterator<ItemInOrder> listIterator() {
//                    return null;
//                }
//
//                @NonNull
//                @Override
//                public ListIterator<ItemInOrder> listIterator(int index) {
//                    return null;
//                }
//
//                @NonNull
//                @Override
//                public List<ItemInOrder> subList(int fromIndex, int toIndex) {
//                    return null;
//                }
//            };
            cartInstance.items.add(new ItemInOrder(itemId,itemName,quantity,itemCost));

        }

        calculateTotalAmount();
    }
    public static synchronized Cart getInstance(){
        if(instance==null){
            instance=new Cart();
        }
        return instance;
    }



}
