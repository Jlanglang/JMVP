package com.baozi.mvpdemo.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.ui.view.UIView;

/**
 * @author jlanglang  2017/2/21 16:44
 * @版本 2.0
 * @Change
 */

public class DefuatlToolbarHelperImplV1 extends BaseToolBarHelperImpl {
    private TextView mLeftText;
    private TextView mRightText;
    private ImageButton mLeftButton;
    private ImageButton mRightButton;
    private TextView mTitle;

    public DefuatlToolbarHelperImplV1(UIView uiView, @LayoutRes int toolbar) {
        super(uiView, toolbar);
    }

    @Override
    public void initToolbar() {
        mLeftText = (TextView) mToolbar.findViewById(R.id.tv_left);
        mRightText = (TextView) mToolbar.findViewById(R.id.tv_right);
        mLeftButton = (ImageButton) mToolbar.findViewById(R.id.ib_left);
        mRightButton = (ImageButton) mToolbar.findViewById(R.id.ib_right);
        mTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        //默认无边距
        mToolbar.setContentInsetsAbsolute(0, 0);
        setLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUIView.onBack();
            }
        });
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {

    }

    @Override
    public void setTitle(@NonNull String title) {
        if (mToolbar != null) {
            mTitle.setText(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        String title = mUIView.getResources().getString(titleId);
        setTitle(title);
    }


    @Override
    public void setLeftText(@NonNull String str, View.OnClickListener clickListener) {
        mLeftButton.setVisibility(View.GONE);
        mLeftText.setVisibility(View.VISIBLE);
        mLeftText.setText(str);
        mLeftText.setOnClickListener(clickListener);
    }

    @Override
    public void setLeftText(@StringRes int strId, View.OnClickListener clickListener) {
        String string = mUIView.getResources().getString(strId);
        setLeftText(string, clickListener);
    }

    @Override
    public void setLeftButton(@NonNull Drawable drawable, View.OnClickListener clickListener) {
        if (mToolbar != null) {
            mLeftText.setVisibility(View.GONE);
            mLeftButton.setVisibility(View.VISIBLE);
            mLeftButton.setImageDrawable(drawable);
            mLeftButton.setOnClickListener(clickListener);
        }
    }

    @Override
    public void setLeftButton(@DrawableRes int drawableId, View.OnClickListener clickListener) {
        setLeftButton(ContextCompat.getDrawable(mUIView.getContext(), drawableId), clickListener);
    }

    @Override
    public void setRightText(@NonNull String str, View.OnClickListener clickListener) {
        if (mToolbar != null) {
            mRightButton.setVisibility(View.GONE);
            mRightText.setVisibility(View.VISIBLE);
            mRightText.setText(str);
            mRightText.setOnClickListener(clickListener);
        }
    }

    @Override
    public void setRightText(int strId, View.OnClickListener clickListener) {
        String string = mUIView.getResources().getString(strId);
        setRightText(string, clickListener);
    }

    @Override
    public void setRightButton(@NonNull Drawable drawable, View.OnClickListener clickListener) {
        if (mToolbar != null) {
            mRightText.setVisibility(View.GONE);
            mRightButton.setVisibility(View.VISIBLE);
            mRightButton.setImageDrawable(drawable);
        }

    }

    @Override
    public void setRightButton(int drawableId, View.OnClickListener clickListener) {
        setRightButton(ContextCompat.getDrawable(mUIView.getContext(), drawableId), clickListener);
    }
}
