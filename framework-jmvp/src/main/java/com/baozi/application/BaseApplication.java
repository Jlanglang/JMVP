package com.baozi.application;

import android.app.Application;

import com.baozi.location.JApiImpl;
import com.baozi.mvp.MVPManager;
import com.baozi.mvp.templet.options.ContentOptions;
import com.baozi.mvp.templet.options.ToolbarOptions;
import com.bumptech.glide.request.RequestOptions;
import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.location.APICallBack;
import com.linfeng.rx_retrofit_network.location.SimpleParams;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;
import com.linfeng.rx_retrofit_network.location.rxandroid.JsonParesTransformer;
import com.linfeng.rx_retrofit_network.location.rxandroid.SimpleObserver;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //比如www.baidu.com
        //3个参数,hostUrl,成功码,context
        NetWorkManager.init("你的hostUrl", 0, this);
        NetWorkManager.setApiCallBack(new APICallBack() {
            @Override
            public String callback(int code, BaseResponse baseResponse) {
                //接口发生异常的回调.
                return null;//返回什么弹什么.
            }
        });
        //请求的例子
        //比如www.baidu.com/api/a
        JApiImpl.getJApi().BasePost("api/a",
                SimpleParams.create()//请求参数集合
                        .putP("key", "")
                        .putP("key1", "")
                        .putP("key2", "")
                        .putP("key3", "")
        )
                .compose(new JsonParesTransformer<>(String.class))//这里构造传入你需要解析的javabaen的class
                .subscribe(new SimpleObserver<String>() {
                    @Override
                    public void call(String s) {
                        //成功的回调
                    }
                });


        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .dontAnimate()
                .dontTransform()
                .autoClone();

        MVPManager.setContentOptions(
                ContentOptions.create()
                        .setEmptyLayout(0)
                        .setErrorLayout(0)
                        .setLoadingLayout(0)
                        .setOpenLoading(false)
        );
        MVPManager.setToolbarOptions(
                ToolbarOptions.Create()
                        .setToolbarColor(0)
                        .setToolbarDrawable(0)
                        .setToolbarHeight(0)
                        .setToolbarLayout(0)
                        .setBackDrawable(0)
                        .setNoBack(false)
        );

    }
}
