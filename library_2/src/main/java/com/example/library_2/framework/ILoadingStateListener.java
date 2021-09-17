package com.example.library_2.framework;

public interface ILoadingStateListener<T> {
    void onLoadChanged(LoadingState state,T data);
}
