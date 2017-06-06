package retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by shuang.xiang on 2017/3/22.
 */

public interface RequestServes {
    @POST("mobile/login")
    Call<ResponseBody> login(@Body User user);

}
