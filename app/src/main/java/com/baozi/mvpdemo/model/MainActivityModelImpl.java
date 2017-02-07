package com.baozi.mvpdemo.model;

import com.baozi.mvpdemo.contract.MainActivityContract;
import com.baozi.mvpdemo.location.APIServiceImpl;
import com.baozi.mvpdemo.location.rxandroid.ModelFilterFactory;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by baozi on 2016/11/24
 */

public class MainActivityModelImpl implements MainActivityContract.Model {

    @Override
    public Observable<String> login(HashMap<String, Object> parmas) {
        return ModelFilterFactory.filter(APIServiceImpl.getInstance().login(parmas));
    }
}