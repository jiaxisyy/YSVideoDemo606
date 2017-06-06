package retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.Body;

/**
 * Created by shuang.xiang on 2017/3/22.
 */

public class RetrofitActivity extends Activity implements RequestServes{


    @BindView(R.id.btn_get)
    Button mBtnGet;
    @BindView(R.id.tv_callback)
    TextView mTvCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
        initSomeThings();
    }


    private void initSomeThings() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.test.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();





    }

    @OnClick({R.id.btn_get, R.id.tv_callback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:

                break;
            case R.id.tv_callback:
                break;
        }
    }

    @Override
    public Call<ResponseBody> login(@Body User user) {


        return null;
    }
}
