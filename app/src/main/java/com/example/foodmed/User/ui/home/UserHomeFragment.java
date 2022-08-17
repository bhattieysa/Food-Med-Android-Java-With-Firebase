package com.example.foodmed.User.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodmed.Admin.ui.home.homefSliderAdapter;
import com.example.foodmed.Admin.ui.products.ViewProductsFilterFragment;
import com.example.foodmed.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class UserHomeFragment extends Fragment {


    ViewPager viewPager;
    private Timer mtime;
    CardView card1,card2,card3,card4;
    FragmentManager fragmentManager;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        container.removeAllViews();
        // Inflate the layout for this fragment
        View  v1= inflater.inflate(R.layout.fragment_user_home, container, false);
        viewPager = v1.findViewById(R.id.viewpager);
        homefSliderAdapter viewPagerAdapter=new homefSliderAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);

        timetask timetask = new timetask();

        mtime = new Timer();
        mtime.schedule(timetask,2000,4000);
        card1=v1.findViewById(R.id.card1);
        card2=v1.findViewById(R.id.card2);
        card3=v1.findViewById(R.id.card3);
        card4=v1.findViewById(R.id.card4);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new UserViewProductsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("category", "All");
                fragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_admin_dashboard1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new UserViewProductsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("category", "Food");
                fragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_admin_dashboard1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new UserViewProductsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("category", "Medicine");
                fragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_admin_dashboard1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new UserViewProductsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("category", "Water Cans");
                fragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_admin_dashboard1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });








        return v1;
    }
    //home slider
    public class timetask extends TimerTask {

        @Override
        public void run() {

            if(getActivity()!=null) {
                getActivity().runOnUiThread(new Runnable() {


                    @Override
                    public void run() {

//                        if (viewPager.getCurrentItem() == 0) {
//                            viewPager.setCurrentItem(1);
//
//                        } else if (viewPager.getCurrentItem() == 1) {
//                            viewPager.setCurrentItem(2);
//                        } else if (viewPager.getCurrentItem() == 2) {
//                            viewPager.setCurrentItem(0);
//                        }
//                        int a = viewPager.getAdapter().getCount();
                        int a= new homefSliderAdapter(getContext()).getCount();
                        if(viewPager.getCurrentItem()+1==a){
                            viewPager.setCurrentItem(0);
                        }else {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    }
                });
            }

        }



    }
}