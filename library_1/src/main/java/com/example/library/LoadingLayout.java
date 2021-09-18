package com.example.library;

import static com.example.library.ILoadingView.STATUS_EMPTY_DATA;
import static com.example.library.ILoadingView.STATUS_LOADING;
import static com.example.library.ILoadingView.STATUS_LOAD_FAILED;
import static com.example.library.ILoadingView.STATUS_LOAD_SUCCESS;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LoadingLayout extends FrameLayout {

    private LoadingView loadingView;

    public LoadingLayout(@NonNull Context context) {
        this(context, null);
    }

    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initConfig();
    }

    public static final int MAX_CHILD_LIMIT = 2 - 1;

    @Override
    public void addView(View child) {
        if (getChildCount() > MAX_CHILD_LIMIT) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }

        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        if (getChildCount() > MAX_CHILD_LIMIT) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }

        super.addView(child, index);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (getChildCount() > MAX_CHILD_LIMIT) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }

        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() > MAX_CHILD_LIMIT) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }

        super.addView(child, index, params);
    }

    private void initConfig() {
        if (loadingView == null)
            loadingView = new LoadingView(getContext());
    }

    public void showLoading() {

        loadingView.setVisibleByStatus(STATUS_LOADING);
        int index = indexOfChild(loadingView);
        if (index == -1) {
            addView(loadingView);
        }
    }

    public void showLoadSuccess() {
        loadingView.setVisibleByStatus(STATUS_LOAD_SUCCESS);
    }

    public void showLoadFailed() {

        loadingView.setVisibleByStatus(STATUS_LOAD_FAILED);
    }


    public void setRetryClickListener(IRetryListener iRetryListener){

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iRetryListener != null) {
                    iRetryListener.retryClick();
                }
            }
        });
//        loadingView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                loadingView.setOnClickListener(null);
//            }
//        });

    }

    public void showLoadEmpty() {

        loadingView.setVisibleByStatus(STATUS_EMPTY_DATA);
    }

    /**
     * 设置 文本信息，文本color 文本size,
     * 如果想使用部分参数，传默认值即可（）
     *
     * @param loadingText null or ""
     * @param resColor    -1
     * @param textSize    -1f
     * @return
     */
    public LoadingLayout setLoadingText(String loadingText, int resColor, float textSize) {
        checkNotNUll();
        loadingView.setLoadText(loadingText);
        loadingView.setColor(resColor);
        loadingView.setTextSize(textSize);

        return this;
    }


    private void checkNotNUll() {
        if (loadingView == null)
            throw new NullPointerException(getResources().getString(R.string.loadview_is_null));
    }

}
