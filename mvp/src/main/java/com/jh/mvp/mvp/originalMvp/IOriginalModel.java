package com.jh.mvp.mvp.originalMvp;

import android.app.Dialog;

import com.jh.mvp.mvp.RequestBean;
import com.jh.mvp.mvp.BaseContract;

import io.reactivex.Observable;

public interface  IOriginalModel  extends BaseContract.IBaseModel {

    <T extends RequestBean>void getData(Observable<T> observable, IOriginalCallBack<T> iOriginalPresenter);

    <T extends RequestBean>void getData(Observable<T> observable, IOriginalCallBack<T> iOriginalPresenter, Dialog dialog);

}
