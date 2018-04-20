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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference databaseOrders;

    TextView textOrder;
    List<ItemInOrder> itemList;
    List<Order> orderList;

    ListView listViewOrder;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        databaseOrders = FirebaseDatabase.getInstance().getReference("Orders");
        textOrder = findViewById(R.id.textView3);
        listViewOrder = findViewById(R.id.listOrder);

        textOrder.setOnClickListener(this);
        itemList = new ArrayList<>();
        orderList = new ArrayList<>();

//        createOrders();

        mAuth = FirebaseAuth.getInstance();



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

    @Override
    protected void onStart() {
        super.onStart();
        orderList.clear();
        final FirebaseUser user = mAuth.getCurrentUser();
        databaseOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot orderSnapshot: dataSnapshot.getChildren()){
                    Order order= orderSnapshot.getValue(Order.class);
                    if(user.getUid().equals(order.getUserId())){
                        orderList.add(order);
                    }
                }
                Collections.reverse(orderList);
                OrderList adapter = new OrderList(OrderActivity.this,orderList);
                listViewOrder.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void createOrders(){


        for(int i =0;i<5;i++){
            itemList.add(new ItemInOrder(10000+i,"Poha",5-i,30));
        }
        String generatedOrderId = databaseOrders.push().getKey();
        Order temp = new Order(generatedOrderId,"100001","10001",250,true,itemList,125472);
        databaseOrders.child(generatedOrderId).setValue(temp);
        Toast.makeText(this,"Order Updated",Toast.LENGTH_SHORT).show();


    }




    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.textView3:
//                createOrders();
                break;

        }
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
