package com.example.shuangxiang.ysvideodemo.ui.setting.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public class ControlRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> names;
    private List<String> values;
    private MyItemClickListener mMyItemClickListener;

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        mMyItemClickListener = myItemClickListener;
    }

    public ControlRvAdapter(Context context, List<String> names, List<String> values) {
        mContext = context;
        this.names = names;
        this.values = values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_setting_control, parent, false);
        return new MyViewHolder(inflate, mMyItemClickListener);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.mTitle.setText(names.get(position));
        viewHolder.mCheckBox.setChecked(Boolean.valueOf(values.get(position)));
//        if (position == 0) {
//            viewHolder.mLongClick.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        return names == null ? 0 : names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View
            .OnLongClickListener, View.OnTouchListener {
        TextView mTitle;
        CheckBox mCheckBox;

        MyItemClickListener mMyItemClickListener;

        public MyViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            mMyItemClickListener = listener;
            mTitle = (TextView) itemView.findViewById(R.id.tv_item_control_setting);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.cb_item_control);
            mCheckBox.setOnLongClickListener(this);
            mCheckBox.setOnTouchListener(this);
        }


        @Override
        public boolean onLongClick(View view) {
            if (mMyItemClickListener != null) {
                mMyItemClickListener.onItemLongClick(view, getPosition(), mCheckBox.isChecked());
                Log.d("TEST", "onLongClick-if");
            }
            Log.d("TEST", "onLongClick");
            return false;
        }


        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                return true;
            }
            return false;
        }
    }

    public interface MyItemClickListener {
        void onItemLongClick(View view, int position, boolean isChecked);
    }
}
