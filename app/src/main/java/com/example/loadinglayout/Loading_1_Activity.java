package com.example.loadinglayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.library_2.LoadingLayout;

import java.util.Locale;
import java.util.Random;

import static com.example.library_2.LoadingLayout.EMPTY;
import static com.example.library_2.LoadingLayout.ERROR;
import static com.example.library_2.LoadingLayout.LOADING;
import static com.example.library_2.LoadingLayout.SUCCESS;

public class Loading_1_Activity extends AppCompatActivity {

    private ImageView mImageview;
    private static final String TAG = "Loading_1_Activity";
    private LoadingLayout mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading1);
        initViews();

        mLoading.setStatus( LOADING);
        Glide.with(this)
                .load(getRandomImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        mLoading.setStatus(EMPTY);
                        Log.d(TAG, "onLoadFailed: ");

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d(TAG, "onResourceReady: ");


                        mLoading.setStatus(SUCCESS);
                        return false;
                    }
                })
                .into(mImageview);
    }

    public static String getRandomImage() {
        Random random = new Random();
        int id = random.nextInt(100000);
        id = id%2==0 ? -1:0;

        String url = String.format(Locale.CHINA, "https://www.thiswaifudoesnotexist.net/example-%d.jpg", id);

        return url;
    }

    private void initViews() {
        mImageview = findViewById(R.id.imageView);
        mLoading = findViewById(R.id.loading);
    }
}