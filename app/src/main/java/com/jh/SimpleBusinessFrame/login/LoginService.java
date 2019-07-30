package com.jh.SimpleBusinessFrame.login;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login.html")
    @FormUrlEncoded
    Observable<LoginBean> login(@Field("username")String username, @Field("password")String password);

}
