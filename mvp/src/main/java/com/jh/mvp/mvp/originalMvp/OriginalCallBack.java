package com.jh.mvp.mvp.originalMvp;

import android.content.Context;
import android.util.Log;

import com.jh.mvp.mvp.RequestBean;
import com.jh.mvp.mvp.ExceptionHandler;
import com.jh.utils.ToastUtils;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;

public abstract class OriginalCallBack<T extends RequestBean> implements IOriginalCallBack<T> {

    private WeakReference<Context> weakReference = null;

    public OriginalCallBack(Context context) {
        this.weakReference = new WeakReference<Context>(context);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        if (null != e) {
            if (e instanceof Exception) {
                //访问获得对应的Exception
                ExceptionHandler.ResponeThrowable responeThrowable = ExceptionHandler.handleException(e);
                if (null != weakReference) {
                    Log.e("BaseHttp","错误码:"+responeThrowable.code+"错误信息:"+responeThrowable.message);
                    ToastUtils.getInstance(weakReference.get()).showShort(responeThrowable.message);
                }
            }
        }
    }

    @Override
    public void onComplete() {
    }
}
