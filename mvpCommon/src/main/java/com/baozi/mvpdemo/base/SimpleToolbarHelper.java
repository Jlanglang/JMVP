package com.baozi.mvpdemo.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baozi.mvpdemo.R;

/**
 * @author jlanglang  2017/2/21 16:44
 * @版本 2.0
 * @Change
 */

public class SimpleToolbarHelper extends ToolbarHelper {

    public SimpleToolbarHelper(Toolbar toolbar, Context context) {
        super(toolbar, context);
    }

    @Override
    public void initToolbarChilds() {

    }

    @Override
    public void setTitle(String title) {
        if (mToolbar == null) {
            return;
        }
//        if (mPresenter.isMaterialDesign()) {
//            getSupportActionBar().setTitle(title);
//        } else {
        ((TextView) mToolbar.findViewById(R.id.tv_title))
                .setText(title);
//        }
    }

    @Override
    public void setTitle(int titleId) {
        String title = context.getResources().getString(titleId);
        setTitle(title);
    }

    protected void setLeftTextString(String str) {
//        if (mToolbar == null || mPresenter.isMaterialDesign()) {
//            return;
//        }
//        ((ImageButton) mToolbar.findViewById(R.id.tv_left)).setText(str);


    }

    protected void setLeftTextString(@StringRes int strId) {
//        String string = getResources().getString(strId);
//        setLeftTextString(string);
    }

    protected void setLeftTextDrawable(Drawable drawable) {
//        if (mToolbar == null || mPresenter.isMaterialDesign()) {
//            return;
//        }
        ImageButton tv_left = (ImageButton) mToolbar.findViewById(R.id.tv_left);
        tv_left.setBackground(drawable);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBack();
            }
        });

    }

    protected void setLeftTextDrawable(@DrawableRes int drawableId) {
//        setLeftTextDrawable(ContextCompat.getDrawable(this, drawableId));
    }

    protected void setRightTextString(String str) {
//        if (mToolbar == null || mPresenter.isMaterialDesign()) {
//            return;
//        }
//        ((TextView) mToolbar.findViewById(R.id.tv_right))
//                .setText(str);
    }

    protected void setRightTextString(int strId) {
//        String string = getResources().getString(strId);
//        setRightTextString(string);
    }

    protected void setRightTextDrawable(Drawable drawable) {
//        if (mToolbar == null || mPresenter.isMaterialDesign()) {
//            return;
//        }
//        mToolbar.findViewById(R.id.tv_right)
//                .setBackground(drawable);
    }

    protected void setRightTextDrawable(int drawableId) {
//        setRightTextDrawable(ContextCompat.getDrawable(this, drawableId));
    }
}
