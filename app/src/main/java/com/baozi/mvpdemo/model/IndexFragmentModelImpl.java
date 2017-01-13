package com.baozi.mvpdemo.model;

import com.baozi.mvpdemo.contract.IndexFragmentContract;
import com.baozi.mvpdemo.location.APIServiceImpl;

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