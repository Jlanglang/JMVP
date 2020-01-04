package com.baozi.demo.ui.at;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.baozi.demo.R;
import com.baozi.demo.persenter.DemoPresenter;
import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.tempalet.TemplateActivity;
import com.baozi.mvp.tempalet.options.ToolbarOptions;


@JView(p = DemoPresenter.class, layout = R.layout.at_template)
public class TemplateAt extends TemplateActivity<DemoPresenter> {
    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        getToolbarHelper().setTitle("我是模板Activity")
                .setLeading("关闭");
        WebView viewById = findViewById(R.id.wb_content);
        viewById.setWebViewClient(new WebViewClient());
        WebSettings webSetting = viewById.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
//        webSetting.setAppCacheMaxSize(1024 * 1024 * 8);
        webSetting.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSetting.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSetting.setAllowFileAccess(true);
        webSetting.setAppCacheEnabled(true);
        String appCachePath = getApplication().getCacheDir().getAbsolutePath();
        webSetting.setAppCachePath(appCachePath);
        webSetting.setDatabaseEnabled(true);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        viewById.loadUrl("https://smlt.tsign.cn/n8gmKAfuuemC");
    }

    @Override
    public ToolbarOptions getToolbarOptions() {
        return super.getToolbarOptions()
                .setNoBack(true)
                .setToolbarColor(0)
                .setElevation(0);
    }
}
