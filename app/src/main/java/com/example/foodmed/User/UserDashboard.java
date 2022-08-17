package com.example.foodmed.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.foodmed.R;
import com.example.foodmed.User.ui.cart.UserCartFragment;
import com.example.foodmed.User.ui.home.UserHomeFragment;
import com.example.foodmed.User.ui.orders.UserOrderFragment;
import com.example.foodmed.User.ui.profile.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserDashboard extends AppCompatActivity {
    FragmentManager fragmentManager;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



        BottomNavigationView bottomNavigationView =  findViewById(R.id.nav_user_view);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_user_home:

                        fragment = new UserHomeFragment();
                        fragmentManager = UserDashboard.this.getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment_activity_admin_dashboard1, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;

                    case R.id.navigation__user_cart:

                        fragment = new UserCartFragment();
                        fragmentManager = UserDashboard.this.getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment_activity_admin_dashboard1, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.navigation_user_orders:
                        fragment = new UserOrderFragment();
                        fragmentManager = UserDashboard.this.getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment_activity_admin_dashboard1, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;

                    case R.id.navigation_user_profile:
                        fragment = new UserProfileFragment();
                        fragmentManager = UserDashboard.this.getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment_activity_admin_dashboard1, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;


                }

                return true;
            }




        });
    }
    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }
}