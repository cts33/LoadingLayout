package com.example.library_2;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class LoadingLayout extends FrameLayout implements IStatusView{

    private final int mEmptyResId;
    private final int mLoadingResId;
    private final int mErrorResId;
    LayoutInflater mInflater;

    int mEmptyImage;
    CharSequence mEmptyText;

    int mErrorImage;
    CharSequence mErrorText, mRetryText;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingLayout(@NonNull Context context) {
        this(context,null);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        mInflater = LayoutInflater.from(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadingLayout, defStyleAttr, R.style.LoadingLayoutDefaultStyle);
        mEmptyImage = a.getResourceId(R.styleable.LoadingLayout_llEmptyImage, NO_ID);
        mEmptyText = a.getString(R.styleable.LoadingLayout_llEmptyText);

        mErrorImage = a.getResourceId(R.styleable.LoadingLayout_llErrorImage, NO_ID);
        mErrorText = a.getString(R.styleable.LoadingLayout_llErrorText);
        mRetryText = a.getString(R.styleable.LoadingLayout_llRetryText);

        mEmptyResId = a.getResourceId(R.styleable.LoadingLayout_llEmptyResId, R.layout.loading_layout_empty);
        mLoadingResId = a.getResourceId(R.styleable.LoadingLayout_llLoadingResId, R.layout.loading_layout_loading);
        mErrorResId = a.getResourceId(R.styleable.LoadingLayout_llErrorResId, R.layout.loading_layout_error);
        a.recycle();
    }

    @Override
    public void setStatus(int status) {

        switch (status){
            case IStatusView.Success:
                break;
            case IStatusView.Error:
                break;
            case IStatusView.Loading:
                break;
            case IStatusView.Empty:
                break;
        }
    }
}
