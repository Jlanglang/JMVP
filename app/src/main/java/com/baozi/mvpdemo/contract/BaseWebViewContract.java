package com.baozi.mvpdemo.contract;

import com.baozi.mvpdemo.ui.view.BaseFragmentView;
import com.gzsll.jsbridge.WVJBWebView;

import java.util.HashMap;

import rx.Observable;

/**
 * @author jlanglang  2016/11/18 9:38
 * @版本 2.0
 * @Change
 * @des ${TODO}
 */
public class BaseWebViewContract {
    public interface View extends BaseFragmentView {
        WVJBWebView getWVJBWebView();

        /**
         * webview加载的url地址
         *
         * @return
         */
        String getUrl();
    }

    public interface Presenter {
        /**
         * 初始化webview
         */
        void initWebView();

        /**
         * 注册交互接口
         */
        void RegisterHandler();
    }

    public interface Model {
        /**
         * 通用接口,直接将data作为string传给h5页面
         *
         * @param url
         * @param params
         * @return
         */
        Observable<String> loadWebViewData(String url, HashMap<String, Object> params);
    }
}