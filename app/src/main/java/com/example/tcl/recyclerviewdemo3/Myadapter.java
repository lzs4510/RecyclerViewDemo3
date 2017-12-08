package com.example.tcl.recyclerviewdemo3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by TCL on 2017/12/8.
 */

public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<News.DataBean> mlist=new ArrayList<News.DataBean>();
    private Context mcontext;

    public Myadapter(ArrayList<News.DataBean> mlist, Context mcontext) {
        this.mlist = mlist;
        this.mcontext = mcontext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item, parent, false);
        RecyclerView.ViewHolder holder=null;
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //先进行强转
        ViewHolder holder1 = (ViewHolder) holder;
        //绑定数据
        holder1.mtitle.setText(mlist.get(position).getNews_title());
        Glide.with(mcontext).load(mlist.get(position).getPic_url()).into(holder1.mimage);

    }

    @Override
    public int getItemCount() {
        return mlist==null?0:mlist.size();
    }

    //定义一个ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mtitle;
        private ImageView mimage;

        public ViewHolder(View itemView) {
        super(itemView);
        mtitle=(TextView)itemView.findViewById(R.id.title);
        mimage=(ImageView)itemView.findViewById(R.id.image);
    }
}
}
