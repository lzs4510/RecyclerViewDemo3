package com.example.tcl.recyclerviewdemo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetDataCallback<News>{

    private Myadapter md;
    private ArrayList<News.DataBean> mlist;
    private RecyclerView mrv;
    private OkhttpUtils mutils;
    private String url="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrv=(RecyclerView)findViewById(R.id.rv);
        //开始进行网络请求
       initdata();

    }
     //网络请求
    private void initdata() {
        mutils=new OkhttpUtils();
       mutils.getdata(url,this,News.class);

    }
    @Override
    public void success(News news) {
        //得到集合类
        mlist = (ArrayList<News.DataBean>) news.getData();
        //设置适配器
        md=new Myadapter(mlist,this);
        mrv.setLayoutManager(new LinearLayoutManager(this));
        mrv.setAdapter(md);

    }

    @Override
    public void fail(int code, String str) {

    }

}
