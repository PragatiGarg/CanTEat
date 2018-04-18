package test.authtry1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 17-04-2018.
 */

public class CartList extends ArrayAdapter<ItemInOrder> {

    private Activity context;
    private List<ItemInOrder> list;

    public CartList(Activity context, List<ItemInOrder> list){

        super(context,R.layout.cartlist,list);
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listView = inflater.inflate(R.layout.cartlist,null,true);
        TextView itemId = listView.findViewById(R.id.textViewItemId);
        TextView itemName = listView.findViewById(R.id.textViewItemName);
        TextView itemQuantity = listView.findViewById(R.id.textViewItemQuantity);
        TextView itemCost = listView.findViewById(R.id.textViewItemCost);


        ItemInOrder item = list.get(position);

        itemId.setText(item.getItemId()+"");
        itemName.setText(item.getItemName());
        itemQuantity.setText(item.getQuantity()+"");
        itemCost.setText(item.getItemCost()+"");

        return listView;


    }
}
