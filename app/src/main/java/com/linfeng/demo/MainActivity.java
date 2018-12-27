package com.linfeng.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;

import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.presenter.EmptyPresenter;
import com.baozi.mvp.templet.TemplateActivity;
import com.baozi.mvp.templet.options.ToolbarOptions;
import com.linfeng.rx_retrofit_network.location.rxbus.RxBus;


public class MainActivity extends TemplateActivity<BasePresenter> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        startService(new Intent(this, SocketService.class));
        EditText et_title = findView(R.id.et_title);
        et_title.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                RxBus.getDefault().post(10086, et_title.getText().toString());
            }
            return false;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    //这里偷懒,就不去单独写个PresenterImpl了
    @Override
    protected BasePresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    public ToolbarOptions getToolbarOptions() {
        return super.getToolbarOptions().setNoBack(true)
                .setToolbarDrawable(R.drawable.shape)
                .setStatusDrawable(R.drawable.shape);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            getToolbarHelper().setTitle("返回了桌面");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
