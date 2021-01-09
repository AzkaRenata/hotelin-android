package com.example.hotelin_android.util;

public interface RequestCallback<T> {
    void requestSuccess(T data, String message);
    void requestFailed(String message);
}

