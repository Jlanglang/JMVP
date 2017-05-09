package com.xingdt.technician.model;
import com.xingdt.technician.bean.QueryOpertionBean;
import com.xingdt.technician.contract.RepairOrderAddServiceFragmentContract;
import com.xingdt.technician.location.ApiServiceImpl;
import com.xingdt.technician.location.rxandroid.ModelFilteredFactory;

import java.util.List;

import rx.Observable;

/**
* Created by Administrator on 2016/11/30
*/

public class RepairOrderAddServiceFragmentModelImpl implements RepairOrderAddServiceFragmentContract.Model{

    @Override
    public Observable<List<QueryOpertionBean>> queryOpertion(String token,String operationdesc) {
        return ModelFilteredFactory.compose(ApiServiceImpl.getInstance().queryOpertion(token,operationdesc));
    }
}