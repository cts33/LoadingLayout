package com.example.library_2;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

public class LoadingLayout extends FrameLayout implements IStatusView {

    public static final int SUCCESS = 0;
    public static final int EMPTY = 1;
    public static final int LOADING = 2;
    public static final int ERROR = 3;

    private LayoutInflater mInflater;

    private int mEmptyImage;
    private CharSequence mEmptyText;
    private int mEmptyResId = NO_ID, mLoadingResId = NO_ID, mErrorResId = NO_ID;
    private int mContentId = NO_ID;
    private  int mErrorImage;
    private CharSequence mErrorText, mRetryText;
    private int mStatus;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingLayout(@NonNull Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
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

        switch (status) {
            case SUCCESS:
                setContent();
                break;
            case ERROR:
                setError();
                break;
            case LOADING:
                setLoading();
                break;
            case EMPTY:
                setEmpty();
                break;
        }
    }

    private void setContent() {

        show(mContentId);
    }

    private void setLoading() {

        show(mLoadingResId);
    }

    private void setError() {

        show(mErrorResId);
        image(mErrorResId, R.id.error_image, R.drawable.icon_error);
        text(mErrorResId, R.id.error_text, "?????????");
        btn(mErrorResId, R.id.retry_button, "????????????");
    }

    private void setEmpty() {

        show(mEmptyResId);
        image(mEmptyResId, R.id.empty_image, R.drawable.icon_empty);
        text(mEmptyResId, R.id.empty_text, "?????????");
    }

    public LoadingLayout setErrorImage(@DrawableRes int resId) {
        mErrorImage = resId;
        image(mErrorResId, R.id.error_image, mErrorImage);
        return this;
    }

    public LoadingLayout setErrorText(String value) {
        mErrorText = value;
        text(mErrorResId, R.id.error_text, mErrorText);
        return this;
    }

    Map<Integer, View> mLayouts = new HashMap<>();

    public void show(int layoutId) {
        for (View view : mLayouts.values()) {
            if (view.getVisibility() != View.GONE) {
                view.setVisibility(GONE);
            }
        }
        if (layoutId == -1)
            return;
        View layout = layout(layoutId);
        updateStatusByLayoutId(layoutId);
        if (layout.getVisibility() != View.VISIBLE) {
            layout.setVisibility(View.VISIBLE);
        }
    }

    private View layout(int layoutId) {
        if (mLayouts.containsKey(layoutId)) {
            return mLayouts.get(layoutId);
        }
        View layout = mInflater.inflate(layoutId, this, false);
        layout.setVisibility(GONE);

        addView(layout, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));

        mLayouts.put(layoutId, layout);

        if (layoutId == mEmptyResId) {
            ImageView img = layout.findViewById(R.id.empty_image);
            if (img != null && mEmptyImage > 0) {
                img.setImageResource(mEmptyImage);
            }
            TextView view = layout.findViewById(R.id.empty_text);
            if (view != null) {
                view.setText(mEmptyText);
            }
//            if (mOnEmptyInflateListener != null) {
//                mOnEmptyInflateListener.onInflate(layout);
//            }
        } else if (layoutId == mErrorResId) {
            ImageView img = layout.findViewById(R.id.error_image);
            if (img != null && mErrorImage > 0) {
                img.setImageResource(mErrorImage);
            }
            TextView txt = layout.findViewById(R.id.error_text);
            if (txt != null) {
                txt.setText(mErrorText);
            }
            View btn = layout.findViewById(R.id.retry_button);
            if (btn != null) {
                btn.setOnClickListener(mRetryButtonClickListener);
            }
//            if (mOnErrorInflateListener != null) {
//                mOnErrorInflateListener.onInflate(layout);
//            }
        }
        return layout;
    }

    private void updateStatusByLayoutId(int layoutId) {
        if (layoutId == mLoadingResId) {
            mStatus = LOADING;
        } else if (layoutId == mContentId) {
            mStatus = SUCCESS;
        } else if (layoutId == mEmptyResId) {
            mStatus = EMPTY;
        } else if (layoutId == mErrorResId) {
            mStatus = ERROR;
        }
    }

    private void text(int layoutId, int ctrlId, CharSequence value) {
        if (mLayouts.containsKey(layoutId)) {
            TextView view = mLayouts.get(layoutId).findViewById(ctrlId);
            if (view != null) {
                view.setText(value);
            }
        }
    }


    private void btn(int layoutId, int ctrlId, String resId) {
        if (mLayouts.containsKey(layoutId)) {
            Button view = mLayouts.get(layoutId).findViewById(ctrlId);
            if (view != null) {
                view.setText(resId);
            }
        }
    }


    private void image(int layoutId, int ctrlId, int resId) {
        if (mLayouts.containsKey(layoutId)) {
            ImageView view = mLayouts.get(layoutId).findViewById(ctrlId);
            if (view != null) {
                view.setImageResource(resId);
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount()==0)
            return;

        if (getChildCount()>1){
            removeViews(1,getChildCount()-1);
        }
        View view = getChildAt(0);
        mLayouts.put(view.getId(),view);
        mStatus = SUCCESS;
    }
    OnClickListener mRetryButtonClickListener;

    public void setRetryButtonClickListener(OnClickListener mRetryButtonClickListener) {
        this.mRetryButtonClickListener = mRetryButtonClickListener;
    }


}
