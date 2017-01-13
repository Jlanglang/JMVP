package com.baozi.mvpdemo.model;

import com.baozi.mvpdemo.contract.BaseWebViewContract;
import com.baozi.mvpdemo.location.APIServiceImpl;
import com.baozi.mvpdemo.location.rxandroid.ModelFilterFactory;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/18
 */

public class BaseWebViewModelImpl implements BaseWebViewContract.Model {

    @Override
    public Observable<String> loadWebViewData(String url, HashMap<String,Object> params) {
        return ModelFilterFactory.compose(APIServiceImpl.getInstance().loadWebViewData(url, params));
    }
}