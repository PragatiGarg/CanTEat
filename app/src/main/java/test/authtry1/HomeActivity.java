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
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    Button profile;
    Button orders;
    Button menu;


    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();


        profile = findViewById(R.id.buttonProfile);
        orders = findViewById(R.id.buttonOrders);
        menu = findViewById(R.id.buttonMenu);
        profile.setOnClickListener(this);
        orders.setOnClickListener(this);
        menu.setOnClickListener(this);


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
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.buttonProfile:
                startActivity(new Intent(this,ProfileNewActivity.class));
                break;
            case R.id.buttonOrders:
                startActivity(new Intent(this,OrderActivity.class));
//                Toast.makeText(this, "This service will be made available shortly", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonMenu:
//                Toast.makeText(this, "This service will be made available shortly", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MenuActivity.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,MainActivity.class));

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.hamburgermenu, menu);
//        return true;
//    }

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
            startActivity(new Intent(this,AboutActivity.class));
        } else if (id == R.id.nav_order_history) {
            startActivity(new Intent(this,OrderActivity.class));
        }  else if (id == R.id.nav_profile) {
            startActivity(new Intent(this,ProfileNewActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
