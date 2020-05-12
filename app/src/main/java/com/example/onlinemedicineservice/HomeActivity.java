package com.example.onlinemedicineservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.onlinemedicineservice.Model.Users;
import com.example.onlinemedicineservice.customerloginsignup.SigninActivity;
import com.example.onlinemedicineservice.drawermenuitems.cart.CartFragment;
import com.example.onlinemedicineservice.drawermenuitems.profile.ProfileFragment;
import com.example.onlinemedicineservice.drawermenuitems.store.StoreFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.example.onlinemedicineservice.common.common.currentUser;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private  long backPressedTime;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Store");

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar,
                                      R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();

        if(savedInstanceState == null) {
            Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT).show();
            toolbar.setTitle("Store");
            getSupportFragmentManager().beginTransaction().replace(R.id.host, new StoreFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_store);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        drawer.closeDrawers();
        switch(item.getItemId()){

            case R.id.nav_store:
                getSupportFragmentManager().beginTransaction().replace(R.id.host, new StoreFragment()).commit();
                toolbar.setTitle("Store");
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.host,
                        new ProfileFragment()).commit();
                toolbar.setTitle("Profile");
                break;
            case R.id.nav_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.host, new CartFragment()).commit();
                toolbar.setTitle("Cart");
                break;
            case R.id.nav_logout:
                logoutPressed();
                break;

        }
        return true;
    }

    private void logoutPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Log Out?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent  = new Intent(getApplicationContext(), SigninActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        HomeActivity.this.finish();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setIcon(R.drawable.ic_error_white_24dp)
                .show();
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(Gravity.LEFT);
        }else{

            if(navigationView.getCheckedItem().getItemId() == R.id.nav_cart ||
                    navigationView.getCheckedItem().getItemId() == R.id.nav_profile){

                toolbar.setTitle("Store");
                getSupportFragmentManager().beginTransaction().replace(R.id.host, new StoreFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_store);

            }else if(navigationView.getCheckedItem().getItemId() == R.id.nav_store){

                if (backPressedTime + 2000 > System.currentTimeMillis()) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return;
                } else {
                    Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
                }

                backPressedTime = System.currentTimeMillis();

            } else {
                super.onBackPressed();
            }



        }
    }

}
