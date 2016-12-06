package com.baozi.mvpdemp.model;

import com.baozi.mvpdemp.contract.MainActivityContract;
import com.baozi.mvpdemp.location.APIServiceImpl;
import com.baozi.mvpdemp.location.rxandroid.ModleFilterFactory;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by baozi on 2016/11/24
 */

public class MainActivityModelImpl implements MainActivityContract.Model {

    @Override
    public Observable<String> login(HashMap<String, Object> parmas) {
        return ModleFilterFactory.filter(APIServiceImpl.getInstance().login(parmas));
    }
}