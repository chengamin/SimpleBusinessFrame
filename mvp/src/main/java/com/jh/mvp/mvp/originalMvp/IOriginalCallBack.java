package com.jh.mvp.mvp.originalMvp;

import com.jh.mvp.mvp.RequestBean;
import com.jh.mvp.mvp.BaseContract;

import io.reactivex.disposables.Disposable;

public interface IOriginalCallBack<T extends RequestBean> extends BaseContract.IBasePresenter {

    void onSubscribe(Disposable d);

    void onNext(T t);

    void onError(Throwable e);

    void onComplete();

}
