package test.authtry1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listViewCart;
    TextView textView;
    Button buttonPlaceOrder;
    DatabaseReference databaseOrders;

    Cart cart = Cart.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listViewCart = findViewById(R.id.listCart);
        textView = findViewById(R.id.textViewTotal);

        textView.setText("Total Amount: "+cart.cartInstance.totalAmount+"");
//        cart.cartInstance.items.add(new ItemInOrder(10001,"",2,30));
//        textView.setText(cart.cartInstance.items.size()+"");

        CartList adapter = new CartList(CartActivity.this,cart.cartInstance.getItems());
        listViewCart.setAdapter(adapter);

        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder);
        buttonPlaceOrder.setOnClickListener(this);


        databaseOrders = FirebaseDatabase.getInstance().getReference("Orders");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPlaceOrder:
                String generatedOrderId = databaseOrders.push().getKey();
                Order temp = new Order(generatedOrderId,cart.cartInstance.getOrderId(),cart.cartInstance.getUserId(),cart.cartInstance.getTotalAmount(),cart.cartInstance.isStatus(),cart.cartInstance.getItems(),System.currentTimeMillis());
                databaseOrders.child(generatedOrderId).setValue(temp);
                Toast.makeText(this, "Order Placed", Toast.LENGTH_SHORT).show();
                cart.cartInstance.items = null;
                cart.cartInstance.status = true;
                Intent intent = new Intent(this, HomeActivity.class);
                finish();
                startActivity(intent);


        }
    }

    public void onClickSub(View v){
        textView = findViewById(R.id.textViewTotal);
        RelativeLayout parentRow = (RelativeLayout)v.getParent();
        TextView idChild = (TextView)parentRow.getChildAt(1);
        cart.subItemFromCart(Long.parseLong(idChild.getText().toString()));
        if(cart.cartInstance.getItems().size() == 0){
            startActivity(new Intent(this, MenuActivity.class));
            Toast.makeText(this, "No items in cart", Toast.LENGTH_SHORT).show();
        } else{
            CartList adapter = new CartList(CartActivity.this,cart.cartInstance.getItems());
            listViewCart.setAdapter(adapter);
            textView.setText("Total Amount: "+cart.cartInstance.totalAmount+"");
        }
    }
}
