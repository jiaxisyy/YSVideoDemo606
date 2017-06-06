package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public class ParameterRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> names;
    private List<String> values;
    private List<String> units;
    private MyItemClickListener mItemClickListener;

    public ParameterRvAdapter(Context context, List<String> names, List<String> values, List<String> units) {
        mContext = context;
        this.names = names;
        this.values = values;
        this.units = units;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_setting_parameter, parent, false);
        return new MyViewHolder(inflate, mItemClickListener);
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setValues(List<String> values) {
        this.values = values;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //解决参数设置界面错误问题
        holder.setIsRecyclable(false);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.mTitle.setText(names.get(position));
        viewHolder.mValue.setText(values.get(position));
        viewHolder.mUnit.setText(units.get(position));

    }

    @Override
    public int getItemCount() {
        return names == null ? 0 : names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;
        TextView mValue;
        TextView mUnit;
        LinearLayout mLinearLayout;
        private MyItemClickListener mListener;

        public MyViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            mListener = listener;
            mTitle = (TextView) itemView.findViewById(R.id.tv_item_system_setting);
            mValue = (TextView) itemView.findViewById(R.id.tv_item_system_setting_value);
            mUnit = (TextView) itemView.findViewById(R.id.tv_item_system_setting_unit);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_system_setting);
            mLinearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getPosition(), mValue.getText().toString());
            }
        }

    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position, String value);
    }
}
