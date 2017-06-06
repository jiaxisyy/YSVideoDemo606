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

public class WarningListRVAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<WarningInfo.ListBean> data;


    public WarningListRVAdapter(List<WarningInfo.ListBean> data, Context context) {
        this.data = data;
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_warning_list, parent, false);
        return new MyViewHolder(inflate);
    }

    public void setData(List<WarningInfo.ListBean> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        String[] split = data.get(position).getCreateDateStr().split(" ");
        String newDate = split[0] + "\n" + split[1];
        viewHolder.createDate.setText(newDate);
        viewHolder.name.setText(data.get(position).getName());
        if (data.get(position).getStatus().equals("0")) {
            viewHolder.status.setText("报警");
        } else {
            viewHolder.status.setText("报警结束");
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView createDate;
        TextView name;
        TextView status;

        public MyViewHolder(View itemView) {
            super(itemView);
            createDate = (TextView) itemView.findViewById(R.id.tv_item_warning_listDate);
            name = (TextView) itemView.findViewById(R.id.tv_item_warning_listName);
            status = (TextView) itemView.findViewById(R.id.tv_item_warning_listStatus);

        }
    }
}
