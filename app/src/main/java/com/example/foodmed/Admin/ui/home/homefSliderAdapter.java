package com.example.foodmed.Admin.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.foodmed.R;

//  for imageslider on homefragment  slideshow adapter
public class homefSliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    int[] images= {R.drawable.logo,R.drawable.s1,R.drawable.s3,
            R.drawable.s4,R.drawable.s5};

    public homefSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.custom_layout,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);


        imageView.setImageResource(images[position]);


        ViewPager vp =(ViewPager) container;
        vp.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp =(ViewPager) container;
        View view =(View) object;
        vp.removeView(view);
    }
}
