package com.jh.mvp.mvp;

import android.content.Context;

public class BasePresenter<V extends BaseContract.IBaseView,M extends BaseContract.IBaseModel> implements BaseContract.IBasePresenter {


    protected V v = null;
    protected M m = null;
    protected Context c =  null;

    public void attachWindow(V v,M m){
        this.v = v;
        this.m = m;
        this.m.setLife(this.v.getLife());
    }

    public void setContext(Context c){
        this.c = c;
    }

    public void detachWindow(){
        this.v = null;
        this.m = null;
        this.c = null;
    }
}
