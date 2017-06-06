package com.example.shuangxiang.ysvideodemo.ui.warning.record.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/24.
 */

public class WarningListRVAllAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<WarningInfo.ListBean> data;


    public WarningListRVAllAdapter(List<WarningInfo.ListBean> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_warning_list_all, parent, false);
        return new MyViewHolder(inflate);
    }

    public void setData(List<WarningInfo.ListBean> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.name.setText(data.get(position).getDeviceName());
        viewHolder.date.setText(data.get(position).getCreateDateStr());
        viewHolder.info.setText(data.get(position).getName());
        String level = data.get(position).getLevel();
        switch (level) {
            case "1"://红色
                viewHolder.grade.setText("红色预警");
                viewHolder.grade.setBackgroundResource(R.drawable.shape_warning_red);
                break;
            case "2"://橙色
                viewHolder.grade.setText("橙色预警");
                viewHolder.grade.setBackgroundResource(R.drawable.shape_warning_orange);
                break;
            case "3"://黄色
                viewHolder.grade.setText("黄色预警");
                viewHolder.grade.setBackgroundResource(R.drawable.shape_warning_yellow);
                break;
            case "4"://绿色
                viewHolder.grade.setText("绿色预警");
                viewHolder.grade.setBackgroundResource(R.drawable.shape_warning_green);
                break;
        }


//
//        viewHolder.grade.setText(data.get(position).getDeviceName());

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView grade;
        TextView name;
        TextView date;
        TextView info;

        public MyViewHolder(View itemView) {
            super(itemView);
            grade = (TextView) itemView.findViewById(R.id.tv_item_warning_listAll_grade);
            name = (TextView) itemView.findViewById(R.id.tv_item_warning_listAll_name);
            date = (TextView) itemView.findViewById(R.id.tv_item_warning_listAll_date);
            info = (TextView) itemView.findViewById(R.id.tv_item_warning_listAll_info);

        }
    }
}
