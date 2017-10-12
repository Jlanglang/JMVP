package com.linfeng.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvp.base.TempletActivity;
import com.baozi.mvp.helper.ToolbarHelper;
import com.baozi.mvp.presenter.BasePresenter;
import com.linfeng.demo.contract.MainContract;

import java.io.File;
import java.util.ArrayList;

import rx.Observable;
import rx.functions.Func1;

import static com.linfeng.demo.R.layout.activity_main;

public class MainActivity extends TempletActivity<BasePresenter>
        implements MainContract.View {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        mPresenter.onAttch(this);
        Observable.just(new ArrayList<String>())
                .map(new Func1<ArrayList<String>, ArrayList<File>>() {
                    @Override
                    public ArrayList<File> call(ArrayList<String> objects) {
                        ArrayList<File> files = new ArrayList<>();
                        for (int i = 0; i < objects.size(); i++) {
                            File file = new File(objects.get(i));
                            files.add(file);
                        }
                        return files;
                    }
                }).flatMap(new Func1<ArrayList<File>, Observable<?>>() {
            @Override
            public Observable<?> call(ArrayList<File> files) {
                return null;
            }
        });
        super.onCreate(savedInstanceState, persistentState);

    }

    @NonNull
    @Override
    protected View onCreateContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(activity_main, null);
    }


    //这里偷懒,就不去单独写个PresenterImpl了
    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter<MainContract.View>() {
            @Override
            public void onCreate() {
                mView.getToolbarHelper().setTitle("首页");
                mView.getToolbarHelper().setRightText("213", null);
                //设置滑动效果
                mView.getToolbarHelper().setScrollFlag(R.id.tl_custom, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
            }

            @Override
            public void initData() {

            }

            @Override
            public void cancelNetWork() {

            }
        };
    }

    //重写该方法,设置ToolbarLayout
    @Override
    public int initToolbarLayout() {
        return ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL;
    }


    @Override
    public boolean isMaterialDesign() {
        return true;
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
