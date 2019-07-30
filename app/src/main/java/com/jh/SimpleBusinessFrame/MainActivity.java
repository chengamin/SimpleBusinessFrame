package com.jh.SimpleBusinessFrame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jh.SimpleBusinessFrame.login.LoginContract;
import com.jh.SimpleBusinessFrame.login.LoginPresenter;
import com.jh.mvp.mvp.BaseActivity;
import com.jh.mvp.mvp.BaseModel;
import com.jh.mvp.mvp.originalMvp.OriginalModel;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView {

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        p.login("1213", "1234");
    }

    @Override
    protected LoginPresenter setPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected BaseModel setModel() {
        return new OriginalModel();
    }


   @OnClick({R.id.tv_tv})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_tv:

                break;
        }
    }
}
