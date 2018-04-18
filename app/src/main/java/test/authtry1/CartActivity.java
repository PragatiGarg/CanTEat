package test.authtry1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity{

    ListView listViewCart;
    TextView textView;
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

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
