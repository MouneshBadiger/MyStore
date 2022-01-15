package com.trendfly.store.ui.item;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import com.trendfly.store.R;

public class ItemImageAdapter  extends PagerAdapter {

    Context mContext;
    ItemImageAdapter(Context context, String[] imageUrls) {
        this.mContext = context;
        this.imageUrls = imageUrls;
    }

    private String[] imageUrls;


    @Override
    public int getCount() {
        return imageUrls.length;
    }

        @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ImageView) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        Picasso.get()
                .load(imageUrls[position])
               // .fit()
               // .centerCrop()
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
