package com.baozi.demo.ui.at;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baozi.demo.R;
import com.baozi.demo.persenter.MainPresenter;
import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.tempalet.TemplateActivity;
import com.baozi.mvp.tempalet.options.ContentOptions;
import com.bumptech.glide.Glide;

@JView(layout = R.layout.at_load, p = MainPresenter.class, openLoading = true)
public class LoadingAt extends TemplateActivity<MainPresenter> {

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        getLoadHelper().showLoading();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getLoadHelper().showError();
                    }
                });
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getLoadHelper().showSuccess();
                    }
                });
            }
        }.start();
    }

    @Override
    protected View wrapperContentView(View view) {
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((ViewGroup) view).addView(imageView, 0);
        Glide.with(this).load("file:///android_asset/a.gif").into(imageView);
        return super.wrapperContentView(view);
    }

    @Override
    public ContentOptions getContentOptions() {
        return super.getContentOptions()
                .setEmptyLayout(R.layout.empty_layout);
    }
}
