package com.baozi.mvpdemp.model;

import com.baozi.mvpdemp.contract.IndexFragmentContract;
import com.baozi.mvpdemp.location.APIService;
import com.baozi.mvpdemp.location.APIServiceImpl;
import com.baozi.mvpdemp.location.ModleFilterFactory;

import rx.Observable;

/**
 * Created by baozi on 2016/11/28
 */

public class IndexFragmentModelImpl implements IndexFragmentContract.Model {
    public Observable<String> getData() {
        return ModleFilterFactory.filter(APIServiceImpl.getInstance().getData());
    }
}