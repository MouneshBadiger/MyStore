package com.trendfly.store.ui.home;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;
import com.trendfly.store.R;
import com.trendfly.store.ui.item.ItemDetailsFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home1);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        //Top scroll cards
        LinearLayout linearLayout=root.findViewById(R.id.first_v_scrol);

        for(int i=0;i<10;i++){
            CardView cardView=new CardView(getActivity());
            LinearLayout.LayoutParams params0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            final float scale = getContext().getResources().getDisplayMetrics().density;
            int pixels = (int) (309 * scale + 0.5f);
            params0.height=pixels;
            params0.setMargins(30,30,30,30);
            cardView.setLayoutParams(params0);
            cardView.setPadding(10,10,0,0);
            cardView.setCardBackgroundColor(Color.parseColor("#92E6DC"));
            cardView.setCardElevation(50);
            //cardView.setRadius(23);

            ImageView imageView=new ImageView(getActivity());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params1);
           // imageView.setImageResource(R.drawable.shirt1);
            Picasso.get()
                    .load("https://i.imgur.com/DvpvklR.png")
                    .into(imageView);
            cardView.addView(imageView);



            TextView textView1 = new TextView(getActivity());
            textView1.setText("");
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1.setLayoutParams(params2);
            cardView.addView(textView1);
            final int itemId=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new ItemDetailsFragment();
                    Bundle args = new Bundle();
                    args.putSerializable("itemId",itemId);
                    fragment.setArguments(args);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
                }
            });


            linearLayout.addView(cardView);
        }
//Horizontal scroll cards
        LinearLayout linearLayoutH=root.findViewById(R.id.first_h_scroll);

        for(int i=0;i<8;i++){
            CardView cardView=new CardView(getActivity());
            LinearLayout.LayoutParams params0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            final float scale = getContext().getResources().getDisplayMetrics().density;
            int pixelsHight = (int) (171 * scale + 0.5f);
            int pixelsWidth = (int) (152 * scale + 0.5f);
            params0.height=pixelsHight;
            params0.width=pixelsWidth;
            params0.gravity= Gravity.CENTER;
            params0.setMargins(30,30,30,30);
            cardView.setLayoutParams(params0);
            cardView.setPadding(10,10,0,0);
            cardView.setCardBackgroundColor(Color.parseColor("#92E6DC"));
            cardView.setCardElevation(50);
            cardView.setRadius(95);

            ImageView imageView=new ImageView(getActivity());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params1);
            imageView.setImageResource(R.drawable.shirt1);
            Picasso.get()
                    .load("https://i.imgur.com/DvpvklR.png")
                    .into(imageView);
            cardView.addView(imageView);

            TextView textView1 = new TextView(getActivity());
            textView1.setText("");
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1.setLayoutParams(params2);
            cardView.addView(textView1);

            linearLayoutH.addView(cardView);
        }


        //Last tile scroll cards
        LinearLayout parent_layout=root.findViewById(R.id.parent_layout);

        for(int i=0;i<8;i++){
            LinearLayout linearLayoutTile=new LinearLayout(getActivity());
            linearLayoutTile.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams paramsX = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsX.setMargins(40,40,40,40);
            paramsX.gravity=Gravity.CENTER;
            linearLayoutTile.setLayoutParams(paramsX);

            CardView cardView1=new CardView(getActivity());
            LinearLayout.LayoutParams params0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            final float scale = getContext().getResources().getDisplayMetrics().density;
            int pixelsHight = (int) (171 * scale + 0.5f);
            int pixelsWidth = (int) (152 * scale + 0.5f);
            params0.height=pixelsHight;
            params0.width=pixelsWidth;
            params0.gravity= Gravity.CENTER;
            params0.setMargins(10,10,10,10);
            cardView1.setLayoutParams(params0);
            //cardView1.setPadding(0,5,0,0);
            cardView1.setCardBackgroundColor(Color.parseColor("#92E6DC"));
            //cardView1.setCardElevation(50);

            ImageView imageView=new ImageView(getActivity());
            LinearLayout.LayoutParams paramsI = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(paramsI);
            imageView.setImageResource(R.drawable.shirt1);
            Picasso.get()
                    .load("https://i.imgur.com/DvpvklR.png")
                    .into(imageView);
            cardView1.addView(imageView);

            TextView textView1 = new TextView(getActivity());
            textView1.setText("");
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1.setLayoutParams(params1);
            cardView1.addView(textView1);
            linearLayoutTile.addView(cardView1);
            ///////////////////////////////////////////////////////////////////
            CardView cardView2=new CardView(getActivity());
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            final float scale2 = getContext().getResources().getDisplayMetrics().density;
            int pixelsHight2 = (int) (171 * scale2 + 0.5f);
            int pixelsWidth2 = (int) (152 * scale2 + 0.5f);
            params2.height=pixelsHight2;
            params2.width=pixelsWidth2;
            params2.gravity= Gravity.CENTER;
            params2.setMargins(10,10,10,10);
            cardView2.setLayoutParams(params2);
            //cardView2.setPadding(0,5,0,0);
            cardView2.setCardBackgroundColor(Color.parseColor("#92E6DC"));
           // cardView2.setCardElevation(50);

            ImageView imageView1=new ImageView(getActivity());
            LinearLayout.LayoutParams paramsI1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            imageView1.setLayoutParams(paramsI1);
            //imageView1.setImageResource(R.drawable.shirt1);
            Picasso.get()
                    .load("https://i.imgur.com/DvpvklR.png")
                    .into(imageView1);
            cardView2.addView(imageView1);

            TextView textView2 = new TextView(getActivity());
            textView2.setText("");
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            textView2.setLayoutParams(params3);
            cardView2.addView(textView2);
            linearLayoutTile.addView(cardView2);
            ///////////////////////////////////////////////////////////////////////////////////
            parent_layout.addView(linearLayoutTile);
        }

        return root;
    }
}