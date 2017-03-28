package com.baozi.mvp.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baozi.mvp.R;
import com.baozi.mvp.ui.UIView;

/**
 * @author jlanglang  2017/2/21 16:44
 * @版本 2.0
 * @Change
 */

class TempletDefuatlToolbarHelperImpl extends BaseToolBarHelperImpl {
    private TextView mLeftTextView;
    private TextView mRightTextView;
    private ImageButton mLeftButton;
    private ImageButton mRightButton;
    private TextView mTitleView;
    private String mLeftText;
    private String mRightText;
    private Drawable mLeftDrawable;
    private Drawable mRightDrawable;


    TempletDefuatlToolbarHelperImpl(UIView uiView, View rootView, @LayoutRes int toolbar) {
        super(uiView, rootView, toolbar);
    }

    @Override
    public void initToolbar() {
//        mAppBarLayout = (AppBarLayout) mRootView.findViewById(R.id.app_bar);
//        mAppBarLayout.removeAllViews();
//        View inflate = LayoutInflater.from(mUIView.getContext()).inflate(mToolbarLayout, mAppBarLayout, true);
//
//        mToolbar = (Toolbar) inflate.findViewById(R.id.tl_costom);
//        mUIView.setSupportActionBar(mToolbar);

        mLeftTextView = (TextView) mToolbar.findViewById(R.id.tv_left);
        mRightTextView = (TextView) mToolbar.findViewById(R.id.tv_right);
        mLeftButton = (ImageButton) mToolbar.findViewById(R.id.ib_left);
        mRightButton = (ImageButton) mToolbar.findViewById(R.id.ib_right);
        mTitleView = (TextView) mToolbar.findViewById(R.id.tv_title);

        mToolbar.setContentInsetsAbsolute(0, 0);
        setLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUIView.onBack();
            }
        });
        ActionBar supportActionBar = mUIView.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 应该保证在调用Activity.setSupportActionBar()之后使用.
     *
     * @param isMaterialDesign
     */
    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
        super.setMaterialDesignEnabled(isMaterialDesign);
        int visibility = isMaterialDesign ? View.GONE : View.VISIBLE;
        if (null != mRightText) {
            mRightTextView.setVisibility(visibility);
        }
        if (null != mRightButton) {
            mRightButton.setVisibility(visibility);
        }
    }

    public void setTitle(@NonNull String titleView) {
        mTitleView.setText(titleView);
    }

    @Override
    public void setTitle(int titleId) {
        String title = mUIView.getResources().getString(titleId);
        setTitle(title);
    }


    @Override
    public void setLeftText(@NonNull String str, View.OnClickListener clickListener) {
        mLeftDrawable = null;
        mLeftText = str;
        mLeftTextView.setVisibility(View.VISIBLE);
        mLeftTextView.setText(str);
        mLeftTextView.setOnClickListener(clickListener);
    }

    @Override
    public void setLeftText(@StringRes int strId, View.OnClickListener clickListener) {
        String string = mUIView.getResources().getString(strId);
        setLeftText(string, clickListener);
    }

    @Override
    public void setLeftButton(@NonNull Drawable drawable, View.OnClickListener clickListener) {
        mLeftText = null;
        mLeftDrawable = drawable;
        mLeftButton.setVisibility(View.VISIBLE);
        mLeftButton.setImageDrawable(drawable);
        mLeftButton.setOnClickListener(clickListener);
    }

    @Override
    public void setLeftButton(@DrawableRes int drawableId, View.OnClickListener clickListener) {
        setLeftButton(ContextCompat.getDrawable(mUIView.getContext(), drawableId), clickListener);
    }

    @Override
    public void setRightText(@NonNull String str, View.OnClickListener clickListener) {
        mRightDrawable = null;
        mRightText = str;
//        mRightButton.setVisibility(View.GONE);
        mRightTextView.setVisibility(View.VISIBLE);
        mRightTextView.setText(str);
        mRightTextView.setOnClickListener(clickListener);
    }

    @Override
    public void setRightText(int strId, View.OnClickListener clickListener) {
        String string = mUIView.getResources().getString(strId);
        setRightText(string, clickListener);
    }

    @Override
    public void setRightButton(@NonNull Drawable drawable, View.OnClickListener clickListener) {
        mRightText = null;
        mRightDrawable = drawable;
//        mRightTextView.setVisibility(View.GONE);
        mRightButton.setVisibility(View.VISIBLE);
        mRightButton.setImageDrawable(drawable);
    }

    @Override
    public void setRightButton(int drawableId, View.OnClickListener clickListener) {
        setRightButton(ContextCompat.getDrawable(mUIView.getContext(), drawableId), clickListener);
    }
}
