package com.example.shuangxiang.ysvideodemo.ui.data.show.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;

import java.util.List;
import java.util.Random;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public class DataShowBottomRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> names;
    private List<String> values;
    private List<String> units;

    public DataShowBottomRvAdapter(Context context, List<String> names, List<String> values, List<String> units) {
        mContext = context;
        this.names = names;
        this.values = values;
        this.units = units;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_show_monitoring_bottom, parent, false);
        return new MyViewHolder(inflate);
    }

    public void setValues(List<String> values) {
        this.values = values;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        if (names.size() == 0 || values.size() == 0 || units.size() == 0) {

        } else {
            viewHolder.mTitle.setText(names.get(position));
            viewHolder.mValue.setText(values.get(position) + units.get(position));
            int i = new Random().nextInt(4);
            switch (i) {
                case 0:
                    viewHolder.mPic.setImageResource(R.drawable.home2_data_blue);
                    viewHolder.mTitle.setTextColor(Color.parseColor("#0ABCD6"));
                    break;
                case 1:
                    viewHolder.mPic.setImageResource(R.drawable.home2_data_green);
                    viewHolder.mTitle.setTextColor(Color.parseColor("#31b77a"));
                    break;
                case 2:
                    viewHolder.mPic.setImageResource(R.drawable.home2_data_orange);
                    viewHolder.mTitle.setTextColor(Color.parseColor("#e98565"));
                    break;
                case 3:
                    viewHolder.mPic.setImageResource(R.drawable.home2_data_yellow);
                    viewHolder.mTitle.setTextColor(Color.parseColor("#efcf60"));
                    break;
            }
        }


    }

    @Override
    public int getItemCount() {
        return names == null ? 0 : names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mValue;
        ImageView mPic;


        public MyViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_item_monitoring_bottomName);
            mValue = (TextView) itemView.findViewById(R.id.tv_item_monitoring_bottomValue);
            mPic = (ImageView) itemView.findViewById(R.id.iv_item_monitoring_bottomPic);
        }
    }
}
