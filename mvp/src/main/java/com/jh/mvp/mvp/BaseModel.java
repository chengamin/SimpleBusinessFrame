package com.jh.mvp.mvp;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class BaseModel implements BaseContract.IBaseModel {

    private PublishSubject<EventLife> publishSubject = null;

    @Override
    public void setLife(PublishSubject<EventLife> publishSubject) {
        this.publishSubject = publishSubject;
    }

    public <T extends RequestBean> Observable<T> initObservable(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.takeUntil(publishSubject.skipWhile(new Predicate<EventLife>() {
                    @Override
                    public boolean test(EventLife eventLife) throws Exception {
                        if (eventLife == EventLife.DESTROY) {
                            return true;
                        }
                        return false;
                    }
                }));
            }
        });
    }
}
