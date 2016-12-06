package com.baozi.mvpdemp.model;

import com.baozi.mvpdemp.contract.MainActivityContract;
import com.baozi.mvpdemp.location.APIServiceImpl;
import com.baozi.mvpdemp.location.ModleFilterFactory;

import rx.Observable;

/**
 * Created by baozi on 2016/11/24
 */

public class MainActivityModelImpl implements MainActivityContract.Model {

    @Override
    public Observable<String> getData() {
        return ModleFilterFactory.filter(APIServiceImpl.getInstance().getData());
    }
}