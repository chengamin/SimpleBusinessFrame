package com.jh.SimpleBusinessFrame.login;

import android.app.ProgressDialog;
import com.jh.mvp.http.RetrofitWrapper;
import com.jh.mvp.mvp.BasePresenter;
import com.jh.mvp.mvp.originalMvp.OriginalCallBack;
import com.jh.mvp.mvp.originalMvp.OriginalModel;

import io.reactivex.Observable;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView, OriginalModel> {

    private LoginService loginService = null;

    public void login(String username, String password) {
        //如果用的是同一地址,代码可以直接提取到框架中的basePresenter中
        loginService = RetrofitWrapper.getInstance("替换为自己的").createService(LoginService.class);
        Observable<LoginBean> observable = loginService.login(username, password);

        ProgressDialog progressDialog = new ProgressDialog(c);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在登录");
        m.getData(observable, new OriginalCallBack<LoginBean>(c) {
            @Override
            public void onNext(LoginBean loginBean) {

            }
        }, progressDialog);
    }

}
