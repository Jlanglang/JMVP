package com.baozi.mvpdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;

import com.baozi.mvpdemo.R;


/**
 * Created by xiaoz on 15/10/20.
 */
public class WebViewWithProgress extends WebView {

    private ProgressBar mProgressBar;
    private OnTitleReadyListener mOnTitleReadyListener;
    private boolean mShowProgress;

    public WebViewWithProgress(Context context) {
        this(context, null);
    }

    public WebViewWithProgress(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.webViewStyle);
    }

    public WebViewWithProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProperRatingBar);
        mShowProgress = a.getBoolean(R.styleable.WebViewWithProgress_show_progresss, false);
        init();
    }

    @SuppressWarnings("ALL")
    private void init() {
        initProgressBar();
        if (mShowProgress){
            setWebChromeClient(new WebClient());
        }else{
            mProgressBar.setVisibility(GONE);
            setWebChromeClient(new WebChromeClient());
        }
        // 启用javascript
        getSettings().setJavaScriptEnabled(true);
        getSettings().setUseWideViewPort(true);

        //是否可以缩放
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getSettings().setDisplayZoomControls(false);
        }
    }

    private void initProgressBar() {
        mProgressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleHorizontal);
        AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 6, 0, 0
        );
        mProgressBar.setLayoutParams(params);

        Drawable drawable = getResources().getDrawable(R.drawable.progress_bar_horizontal_states);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        AbsoluteLayout.LayoutParams lp = (AbsoluteLayout.LayoutParams) mProgressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        mProgressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public class WebClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mOnTitleReadyListener != null) {
                mOnTitleReadyListener.onTitleReady(title);
            }
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                if (mProgressBar.getVisibility() == GONE) {
                    mProgressBar.setVisibility(VISIBLE);
                }
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    public interface OnTitleReadyListener {
        void onTitleReady(String title);
    }

    public void setOnTitleReadyListener(OnTitleReadyListener onTitleReadyListener) {
        mOnTitleReadyListener = onTitleReadyListener;
    }
}
