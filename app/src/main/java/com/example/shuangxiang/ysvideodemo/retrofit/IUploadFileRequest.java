package com.example.shuangxiang.ysvideodemo.retrofit;

import com.example.shuangxiang.ysvideodemo.feedback.bean.FilePath;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IUploadFileRequest {
    @Multipart
    @POST("files")
    Observable<FilePath> uploadFile(@Part("file\"; filename=\"avatar.png\"") RequestBody file);

    @POST("feedbacks")
    Observable<String> submit(@Body RequestBody jsonBody);
}
