package com.example.library;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

public class LoadingView extends LinearLayout implements ILoadingView {

    private LayoutInflater inflater;
    private Context context;
    private ImageView mImage;
    private IRetryClickListener iRetryClickListener;
    private TextView mDes;

    private String errorMsg, emptyMsg, loadingMsg;
    private int bgColor;

    public LoadingView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        context = getContext();
        inflater = LayoutInflater.from(context);
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.view_loading, this, true);
        mImage = findViewById(R.id.image);
        mDes = findViewById(R.id.text);
        bgColor = getResources().getColor(android.R.color.white);

        errorMsg = getResources().getString(R.string.load_failed);
        emptyMsg = getResources().getString(R.string.load_empty);
        loadingMsg = getResources().getString(R.string.loading);

        setBackgroundColor(bgColor);
    }

    private static final String TAG = "NormalLoadingView";
    private boolean show = true;
    private String currMsg;
    private int currImage = R.drawable.loading_anim;
    private OnClickListener onClickListener;

    public void setVisibleByStatus(int status) {


        switch (status) {
            case STATUS_LOAD_SUCCESS:
                showLoadSuccess();
                break;
            case STATUS_LOADING:

                showLoading();
                break;
            case STATUS_LOAD_FAILED:

                showLoadFailed();
                break;
            case STATUS_EMPTY_DATA:

                showLoadEmpty();
                break;
            default:
                break;
        }
        mImage.setImageResource(currImage);
        setOnClickListener(onClickListener);
        mDes.setText(currMsg);
        setVisibility(show ? View.VISIBLE : View.GONE);
    }



    @Override
    public void showLoading() {
        currMsg = loadingMsg;
        currImage = R.drawable.loading_anim;
        show = true;
    }

    @Override
    public void showLoadSuccess() {
        show = false;
    }

    @Override
    public void showLoadFailed() {
        currMsg = errorMsg;
        currImage = R.drawable.icon_failed;
        show = true;

    }
    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }


    @Override
    public void showLoadEmpty() {
        currMsg = emptyMsg;
        currImage = R.drawable.icon_empty;
        show = true;
    }

    public void setLoadText(String loadingText) {
        mDes.setText(loadingText);
    }

    public void setColor(@ColorInt int resColor) {
        mDes.setTextColor(resColor);
    }

    public void setTextSize(float textSize) {
        mDes.setTextSize(textSize);
    }

    public void setBgColor( int bgColor) {
        this.bgColor = bgColor;
        setBackgroundColor(this.bgColor);
    }
}
