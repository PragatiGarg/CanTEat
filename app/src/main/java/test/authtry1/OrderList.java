package test.authtry1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 21-03-2018.
 */

public class OrderList extends ArrayAdapter<Order> {

    private Activity context;
    private List<Order> orderList;

    public OrderList(Activity context,List<Order> orderList){

        super(context,R.layout.activity_order_old,orderList);
        this.context = context;
        this.orderList= orderList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewOrder = inflater.inflate(R.layout.order,null,true);

        TextView orderId = listViewOrder.findViewById(R.id.orderId);
        TextView orderItem = listViewOrder.findViewById(R.id.orderItem);
        TextView orderCost = listViewOrder.findViewById(R.id.orderCost);

        Order order = orderList.get(position);
        List<ItemInOrder> items = new ArrayList<>();
        items = order.getItems();
        String itemString = "\n";
        for(ItemInOrder item:items){
            itemString = itemString+item.getItemName()+" x "+item.getQuantity()+"\n";
        }
        orderId.setText("Order ID: "+order.getOrderId());
        orderItem.setText("Items: "+itemString );
        orderCost.setText("Total Amount: Rs. " + order.getTotalAmount());


        return listViewOrder;

    }
}
