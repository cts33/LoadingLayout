//package com.example.library;
//
//import android.content.Context;
//import android.util.SparseArray;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//import android.widget.FrameLayout;
//
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.constraintlayout.widget.ConstraintSet;
//import androidx.fragment.app.FragmentActivity;
//
//public class NoBoLoadingManager {
//
//
//    private static final String TAG = "NoBoLoadingManager";
//    private Context context;
//    private static FrameLayout wrapper;
//
//    private ILoadingView innerView;
//    private SparseArray<View> statusView = new SparseArray<>(4);
//
//
//    public NoBoLoadingManager(FragmentActivity activity) {
//        this.context = activity;
//        wrapper = activity.findViewById(android.R.id.content);
//    }
//
//    public NoBoLoadingManager(View rootView) {
//        this.context = rootView.getContext();
//        wrapView(rootView);
//    }
//
//
//    public void wrapView(View rootView) {
//        if (rootView == null)
//            throw new NullPointerException("rootview is null");
//
//        wrapper = new FrameLayout(context);
//        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
//
//        if (layoutParams != null) {
//            wrapper.setLayoutParams(layoutParams);
//        }
//        ViewGroup parent = null;
//        if (rootView.getParent() != null) {
//              parent = (ViewGroup) rootView.getParent();
//
//            int index = parent.indexOfChild(rootView);
//            parent.removeView(rootView);
//            parent.addView(wrapper, index);
//        }
//        findViewConstraintRelation(wrapper, rootView,parent);
//
//        FrameLayout.LayoutParams newLp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//        wrapper.addView(rootView, newLp);
//
//
//    }
//
//    /**
//     * 查找view的约束依赖关系
//     */
//    private void findViewConstraintRelation(FrameLayout wrapper, View rootView,ViewParent parent) {
//
//        if (parent == null)
//            return;
//
//
//        if (parent instanceof ConstraintLayout) {
//            wrapper.setId(Integer.MAX_VALUE);
//            for (int i = 0; i < ((ConstraintLayout) parent).getChildCount(); i++) {
//                View itemView = ((ConstraintLayout) parent).getChildAt(i);
//
//                ConstraintLayout.LayoutParams constrlayoutParams = (ConstraintLayout.LayoutParams) itemView.getLayoutParams();
//                if (rootView.getId() == constrlayoutParams.leftToLeft) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.leftToLeft = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//
//                if (rootView.getId() == constrlayoutParams.leftToRight) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.leftToRight = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//                if (rootView.getId() == constrlayoutParams.startToStart) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.startToStart = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//                if (rootView.getId() == constrlayoutParams.startToEnd) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.startToEnd = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//                if (rootView.getId() == constrlayoutParams.rightToLeft) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.rightToLeft = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//                if (rootView.getId() == constrlayoutParams.rightToRight) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.rightToRight = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//                if (rootView.getId() == constrlayoutParams.endToStart) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.endToStart = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//                if (rootView.getId() == constrlayoutParams.endToEnd) {
//                    ConstraintSet set = new ConstraintSet();
//                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT);
//                    layoutParams.endToEnd = wrapper.getId();
//
//                    set.connect(itemView.getId(), ConstraintSet.END,  wrapper.getId(), ConstraintSet.END);
//
//                    set.applyTo((ConstraintLayout) parent);
//                    wrapper.setLayoutParams(layoutParams);
//                }
//                if (rootView.getId() == constrlayoutParams.topToTop) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.topToTop = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//                if (rootView.getId() == constrlayoutParams.topToBottom) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.topToBottom = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//
//                if (rootView.getId() == constrlayoutParams.bottomToBottom) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.bottomToBottom = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//                if (rootView.getId() == constrlayoutParams.bottomToTop) {
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) wrapper.getLayoutParams();
//                    layoutParams.bottomToTop = wrapper.getId();
//                    wrapper.setLayoutParams(layoutParams);
//                }
//            }
//        }
//    }
//
//
//    public void showLoading() {
//        showLoading(null);
//    }
//
//
//    /**
//     * 执行loading
//     */
//    public void showLoading(ILoadingView inputView) {
//
//
//        // 复用view
//        innerView = (ILoadingView) statusView.get(ILoadingView.STATUS_LOADING);
//
//        innerView = innerView == null ? (ILoadingView) statusView.get(ILoadingView.STATUS_LOAD_FAILED) : innerView;
//
//        //缓存里没有，取外部
//        if (innerView == null) {
//
//            if (inputView != null) {
//
//                innerView = inputView;
//
//                ViewGroup parent;
//                if ((parent = (ViewGroup) innerView.getParent()) != null) {
//                    parent.removeView(innerView);
//                }
//                statusView.put(ILoadingView.STATUS_LOADING, innerView);
//
//                wrapper.addView(innerView);
//            } else {
//                innerView = new NormalLoadingView(context);
//                statusView.put(ILoadingView.STATUS_LOADING, innerView);
//
//                wrapper.addView(innerView);
//
//            }
//
//        } else {
//
//            ViewGroup parent;
//            if ((parent = (ViewGroup) innerView.getParent()) != null) {
//                parent.removeView(innerView);
//            }
//
//            wrapper.addView(innerView);
//        }
//
//        innerView.setVisibleByStatus(NormalLoadingView.STATUS_LOADING);
//    }
//
//    public void showLoadFailed() {
//
//        showLoadFailed(null);
//
//    }
//
//
//    public void showLoadFailed(ILoadingView.IRetryClickListener iRetryClickListener) {
//        checkNotNull();
//        statusView.put(ILoadingView.STATUS_LOAD_FAILED, innerView);
//        innerView.setVisibleByStatus(NormalLoadingView.STATUS_LOAD_FAILED);
//        innerView.setIRetryClickListener(iRetryClickListener);
//    }
//
//    public void showLoadSuccess() {
//
//        showLoadSuccess(null);
//    }
//
//    public void showLoadSuccess(String msg) {
//        checkNotNull();
//        statusView.put(ILoadingView.STATUS_LOAD_SUCCESS, innerView);
//        innerView.setVisibleByStatus(NormalLoadingView.STATUS_LOAD_SUCCESS);
//    }
//
//
//    private void checkNotNull() {
//        if (innerView == null)
//            throw new NullPointerException("innerview must be not null");
//    }
//
//    /**
//     * 回收资源
//     */
//    public void clear() {
//        if (this.context != null)
//            this.context = null;
//
//        statusView.clear();
//    }
//
//}
