package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/24.
 */

public class MydeviceListRVAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> names;
    private List<String> status;
    private MyItemClickListener mItemClickListener;

    public MydeviceListRVAdapter(List<String> names, List<String> status, Context context) {
        this.names = names;
        this.status = status;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_mydevice_list, parent, false);
        return new MyViewHolder(inflate, mItemClickListener);
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setData(List<String> names, List<String> status) {
        this.names = names;
        this.status = status;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.mTitle.setText(names.get(position).toString());
        if (status.get(position).equals("OFFLINE")) {
            viewHolder.mTitle.setTextColor(Color.parseColor("#c9d3dc"));
        } else {
            viewHolder.mTitle.setTextColor(Color.parseColor("#627281"));
        }
        Log.d("TEST", "onBindViewHolder");
    }
    @Override
    public int getItemCount() {
        return names == null ? 0 : names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;
        private MyItemClickListener mListener;

        public MyViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.tv_item_mydevice_listName);
        }
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getPosition());
            }
        }
    }
    public interface MyItemClickListener {
       void onItemClick(View view, int postion);
    }
}
