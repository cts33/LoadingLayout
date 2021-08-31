package com.example.library_2;

public interface IStatusView {

    int Success = 0;
    int Empty = 1;
    int Loading = 2;
    int Error = 3;

    void setStatus(int status);

}
