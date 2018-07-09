package com.linfeng.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.KeyEvent;
import android.widget.Toast;

import com.baozi.frame.JBasePresenter;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.templet.TempletActivity;
import com.baozi.mvp.templet.helper.ToolbarHelper;
import com.linfeng.demo.contract.MainContract;
import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.location.APIExceptionCallBack;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;
import com.linfeng.rx_retrofit_network.location.rxandroid.JsonParesTransformer;
import com.linfeng.rx_retrofit_network.location.rxandroid.SimpleObserver;

import io.reactivex.Observable;


public class MainActivity extends TempletActivity<BasePresenter>
        implements MainContract.View {


    @NonNull
    @Override
    protected int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    //这里偷懒,就不去单独写个PresenterImpl了
    @Override
    protected BasePresenter initPresenter() {
        return new JBasePresenter<MainContract.View>() {
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
                NetWorkManager.putErrorMsg(NullPointerException.class, "数据为空");
                NetWorkManager.putApiCallback(new APIExceptionCallBack() {
                    @Override
                    public String callback(BaseResponse baseResponse) {
                        Toast.makeText(mView.getContext(), "错误100", Toast.LENGTH_SHORT).show();
                        //返回null,则只处理code,不弹消息.
                        return null;
                    }
                }, 100);
                //假数据
                BaseResponse<String> objectBaseResponse = new BaseResponse<>();
                objectBaseResponse.setData("");
                objectBaseResponse.setCode(100);

                Observable.just(objectBaseResponse)
                        .compose(new JsonParesTransformer<>(Object.class))
                        .subscribe(new SimpleObserver<Object>(mCompositeDisposable) {
                            @Override
                            public void call(Object o) {

                            }
                        });
            }

            @Override
            public void cancelNetWork() {

            }
        };
    }


    //重写该方法,设置ToolbarLayout
    @Override
    public int getToolbarLayout() {
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
