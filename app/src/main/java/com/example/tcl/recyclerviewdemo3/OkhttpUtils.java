package com.example.tcl.recyclerviewdemo3;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.string.ok;

/**
 * Created by TCL on 2017/12/8.
 */

public class OkhttpUtils {
    private Handler hand=new Handler();
    private NetDataCallback netDataCallback;
    public <T>void getdata(String url, final NetDataCallback netDataCallback, final Class<T> tclass) {
        //初始化一个 OkHttpClient 并且设置连接和读取超时时间
        OkHttpClient okhttp=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Request request=new Request.Builder().url(url).build();

        Call call = okhttp.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                netDataCallback.fail(499,e.getMessage());
            }

            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                T t = gson.fromJson(response.body().string(), tclass);
                final Message msg = Message.obtain();
               msg.what=ok;
                msg.obj=t;
                hand.post(new Runnable() {
                    @Override
                    public void run() {
                        netDataCallback.success(msg.obj);

                    }
                });

            }
        });
    }
}
