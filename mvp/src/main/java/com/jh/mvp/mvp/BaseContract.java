package com.jh.mvp.mvp;

import io.reactivex.subjects.PublishSubject;

public class BaseContract {

    public interface IBaseView{
        PublishSubject<EventLife> getLife();
    }

    public interface IBaseModel{
        void setLife(PublishSubject<EventLife> publishSubject);
    }

    public interface IBasePresenter{
    }

}
