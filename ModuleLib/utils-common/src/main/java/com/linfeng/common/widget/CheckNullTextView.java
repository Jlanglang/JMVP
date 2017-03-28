package com.linfeng.common.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Administrator on 2016/3/15.
 */
public class CheckNullTextView extends TextView {


    public CheckNullTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param str     传入原始参数,判断是否为空
     * @param defalut 传入修改后的参数,如不需要对原始参数进行修改,则传""
     */
    public void setText(String str, String defalut) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(defalut)) {
            setVisibility(View.GONE);
        } else {
            setText(TextUtils.isEmpty(str) ? defalut : str, BufferType.NORMAL);
            setVisibility(View.VISIBLE);
        }
    }

    public void setText(String text) {
        if (text == null) {
            setText(text, "");
        } else {
            super.setText(text);
        }
    }

    public void cleanText(int hintColor) {
        this.setHintTextColor(hintColor);
        super.setText("");
    }
}
