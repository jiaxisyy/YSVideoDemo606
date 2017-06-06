package com.example.shuangxiang.ysvideodemo.ui.data.analyze;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.data.DataShowFragment;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.adapter.DataAnalyzeCenterRvAdapter;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.p.DataAnalyzeP;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.v.IDataAnalyzeV;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.view.MyMarkerView;
import com.example.shuangxiang.ysvideodemo.ui.data.show.adapter.SpacesItemDecoration;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.p.SettingParameterP;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.v.ISettingParameterV;
import com.example.shuangxiang.ysvideodemo.ui.warning.WarningActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/5/20.
 */

public class DataAnalyzeFragment extends BaseFragment implements
        OnChartGestureListener, OnChartValueSelectedListener, ISettingParameterV, IDataAnalyzeV {
    @BindView(R.id.tv_data_analyze_title)
    TextView mTvDataAnalyzeTitle;
    @BindView(R.id.iv_data_analyze_warning)
    ImageView mIvDataAnalyzeWarning;
    @BindView(R.id.tb_data_analyze)
    Toolbar mTbDataAnalyze;
    @BindView(R.id.iv_data_analyze_analyze)
    ImageView mIvDataAnalyzeAnalyze;
    @BindView(R.id.iv_data_analyze_show)
    ImageView mIvDataAnalyzeShow;
    @BindView(R.id.rl_analyze)
    RelativeLayout mRlAnalyze;
    @BindView(R.id.tv_data_analyze_name)
    TextView mTvDataAnalyzeName;
    @BindView(R.id.rv_data_analyze_center)
    RecyclerView mRvCenter;
    @BindView(R.id.tab_data_analyze)
    TabLayout mTabDataAnalyze;
    @BindView(R.id.lc_data_analyze)
    LineChart mChart;

    private SettingParameterP mSettingParameterP;
    private LinearLayoutManager mLayoutManagerCenter;
    private DataAnalyzeCenterRvAdapter mRvAdapterCenter;
    private DataAnalyzeP mDataAnalyzeP;

    private String mSelectAddress;

    private String mSelectTimeType;
    private boolean mFirstInto = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data_analyze;
    }

    @Override
    protected void init() {
        mTbDataAnalyze.setNavigationIcon(R.drawable.icon_mydevice_back);
        mTbDataAnalyze.setTitle("");
        setImmerseLayout(mTbDataAnalyze);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mTbDataAnalyze);
        setHasOptionsMenu(true);

        String title = CacheUtils.getString(getActivity(), Constants.Define.MYDEVICE_TO_SECONDHOME_TBTITLE);
        if (title != null && !title.equals("")) {
            mTvDataAnalyzeTitle.setText(title);
        }
        List<String> timeTitles = new ArrayList<>();
        timeTitles.add("日");
        timeTitles.add("周");
        timeTitles.add("月");
        timeTitles.add("季");
        timeTitles.add("年");
        mTabDataAnalyze.setTabMode(TabLayout.MODE_FIXED);
        int size = timeTitles.size();
        if (mFirstInto) {
            for (int i = 0; i < size; i++) {
                mTabDataAnalyze.addTab(mTabDataAnalyze.newTab().setText(timeTitles.get(i)));
            }
            mSettingParameterP = new SettingParameterP(this, getActivity());
            mSettingParameterP.getTitle("MONITOR");
        }
        String datatemplateid = CacheUtils.getString(getActivity(), Constants.Define.MYDEVICE_TO_SECONDHOME_DATATEMPLATEID);
        String tableIdUrl = Constants.Define.BASE_URL + "elementTables?dataTemplateId=" + datatemplateid;
        Log.d("TEST", "tableIdUrl=" + tableIdUrl);
        mDataAnalyzeP = new DataAnalyzeP(this, getActivity());
        mDataAnalyzeP.getTableId(tableIdUrl);
        mTabDataAnalyze.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("TEST", "onTabSelected->position=" + position);

                switch (position) {
                    case 0:
                        mSelectTimeType = Constants.Define.ANALYZETIMETYPE_DAY;
                        break;
                    case 1:
                        mSelectTimeType = Constants.Define.ANALYZETIMETYPE_WEEK;
                        break;
                    case 2:
                        mSelectTimeType = Constants.Define.ANALYZETIMETYPE_MONTH;
                        break;
                    case 3:
                        mSelectTimeType = Constants.Define.ANALYZETIMETYPE_QUARTER;
                        break;
                    case 4:
                        mSelectTimeType = Constants.Define.ANALYZETIMETYPE_YEAR;
                        break;
                }
                mDataAnalyzeP.getStatistics(mSelectTimeType, mSelectAddress);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void initData() {
        //默认第一次选择按照日呈现
        mSelectTimeType = Constants.Define.ANALYZETIMETYPE_DAY;
    }

    protected void setImmerseLayout(View view) {
        //先将状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //获取状态栏的高度
            int statusBarHeight = getStatusBarHeight(getActivity());
            //将顶部空间的top padding设置为和状态栏一样的高度，以此达到预期的效果
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }


    private void drawLine(List<String> values) {
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        // mChart.setBackgroundColor(Color.GRAY);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        // x-axis limit line
        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);
        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        //xAxis.setValueFormatter(new MyCustomXAxisValueFormatter());
        //xAxis.addLimitLine(llXAxis); // add x-axis limit line

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");

        LimitLine ll1 = new LimitLine(150f, "Upper Limit");
        ll1.setLineColor(Color.parseColor("#627281"));
        ll1.setLineWidth(1f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);
        ll1.setTypeface(tf);

        LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
        ll2.setLineWidth(1f);
        ll2.setLineColor(Color.parseColor("#627281"));
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
        ll2.setTypeface(tf);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(-50f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        YAxis axisRight = mChart.getAxisRight();
        axisRight.setEnabled(false);


        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        // add data
        setData(values);

//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

        //动画X轴开始===>>>爽
//        mChart.animateX(2500);
        //mChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);

        // // dont forget to refresh the drawing
        // mChart.invalidate();


        actionToggleHorizontalCubic();
        animateY();
    }

    /**
     * 动画Y轴上升
     */
    private void animateY() {
        mChart.animateY(3000, Easing.EasingOption.EaseInCubic);
    }

    /**
     * 圆弧
     */
    private void actionToggleHorizontalCubic() {
        List<ILineDataSet> sets = mChart.getData()
                .getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                    ? LineDataSet.Mode.LINEAR
                    : LineDataSet.Mode.HORIZONTAL_BEZIER);
        }
        mChart.invalidate();
    }

    @Override
    protected boolean isCache() {
        return true;
    }

    private void setData(List<String> list) {

        ArrayList<Entry> values = new ArrayList<Entry>();
        int size = list.size();

        for (int i = 0; i < size; i++) {
            float val = Float.parseFloat(list.get(i));
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);


//            set1.setColor(Color.BLACK);
//            set1.setCircleColor(Color.BLACK);

            //修改后
            set1.setColor(Color.parseColor("#00a1e9"));
            set1.setCircleColor(Color.parseColor("#00a1e9"));


            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.fade_blue);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mChart.setData(data);
        }
    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setRvData(final List<String> names, List<String> values, List<String> ids, List<String>
            units, final List<String> defaultAddress) {
        //默认设置
        if (names != null && names.size() > 0 && values != null && values.size() > 0 && ids != null && ids.size()
                > 0 && units != null && units.size() > 0 && defaultAddress != null && defaultAddress
                .size() > 0) {
            if (mFirstInto) {
                mFirstInto = false;
                mTvDataAnalyzeName.setText(names.get(0));
                mLayoutManagerCenter = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false);
                mRvCenter.setHasFixedSize(true);
                mLayoutManagerCenter.setAutoMeasureEnabled(true);
                mRvCenter.setLayoutManager(mLayoutManagerCenter);
                mRvAdapterCenter = new DataAnalyzeCenterRvAdapter(getActivity(), names);
                mRvCenter.addItemDecoration(new SpacesItemDecoration(Constants.Define.DATASHOW_CENTER_SPACINGINPIXELS));
                mRvCenter.setAdapter(mRvAdapterCenter);
                //默认第一个日显示
                mSelectAddress = defaultAddress.get(0);
                //默认按天统计
                mDataAnalyzeP.getStatistics(Constants.Define.ANALYZETIMETYPE_DAY, mSelectAddress);
                mRvAdapterCenter.setItemClickListener(new DataAnalyzeCenterRvAdapter.MyItemClickListener() {
                    @Override
                    public void itemClick(View view, int position) {
                        if (names.size() > 0 && defaultAddress.size() > 0) {
                            mTvDataAnalyzeName.setText(names.get(position));
                            String address = defaultAddress.get(position);
                            mDataAnalyzeP.getStatistics(mSelectTimeType, address);
                            mSelectAddress = address;
                        } else {
                            CustomToast.showToast(getActivity(), Constants.Define.SERVERDATAERROR, Toast.LENGTH_SHORT);
                        }
                    }
                });
            }


        } else {
            CustomToast.showToast(getActivity(), Constants.Define.SERVERDATAERROR, Toast.LENGTH_SHORT);
        }


    }

    @Override
    public void setToast(String s) {

    }

    @Override
    public void dissDialog() {

    }


    @Override
    public void setLineChart(List<String> values) {

        if (values.size() > 0) {
            drawLine(values);
        } else {
            mChart.clear();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSettingParameterP.dispose();
    }

    @OnClick({R.id.iv_data_analyze_warning, R.id.iv_data_analyze_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_data_analyze_warning:
                startActivity(new Intent(getActivity(), WarningActivity.class));
                break;
            case R.id.iv_data_analyze_show:
                com.example.shuangxiang.ysvideodemo.common.utils.Utils.replace(getActivity()
                                .getSupportFragmentManager(), R.id.fl_home2,
                        DataShowFragment.class);
                break;

        }
    }
}
