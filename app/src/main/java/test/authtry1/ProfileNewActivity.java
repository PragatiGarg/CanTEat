package test.authtry1;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileNewActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener  {

    private static final int CHOOSE_IMAGE = 101;
    ImageView imageView;
    TextView textviewname;
    Uri profileImage;
    String downloadUrl;
    TextView textView;

    TextView textviewemail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_new);

        mAuth = FirebaseAuth.getInstance();


        textviewname = findViewById(R.id.displayName);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.emailVerify);
        textviewemail = findViewById(R.id.displayEmail);


        findViewById(R.id.editButton).setOnClickListener(this);
        findViewById(R.id.signOut).setOnClickListener(this);


        loadUserInformation();

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

        if(mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,MainActivity.class));

        }
    }

    private void loadUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();

        if(user!=null){
            if(user.getPhotoUrl()!=null){
                String photoUri = user.getPhotoUrl().toString();
                Glide.with(this)
                        .load(photoUri)
                        .into(imageView);
            }
            if(user.getDisplayName()!=null) {
                String displayName = user.getDisplayName();
                textviewname.setText(displayName);
            }
            if(user.getEmail()!=null) {
                String displayName = user.getEmail();
                textviewemail.setText(displayName);
            }
            if(user.isEmailVerified()){
                textView.setText("Email Verified");
            } else{
                textView.setText("Email Not Verified (CLick to verify)");
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(ProfileNewActivity.this,"Verifiaction Email Sent",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }

        }




    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.editButton:
                startActivity(new Intent(this,ProfileActivity.class));
                break;
            case R.id.signOut:
                signOutUser();
                break;

        }
    }

    private void signOutUser() {

        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this,MainActivity.class));
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

        } else if (id == R.id.nav_order_history) {
            startActivity(new Intent(this,OrderActivity.class));
        }         else if (id == R.id.nav_profile) {
            startActivity(new Intent(this,ProfileNewActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
