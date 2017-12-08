package com.example.tcl.recyclerviewdemo3;

/**
 * Created by TCL on 2017/12/8.
 */

public interface NetDataCallback<T> {
    void success(T t);
    void fail(int code,String str);
}
