package com.example.library;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;

public class LoadingView extends LinearLayout implements ILoadingView {

    private Context context;
    private ImageView mImage;
    private TextView mDes;
    private boolean show = true;
    private String currMsg;
    private int currImage = R.drawable.icon_loading;
    private String errorMsg, emptyMsg, loadingMsg;
    private int bgColor;
    private ValueAnimator rotation;

    public LoadingView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        context = getContext();

        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.loading_view, this, true);
        mImage = findViewById(R.id.image);
        mDes = findViewById(R.id.text);
        bgColor = getResources().getColor(android.R.color.white);

        errorMsg = getResources().getString(R.string.load_failed);
        emptyMsg = getResources().getString(R.string.load_empty);
        loadingMsg = getResources().getString(R.string.loading);

        setBackgroundColor(bgColor);
    }

    public void setVisibleByStatus(int status, String msg) {


        switch (status) {
            case STATUS_LOAD_SUCCESS:
                showLoadSuccess();
                break;
            case STATUS_LOADING:

                showLoading();
                break;
            case STATUS_LOAD_FAILED:

                showLoadFailed(msg);
                break;
            case STATUS_EMPTY_DATA:

                showLoadEmpty(msg);
                break;
            default:
                break;
        }

        setVisibility(show ? View.VISIBLE : View.GONE);
    }


    @Override
    public void showLoading() {
        currMsg = loadingMsg;
        currImage = R.drawable.icon_loading;
        show = true;
        mImage.setImageResource(currImage);
        startAnim(mImage);

        mDes.setText(currMsg);
    }

    /**
     * 执行旋转属性动画
     *
     * @param mImage
     */
    private void startAnim(ImageView mImage) {

        clearAnim();
        rotation = ObjectAnimator.ofFloat(mImage, "rotation", 0f, 360f);
        rotation.setDuration(1000);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setTarget(mImage);
        rotation.setRepeatMode(ValueAnimator.RESTART);
        rotation.setRepeatCount(ValueAnimator.INFINITE);
        rotation.start();

    }

    @Override
    public void showLoadSuccess() {
        show = false;
        clearAnim();
    }

    /**
     * 停止动画
     */
    public void clearAnim() {
        if (rotation != null) {
            rotation.removeAllListeners();
            rotation.end();
            rotation.cancel();
            rotation = null;
        }
    }

    public void showLoadFailed(String msg) {
        clearAnim();

        currMsg = TextUtils.isEmpty(msg) ? errorMsg : msg;
        currImage = R.drawable.icon_failed;
        show = true;

        mImage.setImageResource(currImage);

        mDes.setText(currMsg);


    }



    @Override
    public void showLoadEmpty(String msg) {
        clearAnim();
        currMsg = TextUtils.isEmpty(msg) ? emptyMsg : msg;
        currImage = R.drawable.icon_empty;
        show = true;

        mImage.setImageResource(currImage);

        mDes.setText(currMsg);
    }

    public void setColor(@ColorInt int resColor) {
        mDes.setTextColor(resColor);
    }

    public void setTextSize(float textSize) {
        mDes.setTextSize(textSize);
    }

}
