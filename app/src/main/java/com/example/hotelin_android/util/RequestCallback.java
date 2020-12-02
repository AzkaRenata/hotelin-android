package com.example.hotelin_android.util;

public interface RequestCallback<T> {
    void requestSuccess(T data);
    void requestFailed(String errorMessage);

//    void requestSuccess(String id);
}

