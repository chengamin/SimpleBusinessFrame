package com.jh.mvp.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseContract.IBaseView {

    protected P p = null;
    private Unbinder unbinder = null;
    private PublishSubject<EventLife> publishSubject = PublishSubject.create();

    @Override
    public PublishSubject<EventLife> getLife() {
        return publishSubject;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        publishSubject.onNext(EventLife.CREATE);
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initBinder();
        initPresenter();
        init(savedInstanceState);
    }


   /* @Override
    protected void onStart() {
        publishSubject.onNext(EventLife.START);
        super.onStart();
    }

    @Override
    protected void onResume() {
        publishSubject.onNext(EventLife.RESUME);
        super.onResume();
    }

    @Override
    protected void onPause() {
        publishSubject.onNext(EventLife.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        publishSubject.onNext(EventLife.STOP);
        super.onStop();
    }*/

    @Override
    protected void onDestroy() {
        publishSubject.onNext(EventLife.DESTROY);
        super.onDestroy();
        if (p != null) {
            p.detachWindow();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    private void initBinder() {
        unbinder = ButterKnife.bind(this);
    }

    private void initPresenter() {
        p = setPresenter();
        if (null != p) {
            p.attachWindow(this, setModel());
            p.setContext(this);
        }
    }

    protected abstract int setLayout();

    protected abstract void init(@Nullable Bundle savedInstanceState);

    protected abstract P setPresenter();

    protected abstract BaseModel setModel();
}
