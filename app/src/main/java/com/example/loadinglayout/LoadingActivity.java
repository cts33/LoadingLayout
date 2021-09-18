package com.example.loadinglayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.library.IRetryListener;
import com.example.library.LoadingLayout;


public class LoadingActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mSuccess;
    private LinearLayout mFailed;
    private LinearLayout mEmpty;
    private ImageView mImage1;
    private ImageView mImage2;
    private ImageView mImage3;
    private LoadingLayout mLoadinglayout1;
    private LoadingLayout mLoadinglayout2;
    private LoadingLayout mLoadinglayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading2);

        initView();
    }


    private void initView() {
        mSuccess = (LinearLayout) findViewById(R.id.success);
        mFailed = (LinearLayout) findViewById(R.id.failed);
        mEmpty = (LinearLayout) findViewById(R.id.empty);
        mEmpty.setOnClickListener(this);
        mFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mFailed.setOnClickListener(null);
                failed();
            }
        });
        mSuccess.setOnClickListener(this);
        mImage1 = (ImageView) findViewById(R.id.image1);
        mImage2 = (ImageView) findViewById(R.id.image2);
        mImage3 = (ImageView) findViewById(R.id.image3);
        mLoadinglayout1 = (LoadingLayout) findViewById(R.id.loadinglayout1);
        mLoadinglayout2 = (LoadingLayout) findViewById(R.id.loadinglayout2);
        mLoadinglayout3 = (LoadingLayout) findViewById(R.id.loadinglayout3);

        mLoadinglayout2.setRetryClickListener(() -> {

//            Toast.makeText(LoadingActivity.this, "点击重试", Toast.LENGTH_SHORT).show();
            failed();
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.success:
                success();
                break;

            case R.id.empty:
                empty();
                break;
        }
    }

    String imgUrl = "http://imgs.jddmoto.com/square/20210531/20210531151405_sqzy.jpg!square?_1242_1242";

    private void success() {
        mLoadinglayout1.showLoading();
        Glide.with(this).load(imgUrl).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                mLoadinglayout1.showLoadFailed();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                mLoadinglayout1.showLoadSuccess();
                return false;
            }
        }).into(mImage1);
    }

    private void failed() {
        mLoadinglayout2.showLoading();
        Glide.with(this).load(imgUrl).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                mLoadinglayout2.showLoading();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                mLoadinglayout2.showLoadFailed();
//
                return false;
            }
        }).into(mImage2);
    }


    private void empty() {
        mLoadinglayout3.showLoading();
        Glide.with(this).load(imgUrl).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                mLoadinglayout3.showLoading();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                mLoadinglayout3.showLoadEmpty();
                return false;
            }
        }).into(mImage3);
    }
}