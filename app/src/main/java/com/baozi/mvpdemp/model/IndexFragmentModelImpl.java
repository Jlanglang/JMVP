package com.baozi.mvpdemp.model;

import com.baozi.mvpdemp.contract.IndexFragmentContract;
import com.baozi.mvpdemp.location.APIServiceImpl;
import com.baozi.mvpdemp.location.rxandroid.ModleFilterFactory;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by baozi on 2016/11/28
 */

public class IndexFragmentModelImpl implements IndexFragmentContract.Model {

    @Override
    public Observable login(HashMap<String, Object> parmas) {
        return ModleFilterFactory.filter(APIServiceImpl.getInstance().login(parmas));
    }
}