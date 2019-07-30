package com.jh.mvp.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseContract.IBaseView {

    protected View rootView = null;
    protected Context context = null;
    protected P p = null;

    private Unbinder unbinder = null;
    private PublishSubject<EventLife> lifePublishSubject = PublishSubject.create();

    @Override
    public PublishSubject<EventLife> getLife() {
        return lifePublishSubject;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(setLayout(), container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        lifePublishSubject.onNext(EventLife.CREATE);
        super.onActivityCreated(savedInstanceState);
        initBinder();
        initPresenter();
        init(savedInstanceState);
    }

   /* @Override
    public void onStart() {
        lifePublishSubject.onNext(EventLife.START);
        super.onStart();
    }

    @Override
    public void onResume() {
        lifePublishSubject.onNext(EventLife.RESUME);
        super.onResume();
    }

    @Override
    public void onPause() {
        lifePublishSubject.onNext(EventLife.RESUME);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifePublishSubject.onNext(EventLife.STOP);
        super.onStop();
    }
*/

    @Override
    public void onDestroyView() {
        lifePublishSubject.onNext(EventLife.DESTROY);
        super.onDestroyView();
        if (p != null) {
            p.detachWindow();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    private void initBinder() {
        unbinder = ButterKnife.bind(this, rootView);
    }

    private void initPresenter() {
        p = setPresenter();
        if (null != p) {
            p.attachWindow(this, setModel());
            p.setContext(context);
        }
    }

    protected abstract int setLayout();

    protected abstract void init(@Nullable Bundle savedInstanceState);

    protected abstract P setPresenter();

    protected abstract BaseModel setModel();
}
