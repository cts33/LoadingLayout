package com.example.library;


import android.view.View;

/**
 *
 *
 */
public interface ILoadingView   {
    int STATUS_LOADING = 1;
    int STATUS_LOAD_SUCCESS = 2;
    int STATUS_LOAD_FAILED = 3;
    int STATUS_EMPTY_DATA = 4;

    void showLoading();

    void showLoadSuccess();

    void showLoadFailed();

    void showLoadEmpty();

}

