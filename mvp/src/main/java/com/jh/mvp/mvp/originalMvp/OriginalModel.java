package com.jh.mvp.mvp.originalMvp;

import android.app.Dialog;

import com.jh.mvp.mvp.RequestBean;
import com.jh.mvp.mvp.BaseModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class OriginalModel extends BaseModel implements IOriginalModel {

    @Override
    public <T extends RequestBean> void getData(Observable<T> observable, IOriginalCallBack<T> iOriginalPresenter) {
        initObservable(observable).subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                iOriginalPresenter.onSubscribe(d);
            }

            @Override
            public void onNext(T t) {
                iOriginalPresenter.onNext(t);
                onComplete();
            }

            @Override
            public void onError(Throwable e) {
                iOriginalPresenter.onError(e);
                onComplete();
            }

            @Override
            public void onComplete() {
                iOriginalPresenter.onComplete();
            }
        });
    }

    @Override
    public <T extends RequestBean> void getData(Observable<T> observable, IOriginalCallBack<T> iOriginalPresenter, Dialog dialog) {
        initObservable(observable).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
                if (dialog != null) {
                    dialog.show();
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                iOriginalPresenter.onSubscribe(d);
            }

            @Override
            public void onNext(T t) {
                iOriginalPresenter.onNext(t);
                onComplete();
            }

            @Override
            public void onError(Throwable e) {
                iOriginalPresenter.onError(e);
                onComplete();
            }

            @Override
            public void onComplete() {
                if (dialog != null) {
                    dialog.dismiss();
                }
                iOriginalPresenter.onComplete();
            }
        });
    }
}
