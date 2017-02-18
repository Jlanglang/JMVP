package com.baozi.mvpdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.contract.SimpleWebViewFragmentContract;
import com.baozi.mvpdemo.presenter.BaseWebViewPresenterImpl;
import com.baozi.mvpdemo.widget.WebViewWithProgress;
import com.gzsll.jsbridge.WVJBWebView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author jlanglang  2016/8/12 17:21
 * @版本 2.0  简单展示通用的webview
 */
public abstract class SimpleWebViewFragment<T extends BaseWebViewPresenterImpl> extends BaseWebViewFragment<T>
        implements SimpleWebViewFragmentContract.View {

    @Bind(R.id.ib_back)
    ImageButton mIbBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.ib_set)
    ImageButton mIbSet;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.tl_custom)
    Toolbar mTlCustom;
    @Bind(R.id.wv_content)
    WebViewWithProgress mWvContent;

    @Nullable
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(mContext, R.layout.fragment_webview_simple, null);
        ButterKnife.bind(this, inflate);
        setToolbar(mTlCustom);
        return inflate;
    }

    @Override
    protected abstract T initPresenter();

    @Override
    public void setTitle(String str) {
        mTvTitle.setText(str);
    }

    @Override
    public void setTitle(@StringRes int res) {
        mTvTitle.setText(res);
    }

    @Override
    public void setToolbarImage(int imageResource) {
        if (imageResource != 0) {
            mTvRight.setBackgroundResource(imageResource);
            mTvRight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setToolbarRightText(String str) {
        if (TextUtils.isEmpty(str)) {
            mTvRight.setVisibility(View.GONE);
        } else {
            mTvRight.setText(str);
            mTvRight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initListener() {
        mIbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
    }

    @Override
    public WVJBWebView getWVJBWebView() {
        return mWvContent;
    }

}
