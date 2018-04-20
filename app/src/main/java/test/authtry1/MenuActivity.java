package test.authtry1;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    DatabaseReference databaseItems;
    ListView listViewFood;
    List<Item> itemList;
    Button viewCart;
    Cart cart = Cart.getInstance();
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_menu);
        listViewFood = findViewById(R.id.listViewFood);
        databaseItems = FirebaseDatabase.getInstance().getReference("Items");
        itemList = new ArrayList<>();

        viewCart = findViewById(R.id.buttonCart);
        viewCart.setOnClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onClickAdd(View v){

        final FirebaseUser user = mAuth.getCurrentUser();
        RelativeLayout parentRow = (RelativeLayout)v.getParent();
        TextView idChild = (TextView)parentRow.getChildAt(4);
        TextView nameChild = (TextView)parentRow.getChildAt(1);
        TextView costChild = (TextView)parentRow.getChildAt(2);
        Button addButton = (Button) parentRow.getChildAt(3);
        cart.setStatus(false);
        cart.setUserId(user.getUid());
        String orderId = Math.abs((cart.cartInstance.getUserId()+System.currentTimeMillis()/1000).hashCode())+"";
        cart.setOrderId(orderId);
        long idText = Long.parseLong(idChild.getText().toString());
        String nameText = nameChild.getText().toString();
        String temp = costChild.getText().toString();
        int costText = Integer.parseInt(temp.substring(4,temp.length()-2));
        cart.addItemtoCart(idText,nameText,1,costText);
        Toast.makeText(getApplicationContext(),"Item added to cart",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this,CartActivity.class);
//        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonCart:
                if(cart.cartInstance.items == null){
                    Toast.makeText(this, "Sorry No items in cart", Toast.LENGTH_SHORT).show();
                } else{
                    startActivity(new Intent(this,CartActivity.class));
                }
                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        itemList.clear();
        databaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    Item item = itemSnapshot.getValue(Item.class);
                    itemList.add(item);

                }

                ItemList adapter = new ItemList(MenuActivity.this,itemList);
                listViewFood.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hamburgermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_canteen) {
            startActivity(new Intent(this,MenuActivity.class));
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_live_chat) {

        } else if (id == R.id.nav_order_history) {
            startActivity(new Intent(this,OrderActivity.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(this,ProfileActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
