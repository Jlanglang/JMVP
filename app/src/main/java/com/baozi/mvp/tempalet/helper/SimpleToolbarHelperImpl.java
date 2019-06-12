package com.baozi.mvp.tempalet.helper;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baozi.mvp.R;
import com.baozi.mvp.tempalet.options.ToolbarOptions;
import com.baozi.mvp.view.ToolbarView;

/**
 * @author jlanglang  2017/2/21 16:44
 * @版本 2.0
 * @Change
 */

public class SimpleToolbarHelperImpl extends BaseToolBarHelperImpl {
    private TextView mTitleView;
    @ColorInt
    private int mOtherTextColor;
    private int mOtherTextSize;
    private LinearLayout endActions;


    SimpleToolbarHelperImpl(ToolbarView uiView) {
        super(uiView);
    }

    @Override
    public void initToolbar() {
        mTitleView = findView(R.id.tv_title);
        endActions = findView(R.id.ll_end_group);
        ToolbarHelper.SimpleInitToolbar(mToolbarView.getContext(), mToolbar, false);
    }

    @Override
    public void setToolbarOptions(ToolbarOptions options) {
        super.setToolbarOptions(options);
        int mTitleSize = options.getTitleSize();
        int mTitleColor = options.getTitleColor();
        mOtherTextColor = options.getOtherTextColor();
        mOtherTextSize = options.getOtherTextSize();
        if (mOtherTextColor != 0 || mOtherTextSize != 0) {
            notifyToolbarText();
        }
        boolean noBack = options.isNoBack();
        if (mTitleSize != 0) {
            mTitleView.setTextSize(mTitleSize);
        }
        if (mTitleColor != 0) {
            mTitleView.setTextColor(mTitleColor);
        }
        if (!noBack) {
            int backDrawable = options.getBackDrawable();
            if (backDrawable == 0) {
                backDrawable = R.drawable.back;
            }
            setLeading(backDrawable);
        }
    }

    private void notifyToolbarText() {
        int childCount = mToolbar.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = mToolbar.getChildAt(childCount);
            if (childAt instanceof TextView) {
                if (mOtherTextColor != 0) {
                    ((TextView) childAt).setTextColor(mOtherTextColor);
                }
                if (mOtherTextSize != 0) {
                    ((TextView) childAt).setTextSize(mOtherTextSize);
                }
            }
        }
    }

    @Override
    public void setTextSize(int size) {
    }

    @Override
    public void setTitleSize(int size) {
        mTitleView.setTextSize(size);
    }


    public void setTitle(@NonNull String titleView) {
        mTitleView.setText(titleView);
    }

    @Override
    public void setTitle(int titleId) {
        String title = mToolbarView.getContext().getResources().getString(titleId);
        setTitle(title);
    }


    @Override
    public void setCanBack(boolean canBack) {
        super.setCanBack(canBack);
        findView(R.id.ll_start_group).setVisibility(canBack ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setLeading(String leading) {
        TextView view = findView(R.id.tv_start);
        if (TextUtils.isEmpty(leading)) {
            view.setVisibility(View.GONE);
            return;
        }
        view.setVisibility(View.VISIBLE);
        view.setText(leading);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbarView.onBackPressed();
            }
        });
    }

    @Override
    public void setLeading(int leadRes) {
        ImageButton view = findView(R.id.ib_start);
        view.setVisibility(View.VISIBLE);
        view.setImageResource(leadRes);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbarView.onBackPressed();
            }
        });
    }

    @Override
    public void addActions(View view) {
        if (endActions != null && view != null) {
            if (view instanceof TextView) {
                if (mOtherTextColor != 0) {
                    ((TextView) view).setTextColor(mOtherTextColor);
                }
                if (mOtherTextSize != 0) {
                    ((TextView) view).setTextSize(mOtherTextSize);
                }
            }
            endActions.addView(view);
        }
    }

    public void setEndText(String str, View.OnClickListener clickListener) {
        if (endActions != null) {
            TextView view = findView(R.id.tv_start);
            if (str == null) {
                view.setVisibility(View.GONE);
                return;
            }
            view.setVisibility(View.VISIBLE);
            view.setText(str);
            view.setOnClickListener(clickListener);
        }
    }

    /**
     * @param drawable      设置0则隐藏
     * @param clickListener 点击监听
     */
    public void setEndImage(int drawable, View.OnClickListener clickListener) {
        if (endActions != null) {
            ImageButton view = findView(R.id.tv_start);
            if (drawable == 0) {
                view.setVisibility(View.GONE);
                return;
            }
            view.setVisibility(View.VISIBLE);
            view.setImageResource(drawable);
            view.setOnClickListener(clickListener);
        }
    }
}
